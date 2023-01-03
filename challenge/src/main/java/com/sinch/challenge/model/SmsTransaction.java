package com.sinch.challenge.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Table
@Builder
public class SmsTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private long customerId;

	private String phoneNo;

	@Column(columnDefinition = "int default 0")
	private int isDelivered;

	@CreationTimestamp
	private Date createdAt;

}
