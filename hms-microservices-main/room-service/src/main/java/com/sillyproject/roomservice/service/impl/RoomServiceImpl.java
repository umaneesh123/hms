	package com.sillyproject.roomservice.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.sillyproject.roomservice.dto.RoomDto;
import com.sillyproject.roomservice.exception.ResourceNotFoundException;
import com.sillyproject.roomservice.model.Hostel;
import com.sillyproject.roomservice.model.Room;
import com.sillyproject.roomservice.payload.ApiResponse;
import com.sillyproject.roomservice.payload.RoomDetails;
import com.sillyproject.roomservice.repository.HostelRepository;
import com.sillyproject.roomservice.repository.RoomRepository;
import com.sillyproject.roomservice.service.IRoomService;
import com.sillyproject.userservice.model.Student;
import com.sillyproject.userservice.model.User;
import com.sillyproject.userservice.service.impl.Payment;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements IRoomService {

	private HostelRepository hostelRepository;
	private RoomRepository roomRepository;
	private ModelMapper modelMapper;
	private WebClient webClient;

	@Override
	public ApiResponse addRoomToHostel(RoomDto roomDto, int hostelId) {
		Hostel hostel = hostelRepository.findById(hostelId)
				.orElseThrow(() -> new ResourceNotFoundException("Hostel not found"));
		Room room = new Room();
		room.setHostel(hostel);
		room.setRoomNo(roomDto.getRoomNo());
		roomRepository.save(room);
		return new ApiResponse(true, "Room added successfully");
	}

	@Override
	public Page<RoomDto> displayRooms(Pageable pageable, int hostelId) {
		Hostel hostel = hostelRepository.findById(hostelId)
				.orElseThrow(() -> new ResourceNotFoundException("Hostel not found"));;
		Page<Room> rooms = roomRepository.findAllRoomsByHostel(hostel, pageable);
		List<RoomDto> roomDtos = rooms.stream().map((room) -> modelMapper.map(room, RoomDto.class)).collect(Collectors.toList());
	    return new PageImpl<>(roomDtos, pageable, rooms.getTotalElements());
	}

	@Override
	public ApiResponse updateRoom(RoomDto roomDto, int roomId) {
		Room room = roomRepository.findById(roomDto.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Room not found"));
		room.setRoomNo(roomDto.getRoomNo());
		roomRepository.save(room);
		return new ApiResponse(true, "Room updated successfully");
	}

	@Override
	public ApiResponse deleteRoom(int roomId) {
		Room room = roomRepository.findById(roomId)
				.orElseThrow(() -> new ResourceNotFoundException("Room not found"));
		if(room.getUsername() != null) throw new RuntimeException("Room could not be deleted as it is alloted to a Student");
		room.setHostel(null);		
		roomRepository.delete(room);
		return new ApiResponse(true, "Room deleted successfully");
	}

	@Override
	public RoomDetails getRoomDetails() {
		String userName = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		Room room = roomRepository.findByUsername(userName)
				.orElseThrow(() -> new ResourceNotFoundException("Room not found"));
		RoomDetails roomDetails = new RoomDetails();
		roomDetails.setRoomNo(room.getRoomNo());
		roomDetails.setHostelName(room.getHostel().getName());
		return roomDetails;
	}
	
	@Override
	public ApiResponse bookRoom(int roomId) {
		
		RoomDetails roomDetails = webClient.get()
				.uri("")
				.retrieve()
				.bodyToMono(RoomDetails.class)
				.block();
		
		boolean transactionStatus = getTransactionStatus();
		if (payment.getTransactionStatus() && student.getRoom() == null) {
			Room room = roomRepository.findById(roomId)
					.orElseThrow(() -> new ResourceNotFoundException("Room not found"));
			room.setIsVacant(false);
			student.setRoom(room);
			studentRepository.save(student);			
			roomRepository.save(room);
			return new ApiResponse(true, "Room booked successfully");
		} 
		return new ApiResponse(false, "Failed to book  room. Please try again");
	}
}
