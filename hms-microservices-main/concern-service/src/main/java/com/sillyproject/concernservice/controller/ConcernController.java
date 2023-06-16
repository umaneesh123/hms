package com.sillyproject.concernservice.controller;

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

import com.sillyproject.concernservice.dto.ConcernDto;
import com.sillyproject.concernservice.payload.ApiResponse;
import com.sillyproject.concernservice.service.IConcernService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value="/concern")
@RequiredArgsConstructor
public class ConcernController {
	
	private IConcernService concernService;
	
	@PostMapping
	public ResponseEntity<ApiResponse> addConcern(@RequestBody ConcernDto concernDto) {	
    	return ResponseEntity.ok().body(concernService.addConcern(concernDto)); 
    }
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> updateConcern(@RequestBody ConcernDto concernDto, @PathVariable int id) {	
    	return ResponseEntity.ok().body(concernService.updateConcern(concernDto, id)); 
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<ConcernDto> viewConcern(@PathVariable int id) {	
    	return ResponseEntity.ok().body(concernService.viewConcern(id)); 
    }
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/get-all-concerns")
	public ResponseEntity<Page<ConcernDto>> getAllConcerns(Pageable pageable) {			
    	return ResponseEntity.ok().body(concernService.getAllConcerns(pageable)); 
    }	
	
	@GetMapping("/get-all-concerns-for-single-user")
	public ResponseEntity<Page<ConcernDto>> getAllConcernsForSingleUser(Pageable pageable) {			
    	return ResponseEntity.ok().body(concernService.getAllConcernsForSingleUser(pageable)); 
    }
}
