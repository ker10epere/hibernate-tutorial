must add to build path hibernate orm jars



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
CREATE TABLE  "instructor-details"(
	id SERIAL PRIMARY KEY,
	youtube_channel TEXT DEFAULT NULL,
	hobby TEXT DEFAULT NULL
)
```

Instructor Details Table
```sql
CREATE TABLE  instructors(
	id SERIAL PRIMARY KEY,
	first_name TEXT DEFAULT NULL,
	last_name TEXT DEFAULT NULL,
	email text TEXT DEFAULT NULL,
	"instructor-details-id" INT DEFAULT NULL,
	CONSTRAINT fk_instructor_details_id
	FOREIGN KEY("instructor-details-id") 
	REFERENCES instructor(id)
)
```
