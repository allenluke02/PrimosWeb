package com.bi.model.franchise;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import com.bi.model.Searchable;
import com.bi.model.State;
import com.bi.model.Document.DocumentType;
import com.bi.views.SiteSubmitterView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "site_submitters")
@Getter
@Setter
@Accessors(chain = true)
public class SiteSubmitter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(SiteSubmitterView.BasicView.class)
	private Long id;
	
	@Searchable
	@NotBlank(message = "{err.first.name.blank}", groups = { Default.class })
	@JsonView(SiteSubmitterView.BasicView.class)
	String firstName;

	@NotBlank(message = "{err.last.name.blank}", groups = { Default.class })
	@JsonView(SiteSubmitterView.BasicView.class)
	String lastName;
	
	@Searchable
	@JsonView(SiteSubmitterView.BasicView.class)
	String company;
	
	@JsonView(SiteSubmitterView.BasicView.class)
	@NotBlank(message = "{err.mailing.address.line1.blank}", groups = { Default.class })
	String mailingAddressLine1;
	
	@JsonView(SiteSubmitterView.BasicView.class)
	String mailingAddressLine2;
	
	
	@Searchable
	@JsonView(SiteSubmitterView.BasicView.class)
	@NotBlank(message = "{err.city.blank}", groups = { Default.class })
	String city;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id", foreignKey = @ForeignKey(name = "fk_t_site_submitters_stateId_t_state_id", value = ConstraintMode.CONSTRAINT))
	@JsonView(SiteSubmitterView.BasicView.class)
	private State state;
	
	@NotBlank(message = "{err.zip.blank}", groups = { Default.class })
	@JsonView(SiteSubmitterView.BasicView.class)
	String zip;
	
	@Searchable
	@NotNull(message = "{err.email.empty}", groups = { Default.class })
	@JsonView(SiteSubmitterView.BasicView.class)
	String email;
	
	@NotNull(message = "{err.fax.empty}", groups = { Default.class })
	@JsonView(SiteSubmitterView.BasicView.class)
	String fax;
	
	@Searchable
	@NotNull(message = "{err.phone.empty}", groups = { Default.class })
	@JsonView(SiteSubmitterView.BasicView.class)
	String officePhone;
	
	@Searchable
	//@NotNull(message = "{err.phone.empty}", groups = { Default.class })
	@JsonView(SiteSubmitterView.BasicView.class)
	String mobilePhone;
	
	
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "site_information_id", foreignKey = @ForeignKey(name = "fk_t_site_submitters_siteInformationId_t_site_information_id", value = ConstraintMode.CONSTRAINT))
	@JsonView(SiteSubmitterView.BasicView.class)
	private SiteInformation siteInformation;
	
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "site_type_id", foreignKey = @ForeignKey(name = "fk_t_site_submitters_siteTypeId_t_site_type_id", value = ConstraintMode.CONSTRAINT))
	@JsonView(SiteSubmitterView.BasicView.class)
	private SiteType siteType;
	
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "site_submittal_id", foreignKey = @ForeignKey(name = "fk_t_site_submitters_siteSubmittalId_t_site_submittal_id", value = ConstraintMode.CONSTRAINT))
	@JsonView(SiteSubmitterView.BasicView.class)
	private SiteSubmittal siteSubmittal;
	
	@JsonView(SiteSubmitterView.BasicView.class)
	@Enumerated(EnumType.STRING)
	DocumentType documentType;

	// resume url
	@JsonView(SiteSubmitterView.BasicView.class)
	String docUrl;
	
	@JsonView(SiteSubmitterView.BasicView.class)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate = new Date();
	
	@Transient
	String reCaptcha;
	
}
