package com.sinch.challenge.dto;

import org.springframework.lang.NonNull;

import lombok.Data;

@Data
public class SmsDto {

	private long customerId;

	@NonNull
	private String phoneNo;

	@NonNull
	private String message;
}
