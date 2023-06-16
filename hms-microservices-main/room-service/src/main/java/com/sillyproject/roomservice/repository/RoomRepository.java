package com.sillyproject.roomservice.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sillyproject.roomservice.model.Hostel;
import com.sillyproject.roomservice.model.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {
	Page<Room> findAllRoomsByHostel(Hostel hostel, Pageable pageable);
	Optional<Room> findByUsername(String username);
}
