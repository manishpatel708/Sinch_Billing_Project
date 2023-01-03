package com.sinch.challenge.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import org.apache.tomcat.util.bcel.Const;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinch.challenge.SmsPlanRepository;
import com.sinch.challenge.constants.Constant;
import com.sinch.challenge.dto.CustomerDto;
import com.sinch.challenge.dto.SmsDto;
import com.sinch.challenge.dto.SubscribePlanDto;
import com.sinch.challenge.model.Customer;
import com.sinch.challenge.model.SmsPlan;
import com.sinch.challenge.model.SmsTransaction;
import com.sinch.challenge.repository.CustomerRepository;
import com.sinch.challenge.repository.SmsTransactionRepository;
import com.sinch.challenge.service.CustomerService;
import com.sinch.challenge.util.UtilData;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	SmsTransactionRepository smsTransactionRepository;

	@Autowired
	SmsPlanRepository smsPlanRepository;

	@Override
	public CustomerDto saveCustomer(CustomerDto customerDto) {
		Customer customer = modelMapper.map(customerDto, Customer.class);
		Customer savedCustomer = customerRepository.save(customer);
		return modelMapper.map(savedCustomer, CustomerDto.class);
	}

	@Override
	public String subscribePlan(SubscribePlanDto subscribePlanDto) {
		Optional<Customer> optional = customerRepository.findById(subscribePlanDto.getCustomerId());
		if (optional.isPresent()) {
			Customer customer = optional.get();
			Optional<SmsPlan> smsPlan = smsPlanRepository.findById(subscribePlanDto.getSmsPlanId());
			if (smsPlan.isPresent()) {
				customer.setSmsPlan(smsPlan.get());
			} else {
				return Constant.INVALID_SMS_PLAN;
			}
			customerRepository.save(customer);
			return Constant.PLAN_SUBSCRIBED;
		}
		return Constant.SOMETHING_WENT_WRONG;
	}

	@Override
	public String sendSms(SmsDto smsSto) {
		Optional<Customer> optional = customerRepository.findById(smsSto.getCustomerId());
		if (optional.isPresent()) {
			Customer customer = optional.get();
			SmsTransaction smsTransaction = SmsTransaction.builder().customerId(customer.getId())
					.phoneNo(customer.getPhoneNo()).isDelivered(1).build();

			smsTransactionRepository.save(smsTransaction);
			return Constant.SMS_SENT_SUCCESSFULLY;
		}
		return Constant.SOMETHING_WENT_WRONG;
	}

	@Override
	public String getBillForCurrentMonth(long id) {
		Date firstDate = UtilData.getSqlDateFromLocalDate(LocalDate.now().withDayOfMonth(1));
		Date lastDate = UtilData.getSqlDateFromLocalDate(LocalDate.now().plusMonths(1).withDayOfMonth(1).minusDays(1));

		Optional<Customer> optional = customerRepository.findById(id);
		if (optional.isPresent()) {
			Customer customer = optional.get();
			long totalSentSmsForMonth = customerRepository.getSentSmsForCustomer(id, firstDate, lastDate);
			return calculateDynamicBillForCustomer(customer, totalSentSmsForMonth);
		}
		return Constant.SOMETHING_WENT_WRONG;
	}

	private String calculateDynamicBillForCustomer(Customer customer, long totalSentSmsForMonth) {
		double billAmount = 0;
		if (customer.getSmsPlan().getPlanName().equalsIgnoreCase("Basic")) {
			billAmount = totalSentSmsForMonth * customer.getSmsPlan().getPrice();
			return Constant.BILLING_MESSAGE + billAmount + " " + customer.getSmsPlan().getCurrency();
		} else if (customer.getSmsPlan().getPlanName().equalsIgnoreCase("Silver")) {
			if (customer.getSmsPlan().getFreeSms() < totalSentSmsForMonth) {
				billAmount = (totalSentSmsForMonth - customer.getSmsPlan().getFreeSms())
						* customer.getSmsPlan().getPrice();
				return Constant.BILLING_MESSAGE + billAmount + " " + customer.getSmsPlan().getCurrency();
			}
		} else {
			if (customer.getSmsPlan().getFreeSms() < totalSentSmsForMonth) {
				billAmount = (totalSentSmsForMonth - customer.getSmsPlan().getFreeSms())
						* customer.getSmsPlan().getPrice();
				if (totalSentSmsForMonth > 100000) {
					double discountedBill = (totalSentSmsForMonth - 100000) * 0.0005;
					double regularBill = 100000 * customer.getSmsPlan().getPrice();
					double discountedPrice = billAmount - (regularBill + discountedBill);
					billAmount = regularBill + discountedBill;
					return Constant.BILLING_MESSAGE + billAmount + " " + customer.getSmsPlan().getCurrency()
							+ " : With discount of " + discountedPrice + customer.getSmsPlan().getCurrency();
				}
				return Constant.BILLING_MESSAGE + billAmount + " " + customer.getSmsPlan().getCurrency();
			}
		}
		return Constant.SOMETHING_WENT_WRONG;
	}

}
