package com.bi.model.hire;

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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import com.bi.model.Searchable;
import com.bi.model.Document.DocumentType;
import com.bi.model.location.County;
import com.bi.model.location.PickupPoint;
import com.bi.views.CareerView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
//@Table(name = "careers", uniqueConstraints = { @UniqueConstraint(columnNames = { "email", "county_id", "pickup_point_id",
//		"job_id" }, name = "fp_careers_email_county_id_pickup_point_id_unique") })
@Table(name = "careers")
@Getter
@Setter
@Accessors(chain = true)
public class Career {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(CareerView.BasicView.class)
	private Long id;

	@Searchable
	@NotBlank(message = "{err.first.name.blank}", groups = { Default.class })
	@JsonView(CareerView.BasicView.class)
	String firstName;

	@NotBlank(message = "{err.last.name.blank}", groups = { Default.class })
	@JsonView(CareerView.BasicView.class)
	String lastName;

	@Email(message = "{err.invalid.email}", groups = { Default.class })
	//@NotBlank(message = "{err.not.blank}", groups = { Default.class })
	@JsonView(CareerView.BasicView.class)
	String email;

	@Searchable
	@NotBlank(message = "{err.phone.blank}", groups = { Default.class })
	@JsonView(CareerView.BasicView.class)
	String phone;

	@Searchable
	/*
	 * @NotBlank(message = "{err.mailing.address.blank}", groups = { Default.class
	 * })
	 */
	@JsonView(CareerView.BasicView.class)
	String mailingAddress;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "county_id", foreignKey = @ForeignKey(name = "fk_t_careers_countyId_t_county_id", value = ConstraintMode.CONSTRAINT))
	//@NotNull(message = "{err.county.not.exists}", groups = { Default.class })
	@JsonView(CareerView.BasicView.class)
	private County county;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pickup_point_id", foreignKey = @ForeignKey(name = "fk_t_careers_pickupPointId_t_pickup_point_id", value = ConstraintMode.CONSTRAINT))
	//@NotNull(message = "{err.pickup.point.not.exists}", groups = { Default.class })
	@JsonView(CareerView.BasicView.class)
	private PickupPoint pickupPoint;

	@Searchable
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "job_id", foreignKey = @ForeignKey(name = "fk_t_careers_jobId_t_job_id", value = ConstraintMode.CONSTRAINT))
	@NotNull(message = "{err.job.not.exists}", groups = { Default.class })
	@JsonView(CareerView.BasicView.class)
	private Job job;
	
	
	//Represents Recent Employer
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "employer_id", foreignKey = @ForeignKey(name = "fk_t_careers_employerId_t_employer_id", value = ConstraintMode.CONSTRAINT))
	//@NotNull(message = "{err.employer.not.exists}", groups = { Default.class })
	@JsonView(CareerView.BasicView.class)
	private Employer employer;
	
	@JsonView(CareerView.BasicView.class)
	@Enumerated(EnumType.STRING)
	DocumentType documentType;

	// resume url
	@JsonView(CareerView.BasicView.class)
	String docUrl;
	
	/**
	 * Are you legally able to work in the United States?
	 */
//	@JsonView(CareerView.BasicView.class)
//	boolean isLegal;
//	
	@JsonView(CareerView.BasicView.class)
	@Size(max=255,message="{err.comments.length}")
	String comments;
	
	@Searchable
	@JsonView(CareerView.BasicView.class)
	String postalAddress;
	
	@JsonView(CareerView.BasicView.class)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate  = new Date();

}
