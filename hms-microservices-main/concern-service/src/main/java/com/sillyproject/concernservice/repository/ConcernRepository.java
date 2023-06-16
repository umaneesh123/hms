package com.sillyproject.concernservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sillyproject.concernservice.model.Concern;

public interface ConcernRepository extends JpaRepository<Concern, Integer> {
	Page<Concern> findAllByCreatedBy(String createdBy, Pageable pageable);
}
