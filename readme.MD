# **MUST ADD TO BUILD PATH HIBERNATE ORM JARS**

# **DROP ALL TABLES FROM ANOTHER BRANCH IF YOU HAVE THAT, BECAUSE THIS BRANCH TABLE'S NAME IS DIFFERENT**

# SQL

Drop Table

```sql
DROP TABLE IF EXISTS
		reviews, courses, instructor_details, instructors, student;
```

Student Table

```sql
CREATE TABLE students (
		id serial4 NOT NULL,
		first_name text NULL,
		last_name text NULL,
		email text NULL,
		CONSTRAINT student_pkey PRIMARY KEY (id)
);
```

Instructor Details Table

```sql
CREATE TABLE public.instructor_details (
		id SERIAL,
		youtube_channel text NULL,
		hobby text NULL,
		CONSTRAINT "id_pkey" PRIMARY KEY (id)
);
```

Instructor Table

```sql
CREATE TABLE public.instructors (
		id SERIAL,
		first_name text NULL,
		last_name text NULL,
		email text NULL,
		instructor_details_id int4 NULL,
		CONSTRAINT instructor_pkey PRIMARY KEY (id),
		CONSTRAINT fk_instructor_details_id 
		FOREIGN KEY (instructor_details_id) 
		REFERENCES instructor_details(id)
);
```

Course Table

```sql
CREATE TABLE courses(
		id SERIAL PRIMARY KEY,
		title TEXT,
		instructor_id INTEGER NULL,
		UNIQUE(title),
		CONSTRAINT FK_instructor_id
		FOREIGN KEY (instructor_id) REFERENCES instructors (id)
);
```

Review Table

```sql
CREATE TABLE reviews(
		id SERIAL,
		COMMENT TEXT,
		course_id INTEGER,
		CONSTRAINT review_id_pkey PRIMARY KEY (id),
		CONSTRAINT fk_course_id
		FOREIGN KEY (course_id) REFERENCES courses(id)
);
```

Truncate All Tables

```sql
TRUNCATE "instructor_details", instructors, student, course, reviews
RESTART IDENTITY CASCADE;
```

# INSERTS

INSERT INSTRUCTOR AND INSTRUCTOR DETAILS

```java
	Instructor instructor = new Instructor("Ker", "Tenepere",
	"kertenepere@gmail.com"); InstructorDetails instructorDetails = new
	InstructorDetails("Coder Coding","Problem Solving");
	instructor.setInstructorDetails(instructorDetails);

	session.persist(instructor);

	session.getTransaction().commit();
```

INSERT COURSES

```JAVA
	Instructor instructor = session.get(Instructor.class, 1);
	instructor.addCourse(new Course("ReactJS Mastery"));
	instructor.addCourse(new Course("Powershell User Bootcamp"));
	instructor.addCourse(new Course("Spring Masterclass"));

	session.persist(instructor);

	session.getTransaction().commit();
```

INSERT REVIEW

```JAVA
	Course course = session.get(Course.class, 1);
	course.addReview(new Review("excellent course"));

	session.persist(course);
	session.getTransaction().commit();
```
