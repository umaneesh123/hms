package com.sillyproject.userservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sillyproject.userservice.model.Student;
import com.sillyproject.userservice.model.User;

public interface StudentRepository extends JpaRepository<Student, Integer>{
	Optional<Student> findByUser(User user);
}
