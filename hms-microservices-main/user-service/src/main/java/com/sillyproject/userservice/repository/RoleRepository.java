package com.sillyproject.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sillyproject.userservice.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	Role findByName(String roleName);
}
