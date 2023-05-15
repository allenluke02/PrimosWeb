package com.bi.model.catering;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bi.views.CateringView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "catering_price")
@Getter
@Setter
@Accessors(chain = true)
public class CateringPrice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(CateringView.BasicView.class)
	private Long id;
	
	@JsonView(CateringView.BasicView.class)
	float serviceFee;
	
	@JsonView(CateringView.BasicView.class)
	// represents price per people
	double price;
	
	@JsonView(CateringView.BasicView.class)
	int minNoOfPeople;
	
}
