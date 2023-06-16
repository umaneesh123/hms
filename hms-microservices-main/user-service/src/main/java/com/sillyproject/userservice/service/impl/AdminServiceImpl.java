package com.sillyproject.userservice.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.sillyproject.userservice.dto.AdminDto;
import com.sillyproject.userservice.exception.ResourceNotFoundException;
import com.sillyproject.userservice.model.Admin;
import com.sillyproject.userservice.model.User;
import com.sillyproject.userservice.payload.ApiResponse;
import com.sillyproject.userservice.repository.AdminRepository;
import com.sillyproject.userservice.repository.UserRepository;
import com.sillyproject.userservice.service.IAdminService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements IAdminService {	
	
	private AdminRepository adminRepository;
	private ModelMapper modelMapper;
	private UserRepository userRepository;

	@Override
	public AdminDto viewAdmin() {
		String userName = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		User user = userRepository.findByUserName(userName)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));
		Admin admin = adminRepository.findByUser(user)
				.orElseThrow(() -> new ResourceNotFoundException("Admin not found"));	
		return modelMapper.map(admin, AdminDto.class);
	}	

	@Override
	public ApiResponse updateAdmin(AdminDto adminDto) {
		String userName = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		User user = userRepository.findByUserName(userName)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));
		Admin admin = adminRepository.findByUser(user)
				.orElseThrow(() -> new ResourceNotFoundException("Admin not found"));
		admin.setDepartment(adminDto.getDepartment());
		admin.setDesignation(adminDto.getDesignation());
		admin.setEmailId(adminDto.getEmailId());
		admin.setMobileNo(adminDto.getMobileNo());
		adminRepository.save(admin);
		return new ApiResponse(true, "Admin details updated successfully");
	}
}
