package com.sillyproject.concernservice.service.impl;

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

import com.sillyproject.concernservice.dto.ConcernDto;
import com.sillyproject.concernservice.exception.ResourceNotFoundException;
import com.sillyproject.concernservice.model.Concern;
import com.sillyproject.concernservice.payload.ApiResponse;
import com.sillyproject.concernservice.payload.RoomDetails;
import com.sillyproject.concernservice.repository.ConcernRepository;
import com.sillyproject.concernservice.service.IConcernService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConcernServiceImpl implements IConcernService {	
	
	private ConcernRepository concernRepository;	
	private ModelMapper modelMapper;
	private WebClient webClient;

	@Override
	public ApiResponse addConcern(ConcernDto concernDto) {
		Concern concern = new Concern();		
		concern.setSubject(concernDto.getSubject());
		concern.setMessage(concernDto.getMessage());
		
		RoomDetails roomDetails = webClient.get()
			.uri("")
			.retrieve()
			.bodyToMono(RoomDetails.class)
			.block();
		
		concern.setRoomNo(roomDetails.getRoomNo());
		concern.setHostelName(roomDetails.getHostelName());
		
		concernRepository.save(concern);
		return new ApiResponse(true, "Concern raised successfully");
	}
	
	@Override
	public ApiResponse updateConcern(ConcernDto concernDto, int concernId) {
		Concern concern = concernRepository.findById(concernId)
				.orElseThrow(() -> new ResourceNotFoundException("Concern not found"));
		concern.setMessage(concernDto.getMessage());
		concernRepository.save(concern);
		return new ApiResponse(true, "Concern updated successfully");
	}

	@Override
	public ConcernDto viewConcern(int concernId) {
		Concern concern = concernRepository.findById(concernId)
				.orElseThrow(() -> new ResourceNotFoundException("Concern not found"));
		return modelMapper.map(concern, ConcernDto.class);
	}
	
	@Override
	public Page<ConcernDto> getAllConcernsForSingleUser(Pageable pageable) {
		String userName = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		Page<Concern> concerns =  concernRepository.findAllByCreatedBy(userName, pageable);
		List<ConcernDto> concernDtos = concerns.stream().map((concern) -> mapToConcernDto(concern)).collect(Collectors.toList());
	    return new PageImpl<>(concernDtos, pageable, concerns.getTotalElements());
	}
	
	
	@Override
	public Page<ConcernDto> getAllConcerns(Pageable pageable) {
		Page<Concern> concerns =  concernRepository.findAll(pageable);
		List<ConcernDto> concernDtos = concerns.stream().map((concern) -> mapToConcernDto(concern)).collect(Collectors.toList());
	    return new PageImpl<>(concernDtos, pageable, concerns.getTotalElements());
	}

	private ConcernDto mapToConcernDto(Concern concern) {
		ConcernDto concernDto = new ConcernDto();
		concernDto.setId(concern.getId());
		concernDto.setMessage(concern.getMessage());
		concernDto.setSubject(concern.getSubject());		
		concernDto.setStudentName(concern.getCreatedBy());
		try {
			concernDto.setHostelName(concern.getHostelName());
			concernDto.setRoomNo(concern.getRoomNo());
		} catch (NullPointerException e) {
			concernDto.setHostelName("Not Booked");
			concernDto.setRoomNo(-1);
		}
		return concernDto;
	}
}
