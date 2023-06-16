package com.sillyproject.userservice.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AllotmentStatus {	
	private int roomNo;
	private String hostelName;
	private String hostelAddress;
	private String contactPerson;
	private String contactPhone;
	private boolean allotmentStatus;
	private boolean paymentStatus;
}
