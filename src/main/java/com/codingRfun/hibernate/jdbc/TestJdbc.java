package com.codingRfun.hibernate.jdbc;

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
//		insertOneToOne(session);
//		deleteCascase(session, new Instructor(29));
		getInstructorDetails(session, new InstructorDetails(1));
	}

	public static void insertOneToOne(Session session) {
		InstructorDetails instructorDetails = new InstructorDetails("leonardo de caprio", "actor");

		Instructor instructor = new Instructor("leonardo", "de caprio", "leonardo@gmail.com");

		instructor.setInstructorDetails(instructorDetails);

		session.beginTransaction();

		/*
		 * change cascade to ALL, if you want to use session.save operations
		 * 
		 * cascase ALL is also applicable to session.persist and other session
		 * operations
		 * 
		 * @OneToOne(cascade = { CascadeType.ALL })
		 */

		session.persist(instructor);

		session.getTransaction().commit();

		session.close();

		System.out.println(instructor);
	}

	public static void deleteCascase(Session session, Instructor item) {
		session.beginTransaction();

		Instructor instructor = session.get(item.getClass(), item.getId());
		System.out.println("fetched instructor: " + instructor + "\n");

		// delete cascade will only work if Cascase.REMOVE is added
		session.delete(instructor);
		session.getTransaction().commit();
	}

	public static void getInstructor(Session session) {
		session.beginTransaction();
		Instructor instructor = session.get(Instructor.class, 1);
		session.refresh(instructor);
		System.out.println(instructor);
	}

	public static void getInstructorDetails(Session session, InstructorDetails item) {
		session.beginTransaction();

		InstructorDetails instructorDetails = session.get(InstructorDetails.class, item.getId());

		System.out.println("fetched instructor details: " + instructorDetails);
		System.out.println("fetched instructor: " + instructorDetails.getInstructor());

	}
}
