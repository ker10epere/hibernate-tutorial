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
		
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class).buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();

		// 1. create student object
		Student student = new Student("angelina", "jolie", "angelina@gmail.com");

		// 2. start transaction
		session.beginTransaction();

		// 3. save the student
		session.save(student);
		
		// 4. commit the transaction
		session.getTransaction().commit();
		
		// 5. close the session
		session.close();
		
		// hibernate automatically sets the id of the student object
		// Student [id=4, firstName=angelina, lastName=jolie, email=angelina@gmail.com]
		System.out.println(student);
	}

}
