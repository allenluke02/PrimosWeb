package com.bi.model.franchise;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bi.views.SiteSubmitterView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "site_submittal")
@Getter
@Setter
@Accessors(chain = true)
public class SiteSubmittal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(SiteSubmitterView.BasicView.class)
	private Long id;
	
	@JsonView(SiteSubmitterView.BasicView.class)
	String lotSize;
	
	@JsonView(SiteSubmitterView.BasicView.class)
	String buildingSize;
	
	@JsonView(SiteSubmitterView.BasicView.class)
	String dimension;
	
	@JsonView(SiteSubmitterView.BasicView.class)
	Boolean isLease;
	
	@JsonView(SiteSubmitterView.BasicView.class)
	Boolean isPurchase;
	
	@JsonView(SiteSubmitterView.BasicView.class)
	String askingRent;
	
	@JsonView(SiteSubmitterView.BasicView.class)
	String askingPrice;

}
