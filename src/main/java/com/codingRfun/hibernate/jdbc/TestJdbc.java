package com.codingRfun.hibernate.jdbc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.codingRfun.course.model.Course;
import com.codingRfun.instructor.model.Instructor;
import com.codingRfun.instructor_details.model.InstructorDetails;
import com.codingRfun.student.model.Student;

public class TestJdbc {

	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class).addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetails.class).addAnnotatedClass(Course.class).buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
//		getInstructor(session);
//		getInstructorDetails(session, new InstructorDetails(1));
//		deleteCascade(session, new Instructor(29));
//		deleteCascadeInstructorDetailsBiDirectional(session, new InstructorDetails(1));
//		dontDeleteCascadeInstructorDetails(session, new InstructorDetails(2));
		
//		InstructorDetails instructorDetails = new InstructorDetails("ker tenepere", "expert");
//		Instructor instructor = new Instructor("ker", "tenepere", "ker@gmail.com");
//		Session currentSession1 = sessionFactory.getCurrentSession();
//		insertOneToOne(currentSession1, instructor, instructorDetails);
//		System.out.println("Outside Instructor" + instructor);
//		Session currentSession2 = sessionFactory.getCurrentSession();
//		createCourses(currentSession2, instructor, new Course("Ice Skating MasterClass"),
//				new Course("Cooking MasterClass"), new Course("ReactJs MasterClass"));
		getAllCoursesFromInstructor(session, new Instructor(1));
	}

	public static void insertOneToOne(Session session, Instructor instructor, InstructorDetails instructorDetails) {

		instructor.setInstructorDetails(instructorDetails);

		try {
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

			System.out.println(instructor);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	public static void deleteCascadeInstructor(Session session, Instructor item) {
		session.beginTransaction();

		try {
			Instructor instructor = session.get(item.getClass(), item.getId());
			System.out.println("fetched instructor: " + instructor + "\n");

			// delete cascade will only work if Cascase.REMOVE is added
			session.delete(instructor);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	public static void deleteCascadeInstructorDetailsBiDirectional(Session session, InstructorDetails item) {
		session.beginTransaction();

		try {
			InstructorDetails instructorDetails = session.get(item.getClass(), item.getId());
			System.out.println("fetched instructor: " + instructorDetails + "\n");

			// delete cascade will only work if Cascase.REMOVE is added
			session.delete(instructorDetails);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	public static void getInstructor(Session session, Instructor item) {
		session.beginTransaction();
		try {
			Instructor instructor = session.get(Instructor.class, item.getId());
			System.out.println(instructor);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public static void getInstructorDetails(Session session, InstructorDetails item) {
		session.beginTransaction();
		try {
			InstructorDetails instructorDetails = session.get(InstructorDetails.class, item.getId());
			System.out.println("fetched instructor details: " + instructorDetails);
			System.out.println("fetched instructor: " + instructorDetails.getInstructor());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public static void dontDeleteCascadeInstructorDetails(Session session, InstructorDetails item) {
		session.beginTransaction();
		try {
			InstructorDetails instructorDetails = session.get(InstructorDetails.class, item.getId());
			instructorDetails.getInstructor().setInstructorDetails(null);

			/*
			 * this deletion will also remove instructorDetails ID reference in the
			 * instructor
			 */

			session.delete(instructorDetails);
			session.getTransaction().commit();

			System.out.println("DELETED: " + instructorDetails);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public static void createCourses(Session session, Instructor item, Course... courses) {
		session.beginTransaction();

		try {
			Instructor instructor = session.get(Instructor.class, item.getId());

			for (Course course : courses) {
				instructor.addCourse(course);
			}

			session.persist(instructor);

			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public static void getAllCoursesFromInstructor(Session session, Instructor item) {
		session.beginTransaction();
		try {
			Instructor instructor = session.get(Instructor.class, item.getId());
			System.out.println();
			System.out.println(instructor);
			System.out.println(instructor.getCourses());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
