package com.sillyproject.paymentservice.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {		
	private int id;
	private String transactionId;
	private String studentName;
	private String hostelName;
	private int roomNo;
	private boolean transactionStatus;
	private LocalDateTime transactionDate;	
}
