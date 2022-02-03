package com.codingRfun.hibernate.jdbc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.codingRfun.course.model.Course;
import com.codingRfun.instructor.model.Instructor;
import com.codingRfun.instructor_details.model.InstructorDetails;
import com.codingRfun.student.model.Student;

public class LazyFetchingError {

	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class).addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetails.class).addAnnotatedClass(Course.class).buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();

		session.beginTransaction();
		try {
			Instructor instructor = session.get(Instructor.class, 1);

			/*
			 * configure instructor courses (fetch to FetchType.LAZY)
			 * 
			 * toggle breakpoint in line 22 to see how lazy loading 
			 * is fetched from the db
			 * 
			 * it will fetch all the courses the moment you view or 
			 * get the list of courses in the instructor object
			 */

			System.out.println("Fun: " + instructor);
			
			/*
			 * this will crash because you closed connection 
			 * before you view the lazy fetch data
			 * 
			 * you must not close connection before
			 * you view the data
			 * 
			 * Error: 
			 * org.hibernate.LazyInitializationException: 
			 * failed to lazily initialize a collection
			 */
			session.close();
			
			System.out.println("Fun: " + instructor.getCourses());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}

	}
}
