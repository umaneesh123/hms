package com.sillyproject.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {	
	private int id;
	private String firstName;
	private String lastName;	
	private String userName;
	private String empId;
	private String department;	
	private String emailId;
	private String mobileNo;	
	private String designation;
}
