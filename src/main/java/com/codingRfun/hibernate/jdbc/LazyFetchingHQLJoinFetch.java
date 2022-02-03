package com.codingRfun.hibernate.jdbc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.codingRfun.course.model.Course;
import com.codingRfun.instructor.model.Instructor;
import com.codingRfun.instructor_details.model.InstructorDetails;
import com.codingRfun.student.model.Student;

public class LazyFetchingHQLJoinFetch {

	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class).addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetails.class).addAnnotatedClass(Course.class).buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();

		session.beginTransaction();
		try {

			Query<Instructor> query = session.createQuery("SELECT x FROM Instructor x"
					+ " JOIN FETCH x.courses WHERE x.id=:instructorId", Instructor.class);
			
			query.setParameter("instructorId", 1);
			
			Instructor instructor = query.getSingleResult();
			/*
			 * x.id=:instructorId - : specifies the following as 
			 * a placeholder, for the value to be mapped in line 27
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
