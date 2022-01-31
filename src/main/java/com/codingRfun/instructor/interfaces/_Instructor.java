package com.codingRfun.instructor.interfaces;

import com.codingRfun.instructor_details.model.InstructorDetails;

public interface _Instructor {
	public Integer getId();

	public void setId(Integer id);

	public String getFirstName();

	public void setFirstName(String firstName);

	public String getLastName();

	public void setLastName(String lastName);

	public String getEmail();

	public void setEmail(String email);

	public InstructorDetails getInstructorDetails();

	public void setInstructorDetails(InstructorDetails instructorDetails);

	public String toString();
}
