package com.sillyproject.roomservice.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sillyproject.roomservice.dto.HostelDto;
import com.sillyproject.roomservice.payload.ApiResponse;
import com.sillyproject.roomservice.service.IHostelService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value="/hostel")
@RequiredArgsConstructor
public class HostelController {
	
	private IHostelService hostelService;

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
    public ResponseEntity<ApiResponse> addHostel(@RequestBody HostelDto hostelDto) {
    	return ResponseEntity.ok().body(hostelService.addHostel(hostelDto)); 
    }
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateHostel(@RequestBody HostelDto hostelDto, @PathVariable int id) throws Exception {
    	return ResponseEntity.ok().body(hostelService.updateHostel(hostelDto, id)); 
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<HostelDto> viewHostel(@PathVariable int id) {		
    	return ResponseEntity.ok().body(hostelService.viewHostel(id)); 
    }
	
	@GetMapping("/get-all-hostels")
    public ResponseEntity<Page<HostelDto>> getAllHostels(Pageable pageable) {		
    	return ResponseEntity.ok().body(hostelService.getAllHostels(pageable)); 
    }	
}
