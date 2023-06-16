package com.sillyproject.userservice.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sillyproject.userservice.dto.UserDto;
import com.sillyproject.userservice.exception.ResourceNotFoundException;
import com.sillyproject.userservice.model.Role;
import com.sillyproject.userservice.model.User;
import com.sillyproject.userservice.payload.ApiResponse;
import com.sillyproject.userservice.payload.SignUpRequest;
import com.sillyproject.userservice.repository.UserRepository;
import com.sillyproject.userservice.service.IUserService;

import in.cdac.hms.payload.JwtAuthenticationResponse;
import in.cdac.hms.payload.LoginRequest;
import in.cdac.hms.security.JwtUtil;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class AuthController {   	
	
	private JwtUtil jwtUtil;	
	private AuthenticationManager authenticationManager; 	
    private IUserService userService;	
	private UserRepository userRepository; 		

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateStudent(@RequestBody LoginRequest loginRequest) throws Exception {
		System.out.println(loginRequest);
		String jwt = null;
		List<String> roleNames = null;
		try {
			authenticationManager.authenticate(
			        new UsernamePasswordAuthenticationToken(
			                loginRequest.getUserName(),
			                loginRequest.getPassword()
			        )
			);
			jwt =  jwtUtil.generateToken(loginRequest.getUserName());

			User user;
			try {
				user = userRepository.findByUserName(loginRequest.getUserName())
						.orElseThrow(() -> new ResourceNotFoundException("User not found"));
				roleNames = user.getRoles().stream().map((role) -> mapToRoleNames(role)).collect(Collectors.toList());

			} catch (Exception e) {
				roleNames = null;
			}
			return ResponseEntity.ok(new JwtAuthenticationResponse(jwt,roleNames));

		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().body(new JwtAuthenticationResponse(jwt,roleNames));		}

	}
	
    private String mapToRoleNames(Role role) {    	
		return role.getName();
	}

	@PostMapping("/signup")
    public ResponseEntity<?> saveUser(@RequestBody SignUpRequest signUpRequest) {
    	if (userRepository.existsByUserName(signUpRequest.getUserName())) {
			return ResponseEntity
					.badRequest()
					.body(new ApiResponse(false,"Username is already taken!"));
		}
    	userService.saveUser(signUpRequest);
    	URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/signup").toUriString());
    	return ResponseEntity.created(uri).body(new ApiResponse(true,"User registered successfully!"));
    }
	
	@GetMapping
	public ResponseEntity<UserDto> getUser() {
		return ResponseEntity.ok().body(userService.getUser());
	}
}
