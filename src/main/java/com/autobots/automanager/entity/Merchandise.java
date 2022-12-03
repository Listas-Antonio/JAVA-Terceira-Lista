package com.autobots.automanager.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.Data;


@Data
@Entity
public class Merchandise {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private Date expiration;
	@Column(nullable = false)
	private Date manufacturing;
	@Column(nullable = false)
	private Date registration;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private long quantity;
	@Column(nullable = false)
	private double value;
	@Column()
	private String description;
}