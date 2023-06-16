package com.sillyproject.paymentservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sillyproject.paymentservice.dto.PaymentDto;
import com.sillyproject.paymentservice.payload.ApiResponse;

public interface IPaymentService {
	ApiResponse makePayment(String transactionId);
	Page<PaymentDto> getTransactions(Pageable pageable);
}
