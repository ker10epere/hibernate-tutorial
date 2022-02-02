package com.codingRfun.hibernate.jdbc;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.codingRfun.instructor.model.Instructor;
import com.codingRfun.instructor_details.model.InstructorDetails;
import com.codingRfun.student.model.Student;

@SuppressWarnings("unchecked")
public class TestJdbc {

	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class).addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetails.class).buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
//		getInstructor(session);
		insertOneToOne(session);
	}

	public static void insertOneToOne(Session session) {
		InstructorDetails instructorDetails = new InstructorDetails("leonardo de caprio", "actor");
		
		Instructor instructor = new Instructor("leonardo", "de caprio", "leonardo@gmail.com");
		
		instructor.setInstructorDetails(instructorDetails);
		
		session.beginTransaction();

		/*
		 * change cascade to ALL, if you want to use session.save operations
		 * 
		 * cascase ALL is also applicable to session.persist and other session operations
		 * 
		 * @OneToOne(cascade = { CascadeType.ALL })
		 */
		
		session.persist(instructor);

		session.getTransaction().commit();

		session.close();

		System.out.println(instructor);
	}

	public static void getInstructor(Session session) {
		session.beginTransaction();
		Instructor instructor = session.get(Instructor.class, 1);
		session.refresh(instructor);
		System.out.println(instructor);
	}

}
