package com.sillyproject.userservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sillyproject.userservice.model.Admin;
import com.sillyproject.userservice.model.User;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
	Optional<Admin> findByUser(User user);	
}

