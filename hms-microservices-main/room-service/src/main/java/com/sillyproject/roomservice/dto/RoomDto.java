package com.sillyproject.roomservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomDto {	
	private int id;
	private int hostelId;
	private String hostelName;
	private int roomNo;
	private boolean isVacant;	
}
