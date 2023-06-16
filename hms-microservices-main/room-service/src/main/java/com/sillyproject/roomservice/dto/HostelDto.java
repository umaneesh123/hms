package com.sillyproject.roomservice.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HostelDto {	
	private int id;
	private String name;	
	private String contactPerson;	
	private String contactMobileNo;	
	private String address;	
	private int hostelFees;
	private int totalRooms;	
}
