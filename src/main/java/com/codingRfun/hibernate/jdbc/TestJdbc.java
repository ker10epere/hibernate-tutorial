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

		// 1. start transaction
		session.beginTransaction();

		// 2. get the student using the id in the second argument
		// it will return the student from database
		Student student = session.get(Student.class, 1);
		
		// 3. commit the transaction
		session.getTransaction().commit();
		
		// 4. close the session
		session.close();
		
		System.out.println(student);
	}

}
