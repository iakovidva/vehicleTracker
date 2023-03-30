package com.vasileiosiakovidis.VehicleTracker.models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotEmpty;

@MappedSuperclass
public class Person {

	@Column(name = "first_name")
	@NotEmpty(message="required field")
	private String firstName;

	@Column(name = "last_name")
	@NotEmpty(message="required field")
	private String lastName;
	
	@Column(name = "email")
	@NotEmpty(message="required field")
	private String email;

	@Column(name = "phone_number")
	@NotEmpty(message="required field")
	private String phoneNumber;

	@Column(name = "password")
	@NotEmpty(message="required field")
	private String password;
	
	public Person() {
		
	}
	
	public Person(@NotEmpty(message = "required field") String firstName,
			@NotEmpty(message = "required field") String lastName, @NotEmpty(message = "required field") String email,
			@NotEmpty(message = "required field") String phoneNumber,
			@NotEmpty(message = "required field") String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phoneNumber="
				+ phoneNumber + "]";
	}
	
}
