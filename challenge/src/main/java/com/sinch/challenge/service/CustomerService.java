package com.sinch.challenge.service;

import com.sinch.challenge.dto.CustomerDto;
import com.sinch.challenge.dto.SmsDto;
import com.sinch.challenge.dto.SubscribePlanDto;

public interface CustomerService {

	public CustomerDto saveCustomer(CustomerDto customerDto);

	public String sendSms(SmsDto smsSto);

	public String subscribePlan(SubscribePlanDto subscribePlanDto);

	public String getBillForCurrentMonth(long id);
}
