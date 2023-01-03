package com.sinch.challenge.dto;

import org.springframework.lang.NonNull;

import com.sinch.challenge.model.SmsPlan;

import lombok.Data;

@Data
public class CustomerDto {

	@NonNull
	private String name;

	@NonNull
	private String phoneNo;

	@NonNull
	private String email;

	private SmsPlan smsPlan;

	public CustomerDto() {
	}
}
