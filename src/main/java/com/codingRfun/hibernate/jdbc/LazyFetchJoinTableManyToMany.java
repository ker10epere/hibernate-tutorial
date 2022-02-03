package com.codingRfun.hibernate.jdbc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.codingRfun.course.model.Course;
import com.codingRfun.instructor.model.Instructor;
import com.codingRfun.instructor_details.model.InstructorDetails;
import com.codingRfun.review.model.Review;
import com.codingRfun.student.model.Student;

public class LazyFetchJoinTableManyToMany {

	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class).addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetails.class).addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class).buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();

		session.beginTransaction();
		
		System.out.println();
		System.out.println();
		try {
			Course item = new Course(5);
			
			Course course = session.get(Course.class, item.getId());
			
			System.out.println("FUN Course: " + course);
			System.out.println("FUN Course Students: " + course.getStudents());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

	}
}
/*
 * INSERT INSTRUCTOR AND INSTRUCTOR DETAILS
 * 
 * Instructor instructor = new Instructor("Ker", "Tenepere",
 * "kertenepere@gmail.com"); InstructorDetails instructorDetails = new
 * InstructorDetails("Coder Coding","Problem Solving");
 * instructor.setInstructorDetails(instructorDetails);
 * 
 * session.persist(instructor);
 * 
 * session.getTransaction().commit();
 */


/*
 * INSERT COURSES
 * 
 * Instructor instructor = session.get(Instructor.class, 1);
 * instructor.addCourse(new Course("ReactJS Mastery")); instructor.addCourse(new
 * Course("Powershell User Bootcamp")); instructor.addCourse(new
 * Course("Spring Masterclass"));
 * 
 * session.persist(instructor);
 * 
 * session.getTransaction().commit();
 */

/*
 * INSERT REVIEW
 * 
 * Course course = session.get(Course.class, 1); course.addReview(new
 * Review("excellent course"));
 * 
 * session.persist(course); session.getTransaction().commit();
 */