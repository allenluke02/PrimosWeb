package com.bi.model.franchise;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import com.bi.views.SiteSubmitterView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "site_type")
@Getter
@Setter
@Accessors(chain = true)
public class SiteType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(SiteSubmitterView.BasicView.class)
	private Long id;
	
	@JsonView(SiteSubmitterView.BasicView.class)
	Boolean endCap;
	
	@JsonView(SiteSubmitterView.BasicView.class)
	Boolean inLine;
	
	@JsonView(SiteSubmitterView.BasicView.class)
	Boolean isPadSite;
	
	@JsonView(SiteSubmitterView.BasicView.class)
	Boolean hasFreeStandingWithDriveThru;
	
	@NotNull(message = "{err.phone.empty}", groups = { Default.class })
	@JsonView(SiteSubmitterView.BasicView.class)
	String officePhone;
	
	

}
