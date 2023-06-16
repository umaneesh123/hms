package com.sillyproject.userservice.service;

import com.sillyproject.userservice.dto.UserDto;
import com.sillyproject.userservice.payload.ApiResponse;
import com.sillyproject.userservice.payload.SignUpRequest;


public interface IUserService{	
	ApiResponse saveUser(SignUpRequest signUpRequest);			
	UserDto getUser();
}
