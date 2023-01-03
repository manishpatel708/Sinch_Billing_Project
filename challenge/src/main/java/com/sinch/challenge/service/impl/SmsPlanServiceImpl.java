package com.sinch.challenge.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinch.challenge.SmsPlanRepository;
import com.sinch.challenge.constants.Constant;
import com.sinch.challenge.dto.SmsPlanDto;
import com.sinch.challenge.model.SmsPlan;
import com.sinch.challenge.service.SmsPlanService;

@Service
@Transactional
public class SmsPlanServiceImpl implements SmsPlanService {

	@Autowired
	SmsPlanRepository smsPlanRepository;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public String createSmsPlan(SmsPlanDto dto) {
		SmsPlan smsPlan = modelMapper.map(dto, SmsPlan.class);
		smsPlanRepository.save(smsPlan);
		return Constant.SMS_PLAN_CREATED;
	}

}
