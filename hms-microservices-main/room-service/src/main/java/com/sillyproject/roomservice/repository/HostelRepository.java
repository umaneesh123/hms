package com.sillyproject.roomservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sillyproject.roomservice.model.Hostel;

public interface HostelRepository extends JpaRepository<Hostel, Integer> {
}
