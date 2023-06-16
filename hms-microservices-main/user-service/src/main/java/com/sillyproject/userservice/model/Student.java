package com.sillyproject.userservice.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class Student extends Base {

	@Column(name = "email_id", unique = true)
	private String emailId;
	
	@Column(name = "mobile_no", unique = true)
	private String mobileNo;	
	
	private String degree;
	
	private String course;
	
	@Column(name = "academic_year")
	private String academicYear;
	
	@Column(name = "date_of_birth")	
	private LocalDateTime dateOfBirth;
	
	private String gender;
	
	private String address;	
	
	@OneToOne
    @JoinColumn(name = "user_id")
    private User user;
	
	private String hostelName;
	
	private int roomNo;
}
