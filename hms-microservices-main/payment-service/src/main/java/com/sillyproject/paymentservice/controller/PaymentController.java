package com.sillyproject.paymentservice.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sillyproject.paymentservice.dto.PaymentDto;
import com.sillyproject.paymentservice.payload.ApiResponse;
import com.sillyproject.paymentservice.service.IPaymentService;

import lombok.RequiredArgsConstructor;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value="/admin")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class PaymentController {
	
	private IPaymentService paymentService;
	
	@PostMapping("/make-payment")
	public ResponseEntity<ApiResponse> makePayment(@RequestParam String transactionId) {
		return ResponseEntity.ok().body(paymentService.makePayment(transactionId));
	}
	
	@GetMapping("/payment/get-all-payments")
    public ResponseEntity<Page<PaymentDto>> getTransactions(Pageable pageable) {		
    	return ResponseEntity.ok().body(paymentService.getTransactions(pageable)); 
    }	
}
