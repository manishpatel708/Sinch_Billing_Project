package com.sinch.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinch.challenge.dto.SmsPlanDto;
import com.sinch.challenge.service.SmsPlanService;

@RestController
@RequestMapping("/api/sms/plan")
public class SmsPlanController {

	@Autowired
	SmsPlanService smsPlanService;

	@PostMapping("/save")
	public ResponseEntity<String> createSmsPlan(@RequestBody SmsPlanDto smsPlanDto) {
		return new ResponseEntity<>(smsPlanService.createSmsPlan(smsPlanDto), HttpStatus.CREATED);
	}

}
