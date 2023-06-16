package com.sillyproject.roomservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sillyproject.roomservice.dto.HostelDto;
import com.sillyproject.roomservice.payload.ApiResponse;

public interface IHostelService {	
	ApiResponse addHostel(HostelDto hostelDto);
	ApiResponse updateHostel(HostelDto hostelDto, int hostelId);
	HostelDto viewHostel(int hostelId);
	Page<HostelDto> getAllHostels(Pageable pageable);
}
