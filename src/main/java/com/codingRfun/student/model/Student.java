package com.codingRfun.student.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.codingRfun.student.interfaces._Student;

@Entity
@Table(name = "student")
public class Student implements _Student {
	@Id
	@Column(name = "id")
//	@Column
	private Integer id;

	@Column(name = "first_name")
//	@Column
	private String firstName;

	@Column(name = "last_name")
//	@Column
	private String lastName;

	@Column(name = "email")
//	@Column
	private String email;

	public Student(Integer id, String firstName, String lastName, String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public Student(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public Student(Integer id) {
		super();
		this.id = id;
	}

	public Student() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return String.format("Student [id=%s, firstName=%s, lastName=%s, email=%s]", id, firstName, lastName, email);
	}

}
