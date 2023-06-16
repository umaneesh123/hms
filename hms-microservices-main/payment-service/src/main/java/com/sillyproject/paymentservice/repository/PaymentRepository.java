package com.sillyproject.paymentservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sillyproject.paymentservice.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	Optional<Payment> findByCreatedBy(String username);
}
