package com.sillyproject.userservice.service.impl;

import java.util.Arrays;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sillyproject.userservice.dto.UserDto;
import com.sillyproject.userservice.exception.ResourceNotFoundException;
import com.sillyproject.userservice.model.User;
import com.sillyproject.userservice.payload.ApiResponse;
import com.sillyproject.userservice.payload.SignUpRequest;
import com.sillyproject.userservice.repository.RoleRepository;
import com.sillyproject.userservice.repository.UserRepository;
import com.sillyproject.userservice.service.IUserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	private ModelMapper modelMapper;

	@Override
	public ApiResponse saveUser(SignUpRequest signUpRequest) {		
		User user = new User();
		user.setFirstName(signUpRequest.getFirstName());
		user.setLastName(signUpRequest.getLastName());
		user.setUserName(signUpRequest.getUserName());
		user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
		user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_STUDENT")));
		userRepository.save(user);
		return new ApiResponse(true, "User saved successfully");
	}

	@Override
	public UserDto getUser() {
		String userName = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		User user = userRepository.findByUserName(userName)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));		
		return modelMapper.map(user, UserDto.class);
	}

}
