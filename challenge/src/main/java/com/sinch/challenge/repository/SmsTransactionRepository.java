package com.sinch.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sinch.challenge.model.SmsTransaction;

@Repository
public interface SmsTransactionRepository extends JpaRepository<SmsTransaction, Long> {

}
