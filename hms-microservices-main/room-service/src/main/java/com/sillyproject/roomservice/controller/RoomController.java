package com.sillyproject.roomservice.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sillyproject.roomservice.dto.RoomDto;
import com.sillyproject.roomservice.payload.ApiResponse;
import com.sillyproject.roomservice.payload.RoomDetails;
import com.sillyproject.roomservice.service.IRoomService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/room")
@RequiredArgsConstructor
public class RoomController {

	private IRoomService roomService;

	@PostMapping("/{hostelId}")
	public ResponseEntity<ApiResponse> addRoom(@RequestBody RoomDto roomDto, @PathVariable int hostelId)
			throws Exception {
		return ResponseEntity.ok().body(roomService.addRoomToHostel(roomDto, hostelId));
	}

	@PutMapping("/{roomId}")
	public ResponseEntity<ApiResponse> updateRoom(@RequestBody RoomDto roomDto, @PathVariable int roomId) {
		return ResponseEntity.ok().body(roomService.updateRoom(roomDto, roomId));
	}

	@GetMapping("/get-all-rooms/{hostelId}")
	public ResponseEntity<Page<RoomDto>> displayRooms(Pageable pageable, @PathVariable int hostelId) {
		return ResponseEntity.ok().body(roomService.displayRooms(pageable, hostelId));
	}
	
	@GetMapping("/get-room-details")
	public ResponseEntity<RoomDetails> getRoomDetails() {
		return ResponseEntity.ok().body(roomService.getRoomDetails());
	}

	@DeleteMapping("/{roomId}")
	public ResponseEntity<ApiResponse> deleteRoom(@PathVariable int roomId) {
		return ResponseEntity.ok().body(roomService.deleteRoom(roomId));
	}
	
	@PostMapping("/book")
	public ResponseEntity<ApiResponse> bookRoom(@RequestParam int roomId) {
		return ResponseEntity.ok().body(roomService.bookRoom(roomId));
	}
}
