package com.sillyproject.userservice.service.impl;

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

import com.sillyproject.userservice.dto.StudentDto;
import com.sillyproject.userservice.exception.ResourceNotFoundException;
import com.sillyproject.userservice.model.Student;
import com.sillyproject.userservice.model.User;
import com.sillyproject.userservice.payload.AllotmentStatus;
import com.sillyproject.userservice.payload.ApiResponse;
import com.sillyproject.userservice.repository.StudentRepository;
import com.sillyproject.userservice.repository.UserRepository;
import com.sillyproject.userservice.service.IStudentService;

import in.cdac.hms.model.Payment;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements IStudentService {

	private StudentRepository studentRepository;
	private UserRepository userRepository;
	private ModelMapper modelMapper;
	private WebClient webClient;

	@Override
	public Page<StudentDto> getAllStudents(Pageable pageable) {
		Page<Student> students = studentRepository.findAll(pageable);
		List<StudentDto> studentDtos = students.stream().map((student) -> mapToStudentDto(student)).collect(Collectors.toList());
	    return new PageImpl<>(studentDtos, pageable, students.getTotalElements());
	}

	@Override
	public ApiResponse updateStudent(StudentDto studentDto) {
		String userName = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		User user = userRepository.findByUserName(userName)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));
		Student student = studentRepository.findByUser(user)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found"));
		modelMapper.map(studentDto, Student.class);
		studentRepository.save(student);
		return new ApiResponse(true, "Student registered successfully");
	}

	@Override
	public StudentDto viewStudent() {
		String userName = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		User user = userRepository.findByUserName(userName)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));
		StudentDto studentDto = new StudentDto();
		studentDto.setFirstName(user.getFirstName());
		studentDto.setLastName(user.getLastName());
		studentDto.setUserName(user.getUserName());
		Student student = studentRepository.findByUser(user)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found"));
		studentDto.setEmailId(student.getEmailId());
		studentDto.setMobileNo(student.getMobileNo());
		studentDto.setCourse(student.getCourse());
		studentDto.setDateOfBirth(student.getDateOfBirth());
		studentDto.setGender(student.getGender());
		studentDto.setAddress(student.getAddress());
		return studentDto;
	}

	private StudentDto mapToStudentDto(Student student) {
		StudentDto studentDto = new StudentDto();
		User user = student.getUser();
		studentDto.setFirstName(user.getFirstName());
		studentDto.setLastName(user.getLastName());
		studentDto.setUserName(user.getUserName());
		studentDto.setEmailId(student.getEmailId());
		studentDto.setMobileNo(student.getMobileNo());
		studentDto.setCourse(student.getCourse());
		studentDto.setDateOfBirth(student.getDateOfBirth());
		studentDto.setGender(student.getGender());
		studentDto.setAddress(student.getAddress());
		try {
			studentDto.setRoomNo(student.getRoomNo());
			studentDto.setHostelName(student.getHostelName());
		} catch (NullPointerException e) {
			studentDto.setRoomNo(-1);
			studentDto.setHostelName("Not Booked");
		}
		return studentDto;
	}
	
	@Override
	public AllotmentStatus getAllotmentStatus() {
		String userName = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		User user = userRepository.findByUserName(userName)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));;
		Student student = studentRepository.findByUser(user)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found"));
		Payment payment = paymentRepository.findByStudent(student)
				.orElseThrow(() -> new ResourceNotFoundException("Payment not found"));
		AllotmentStatus allotmentStatus = new AllotmentStatus();
		if (payment != null) {
			allotmentStatus.setRoomNo(student.getRoom().getRoomNo());
			allotmentStatus.setHostelName(student.getRoom().getHostel().getName());
			allotmentStatus.setContactPerson(student.getRoom().getHostel().getContactPerson());
			allotmentStatus.setContactPhone(student.getRoom().getHostel().getContactMobileNo());
			allotmentStatus.setHostelAddress(student.getRoom().getHostel().getAddress());
			allotmentStatus.setAllotmentStatus(true);
			allotmentStatus.setPaymentStatus(payment.getTransactionStatus());
			return allotmentStatus;
		}else {
			allotmentStatus.setAllotmentStatus(false);
			allotmentStatus.setPaymentStatus(false);
			return allotmentStatus;
		}
	}
}
