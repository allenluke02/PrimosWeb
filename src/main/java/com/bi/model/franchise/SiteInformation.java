package com.bi.model.franchise;

import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import com.bi.model.State;
import com.bi.views.SiteSubmitterView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "site_information")
@Getter
@Setter
@Accessors(chain = true)
public class SiteInformation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(SiteSubmitterView.BasicView.class)
	private Long id;
	

	@NotBlank(message = "{err.mailing.address.line1.blank}", groups = { Default.class })
	@JsonView(SiteSubmitterView.BasicView.class)
	String mailingAddressLine1;
	
	@JsonView(SiteSubmitterView.BasicView.class)
	String mailingAddressLine2;
	
	@NotBlank(message = "{err.city.blank}", groups = { Default.class })
	@JsonView(SiteSubmitterView.BasicView.class)
	String city;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id", foreignKey = @ForeignKey(name = "fk_t_site_information_stateId_t_state_id", value = ConstraintMode.CONSTRAINT))
	@JsonView(SiteSubmitterView.BasicView.class)
	private State state;
	
	@NotBlank(message = "{err.zip.blank}", groups = { Default.class })
	@JsonView(SiteSubmitterView.BasicView.class)
	String zip;
	
	@NotNull(message = "{err.phone.empty}", groups = { Default.class })
	@JsonView(SiteSubmitterView.BasicView.class)
	String officePhone;
}
