package com.sillyproject.paymentservice.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.sillyproject.paymentservice.dto.PaymentDto;
import com.sillyproject.paymentservice.model.Payment;
import com.sillyproject.paymentservice.payload.ApiResponse;
import com.sillyproject.paymentservice.payload.RoomDetails;
import com.sillyproject.paymentservice.repository.PaymentRepository;
import com.sillyproject.paymentservice.service.IPaymentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements IPaymentService {

	private PaymentRepository paymentRepository;
	private WebClient webClient;

	@Override
	public ApiResponse makePayment(String transactionId) {
		Payment payment = new Payment();
		payment.setTransactionId(transactionId);
		payment.setTransactionDate(LocalDateTime.now());

		RoomDetails roomDetails = webClient.get()
				.uri("")
				.retrieve()
				.bodyToMono(RoomDetails.class)
				.block();

		payment.setRoomNo(roomDetails.getRoomNo());
		payment.setHostelName(roomDetails.getHostelName());

		if (transactionId != null) {
			payment.setTransactionStatus(true);
			paymentRepository.save(payment);
			return new ApiResponse(true, "Payment was successfully completed");
		} else {
			payment.setTransactionStatus(false);
			paymentRepository.save(payment);
			return new ApiResponse(false, "Payment failed. Please try again");
		}
	}

	@Override
	public Page<PaymentDto> getTransactions(Pageable pageable) {
		Page<Payment> payments = paymentRepository.findAll(pageable);
		List<PaymentDto> paymentDtos = payments.stream().map((payment) -> mapToPaymentDto(payment))
				.collect(Collectors.toList());
		return new PageImpl<>(paymentDtos, pageable, payments.getTotalElements());
	}

	private PaymentDto mapToPaymentDto(Payment payment) {
		PaymentDto paymentDto = new PaymentDto();
		paymentDto.setId(payment.getId());
		paymentDto.setTransactionId(payment.getTransactionId());
		paymentDto.setStudentName(payment.getCreatedBy());
		paymentDto.setHostelName(payment.getHostelName());
		paymentDto.setRoomNo(payment.getRoomNo());
		paymentDto.setTransactionDate(LocalDateTime.now());
		paymentDto.setTransactionStatus(payment.getTransactionStatus());
		return paymentDto;
	}
}
