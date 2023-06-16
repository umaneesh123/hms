package com.sillyproject.roomservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sillyproject.roomservice.dto.RoomDto;
import com.sillyproject.roomservice.payload.ApiResponse;
import com.sillyproject.roomservice.payload.RoomDetails;

public interface IRoomService {	
	ApiResponse addRoomToHostel(RoomDto roomDto, int hostelId) throws Exception;
	Page<RoomDto> displayRooms(Pageable pageable, int hostelId);
	ApiResponse updateRoom(RoomDto roomDto, int roomId);
	ApiResponse deleteRoom(int roomId);
	RoomDetails getRoomDetails();
	ApiResponse bookRoom(int roomId);
}
