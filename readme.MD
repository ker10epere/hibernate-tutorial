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
CREATE TABLE public.instructor_details (
	id int4 NOT NULL DEFAULT nextval('"instructor-details_id_seq"'::regclass),
	youtube_channel text NULL,
	hobby text NULL,
	CONSTRAINT "instructor_details_pkey" PRIMARY KEY (id)
);
```

Instructor Table
```sql
CREATE TABLE public.instructors (
	id int4 NOT NULL DEFAULT nextval('instructor_id_seq'::regclass),
	first_name text NULL,
	last_name text NULL,
	email text NULL,
	instructor_details_id int4 NULL,
	CONSTRAINT instructor_pkey PRIMARY KEY (id),
	CONSTRAINT fk_instructor_details_id FOREIGN KEY (instructor_details_id) REFERENCES public.instructor_details(id)
);
```
