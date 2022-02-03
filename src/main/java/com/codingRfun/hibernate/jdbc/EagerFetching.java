package com.codingRfun.hibernate.jdbc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.codingRfun.course.model.Course;
import com.codingRfun.instructor.model.Instructor;
import com.codingRfun.instructor_details.model.InstructorDetails;
import com.codingRfun.student.model.Student;

public class EagerFetching {

	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class).addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetails.class).addAnnotatedClass(Course.class).buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();

		session.beginTransaction();
		try {
			Instructor instructor = session.get(Instructor.class, 1);

			/*
			 * configure instructor courses (fetch to FetchType.EAGER)
			 * 
			 * toggle breakpoint in line 22 to see how eager loading 
			 * is fetched from the db
			 * 
			 * it will fetch all the courses the moment it fetches 
			 * instructor
			 */

			System.out.println("Fun: " + instructor);
			System.out.println("Fun: " + instructor.getCourses());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

	}
}
