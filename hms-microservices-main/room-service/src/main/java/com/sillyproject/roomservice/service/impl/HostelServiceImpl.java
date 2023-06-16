	package com.sillyproject.roomservice.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sillyproject.roomservice.dto.HostelDto;
import com.sillyproject.roomservice.exception.ResourceNotFoundException;
import com.sillyproject.roomservice.model.Hostel;
import com.sillyproject.roomservice.payload.ApiResponse;
import com.sillyproject.roomservice.repository.HostelRepository;
import com.sillyproject.roomservice.service.IHostelService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HostelServiceImpl implements IHostelService {	
	
	private HostelRepository hostelRepository;	

	@Override
	public ApiResponse addHostel(HostelDto hostelDto) {
		Hostel hostel = new Hostel();
		hostel.setName(hostelDto.getName());
		hostel.setContactPerson(hostelDto.getContactPerson());
		hostel.setContactMobileNo(hostelDto.getContactMobileNo());
		hostel.setAddress(hostelDto.getAddress());
		hostel.setHostelFees(hostelDto.getHostelFees());		
		hostelRepository.save(hostel);
		return new ApiResponse(true,"Hostel added successfully");
	}

	@Override
	public Page<HostelDto> getAllHostels(Pageable pageable) {
		Page<Hostel> hostels =  hostelRepository.findAll(pageable);
		List<HostelDto> hostelDtos =  hostels.stream().map((hostel) -> mapToHostelDto(hostel)).collect(Collectors.toList());
	    return new PageImpl<>(hostelDtos, pageable, hostels.getTotalElements());
	}
	
	private HostelDto mapToHostelDto(Hostel hostel) {
		HostelDto hostelDto = new HostelDto();
		hostelDto.setId(hostel.getId());
		hostelDto.setName(hostel.getName());
		hostelDto.setContactPerson(hostel.getContactPerson());
		hostelDto.setContactMobileNo(hostel.getContactMobileNo());
		hostelDto.setAddress(hostel.getAddress());
		hostelDto.setHostelFees(hostel.getHostelFees());		
		try {
			hostelDto.setTotalRooms(hostel.getRooms().size());
		} catch (NullPointerException e) {
			hostelDto.setTotalRooms(0);
		}		
		return hostelDto;
	}
	
	@Override
	public ApiResponse updateHostel(HostelDto hostelDto, int hostelId) {
		System.out.println(hostelDto.getHostelFees());
		Hostel hostel = hostelRepository.findById(hostelId)
				.orElseThrow(() -> new ResourceNotFoundException("Hostel not found"));
		hostel.setName(hostelDto.getName());
		hostel.setContactPerson(hostelDto.getContactPerson());
		hostel.setContactMobileNo(hostelDto.getContactMobileNo());
		hostel.setAddress(hostelDto.getAddress());
		hostel.setHostelFees(hostelDto.getHostelFees());
		hostelRepository.save(hostel);	
		return new ApiResponse(true, "Hostel updated successfully");
	}

	@Override
	public HostelDto viewHostel(int hostelId) {
		Hostel hostel = hostelRepository.findById(hostelId)
				.orElseThrow(() -> new ResourceNotFoundException("Hostel not found"));
		HostelDto hostelDto = new HostelDto();
		hostelDto.setId(hostel.getId());
		hostelDto.setName(hostel.getName());
		hostelDto.setContactPerson(hostel.getContactPerson());
		hostelDto.setContactMobileNo(hostel.getContactMobileNo());
		hostelDto.setAddress(hostel.getAddress());
		hostelDto.setHostelFees(hostel.getHostelFees());		
		try {
			hostelDto.setTotalRooms(hostel.getRooms().size());
		} catch (NullPointerException e) {
			hostelDto.setTotalRooms(0);
		}
		
		return hostelDto;
	}
}
