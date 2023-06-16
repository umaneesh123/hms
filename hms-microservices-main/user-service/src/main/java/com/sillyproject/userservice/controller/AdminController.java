package com.sillyproject.userservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sillyproject.userservice.dto.AdminDto;
import com.sillyproject.userservice.payload.ApiResponse;
import com.sillyproject.userservice.service.IAdminService;

import lombok.RequiredArgsConstructor;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value="/admin")
@RequiredArgsConstructor
public class AdminController {
	
	private IAdminService adminService;
	
	@GetMapping("/view")
	public ResponseEntity<AdminDto> viewAdmin() {			
    	return ResponseEntity.ok().body(adminService.viewAdmin()); 
    }	

	@PutMapping("/update")
	public ResponseEntity<?> updateAdmin(@RequestBody AdminDto adminDto) {	
		adminService.updateAdmin(adminDto);
    	return ResponseEntity.ok().body(new ApiResponse(true, "Updated Admin successfully")); 
    }	
}
