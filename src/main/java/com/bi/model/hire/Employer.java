package com.bi.model.hire;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.bi.views.CareerView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "employers")
@Getter
@Setter
@Accessors(chain = true)
public class Employer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(CareerView.BasicView.class)
	private Long id;

	@JsonView(CareerView.BasicView.class)
	String name;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonView(CareerView.BasicView.class)
	private Date startDate;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonView(CareerView.BasicView.class)
	private Date endDate;
	
	@JsonView(CareerView.BasicView.class)
	String phone;
	
	@JsonView(CareerView.BasicView.class)
	String reason;
	
	@JsonView(CareerView.BasicView.class)
	Boolean isApplicable;
	
}
