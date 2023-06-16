package com.sillyproject.concernservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sillyproject.concernservice.dto.ConcernDto;
import com.sillyproject.concernservice.payload.ApiResponse;

public interface IConcernService {
	ApiResponse addConcern(ConcernDto concernDto);
	ApiResponse updateConcern(ConcernDto concernDto,int concernId);
	ConcernDto viewConcern(int concernId);
	Page<ConcernDto> getAllConcerns(Pageable pageable);
	Page<ConcernDto> getAllConcernsForSingleUser(Pageable pageable);	
}
