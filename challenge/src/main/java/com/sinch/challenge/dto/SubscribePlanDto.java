package com.sinch.challenge.dto;

import org.springframework.lang.NonNull;

import lombok.Data;

@Data
public class SubscribePlanDto {

	@NonNull
	private long customerId;

	@NonNull
	private long smsPlanId;
}
