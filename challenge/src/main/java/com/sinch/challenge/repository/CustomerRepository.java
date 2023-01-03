package com.sinch.challenge.repository;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sinch.challenge.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	@Query(value = "select count(id) from demo.sms_transaction where customer_id  = ?1 and is_delivered = 1 and created_at between ?2 and ?3", nativeQuery = true)
	long getSentSmsForCustomer(long customerId, Date startDate, Date endDate);
}
