package com.sinch.challenge.dto;

import lombok.Data;

@Data
public class SmsPlanDto {

	private String planName;

	private long freeSms;

	private double price;

	private String currency;
}
