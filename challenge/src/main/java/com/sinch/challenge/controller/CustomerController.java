package com.sinch.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinch.challenge.dto.CustomerDto;
import com.sinch.challenge.dto.SmsDto;
import com.sinch.challenge.dto.SubscribePlanDto;
import com.sinch.challenge.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@PostMapping("/save")
	public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
		return new ResponseEntity<>(customerService.saveCustomer(customerDto), HttpStatus.CREATED);
	}

	@PostMapping("/smsPlan/subscribe")
	public ResponseEntity<String> subscribeSmsPlan(@RequestBody SubscribePlanDto subscribePlanDto) {
		return new ResponseEntity<>(customerService.subscribePlan(subscribePlanDto), HttpStatus.OK);
	}

	@PostMapping("/sendSms")
	public ResponseEntity<String> sendSmsCustomer(@RequestBody SmsDto smsSto) {
		return new ResponseEntity<>(customerService.sendSms(smsSto), HttpStatus.OK);
	}

	@GetMapping("/getBill/{id}")
	public ResponseEntity<String> getBillForCurrentMonth(@PathVariable long id) {
		return new ResponseEntity<>(customerService.getBillForCurrentMonth(id), HttpStatus.OK);
	}
}
