package com.bi.model.hire;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import com.bi.model.Searchable;
import com.bi.model.location.PickupPoint;
import com.bi.views.CareerView;
import com.bi.views.JobView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "jobs", uniqueConstraints = { @UniqueConstraint(columnNames = {"title"},name="fp_jobs_title_unique")})
@Getter
@Setter
@Accessors(chain = true)
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({JobView.BasicView.class,CareerView.BasicView.class})
	private Long id;

	@Searchable
	@JsonView({JobView.BasicView.class,CareerView.BasicView.class})
	@NotBlank(message = "{err.not.blank}", groups = { Default.class })
	String title;

	@JsonView(JobView.BasicView.class)
	@Searchable
	String companyName;
	
	@JsonView(JobView.BasicView.class)
	String description;
	
	@JsonView(JobView.BasicView.class)
	String imageUrl;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinTable(name = "jobs_pickup_points", 
			uniqueConstraints = {
		            @UniqueConstraint(columnNames = {"pickup_point_id", "jobs_id"}, name = "pickup_point_id_jobs_id")},
			joinColumns = {	@JoinColumn(name = "jobs_id",nullable=false,foreignKey = @ForeignKey(name = "fk_t_pickup_points_jobs_id_t_jobsId", value = ConstraintMode.CONSTRAINT)) }, 
			inverseJoinColumns = {@JoinColumn(name = "pickup_point_id",nullable=false,foreignKey = @ForeignKey(name = "fk_t_pickup_points_pickup_point_id_t_pickupPointId", value = ConstraintMode.CONSTRAINT))  })
	@JsonView(JobView.BasicView.class)
	@NotNull(message = "{err.pickup.point.not.exists}", groups = { Default.class })
	@Searchable
	private Set<PickupPoint> pickupPoint;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonView({JobView.BasicView.class,CareerView.BasicView.class})
	private Date postingDt;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "job_type_id", foreignKey = @ForeignKey(name = "fk_t_jobs_jobTypeId_t_job_type_id", value = ConstraintMode.CONSTRAINT))
	@JsonView({JobView.BasicView.class,CareerView.BasicView.class})
	@Searchable
	private JobType jobType;

	@JsonView(JobView.BasicView.class)
	boolean isExpired;
}
