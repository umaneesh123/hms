package com.sillyproject.userservice.dto;


import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {	
	private int id;
	private String firstName;
	private String lastName;	
	private String userName;	
	private String emailId;	
	private String mobileNo;		
	private String course;
	private LocalDateTime dateOfBirth;	
	private String gender;	
	private String address;	
	private int roomNo;
	private String hostelName;
}
