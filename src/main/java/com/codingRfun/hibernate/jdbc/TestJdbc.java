package com.codingRfun.hibernate.jdbc;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.codingRfun.student.model.Student;

@SuppressWarnings("unchecked")
public class TestJdbc {

	public static void main(String[] args) {
		// the default config name of hibernate is hibernate.cfg.xml
		// config name is optional if your config name is default name

		// 1. create SessionFactory that stores details of the database
		// Only create one SessionFactory for the whole application
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class).buildSessionFactory();

		// 2. get connection session to be used for database operations
		Session session = sessionFactory.getCurrentSession();

		// 3. open connection that communicates your app to database
		session.beginTransaction();

		// 4. create a Hibernate Query Language and return the results
		List<Student> students = session.createQuery("from Student").getResultList();

		// 5. close the session
		session.close();
		System.out.println(students);
	}

}
