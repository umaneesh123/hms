package com.sillyproject.userservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sillyproject.userservice.dto.StudentDto;
import com.sillyproject.userservice.payload.AllotmentStatus;
import com.sillyproject.userservice.payload.ApiResponse;

public interface IStudentService {	
	ApiResponse updateStudent(StudentDto studentDto);
	StudentDto viewStudent();
	Page<StudentDto> getAllStudents(Pageable pageable);
	AllotmentStatus getAllotmentStatus();
}
