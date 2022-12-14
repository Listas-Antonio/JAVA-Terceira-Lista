package com.autobots.automanager.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.hateoas.RepresentationModel;

import com.autobots.automanager.enumerators.DocumentType;

import lombok.Data;

@Data
@Entity
public class Document extends RepresentationModel<Document>{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private Date emissionDate;
	@Column(unique = true)
	private String number;
	@Column(nullable = false)
	private DocumentType documentType;

}