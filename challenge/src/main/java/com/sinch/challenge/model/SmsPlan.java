package com.sinch.challenge.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name = "sms_plan")
@Data
public class SmsPlan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String planName;

	private long freeSms;

	private double price;

	private String currency;

	@Column(columnDefinition = "int default 0")
	private int isDeleted;

	@Column(name = "created_at", nullable = false)
	@CreationTimestamp
	private Date createdAt;

	@Column(name = "updated_at", nullable = false)
	@UpdateTimestamp
	private Date updatedAt;

}
