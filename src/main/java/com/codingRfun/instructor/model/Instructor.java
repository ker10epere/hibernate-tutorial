package com.codingRfun.instructor.model;
 
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.codingRfun.course.model.Course;
import com.codingRfun.instructor.interfaces._Instructor;
import com.codingRfun.instructor_details.model.InstructorDetails;

@Entity
@Table(name = "instructors")
public class Instructor implements _Instructor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinColumn(name = "instructor_details_id")
	private InstructorDetails instructorDetails;

	@OneToMany(mappedBy = "instructor", cascade = { CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE,
			CascadeType.REFRESH })
	private List<Course> courses;

	public Instructor(Integer id, String firstName, String lastName, String email,
			InstructorDetails instructorDetails) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.instructorDetails = instructorDetails;
	}

	public Instructor(String firstName, String lastName, String email, InstructorDetails instructorDetails) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.instructorDetails = instructorDetails;
	}

	public Instructor(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public Instructor(Integer id) {
		super();
		this.id = id;
	}

	public Instructor() {
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

	public InstructorDetails getInstructorDetails() {
		return instructorDetails;
	}

	public void setInstructorDetails(InstructorDetails instructorDetails) {
		this.instructorDetails = instructorDetails;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	
	// add convenience method for bi-directional relationship
	public void addCourse(Course item) {
		if(courses == null) {
			courses = new ArrayList<Course>();
		}
		
		courses.add(item);
		
		item.setInstructor(this);
	}
	
	@Override
	public String toString() {
		return String.format("Instructor [id=%s, firstName=%s, lastName=%s, email=%s, instructorDetails=%s]", id,
				firstName, lastName, email, instructorDetails);
	}

}
