package com.bi.model;

import java.util.Date;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import com.bi.model.location.PickupPoint;
import com.bi.views.FeedbackView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "feedbacks")
@Getter
@Setter
@Accessors(chain = true)
public class Feedback {

	@Searchable
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(FeedbackView.BasicView.class)
	private Long id;

	@Searchable
	@NotNull(message = "{err.first.name.empty}", groups = { Default.class })
	@JsonView(FeedbackView.BasicView.class)
	String firstName;

	@NotNull(message = "{err.last.name.empty}", groups = { Default.class })
	@JsonView(FeedbackView.BasicView.class)
	String lastName;

	@Searchable
	@NotNull(message = "{err.email.empty}", groups = { Default.class })
	@JsonView(FeedbackView.BasicView.class)
	String email;

	@Searchable
	@NotNull(message = "{err.phone.empty}", groups = { Default.class })
	@JsonView(FeedbackView.BasicView.class)
	String phone;

	@Enumerated(EnumType.STRING)
	@JsonView(FeedbackView.BasicView.class)
	private ContactMode contactMode;

	@Searchable
	@JsonView(FeedbackView.BasicView.class)
	@Size(max=255,message="{err.compliment.length}")
	String compliment;

	@Searchable
	@OneToOne
	@JoinColumn(name = "pickup_point_id", foreignKey = @ForeignKey(name = "fk_t_feedbacks_pickupPointId_t_pickup_point_id", value = ConstraintMode.CONSTRAINT))
	@JsonView(FeedbackView.BasicView.class)
	@NotNull(message = "{err.pickup.point.empty}", groups = { Default.class })
	private PickupPoint pickupPoint;

	@NotNull(message = "{err.posting.date.empty}", groups = { Default.class })
	@Temporal(TemporalType.TIMESTAMP)
	@JsonView(FeedbackView.BasicView.class)
	private Date postingDt;

	
//	@NotNull(message = "{err.time.period.empty}", groups = { Default.class })
//	@Enumerated(EnumType.STRING)
//	@JsonView(FeedbackView.BasicView.class)
//	private TimePeriod timePeriod;

	@Searchable
	@JsonView(FeedbackView.BasicView.class)
	@Size(max=255,message="{err.comments.length}")
	String comment;
	
	
	//@OneToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name = "city_id", foreignKey = @ForeignKey(name = "fk_t_feedbacks_cityId_t_city_id", value = ConstraintMode.CONSTRAINT))
	@JsonView(FeedbackView.BasicView.class)
    private String city;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id", foreignKey = @ForeignKey(name = "fk_t_feedbacks_stateId_t_state_id", value = ConstraintMode.CONSTRAINT))
	@JsonView(FeedbackView.BasicView.class)
	private State state;

	@JsonView(FeedbackView.BasicView.class)
	String zip;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_concern_id", foreignKey = @ForeignKey(name = "fk_t_feedbacks_productConcernId_t_product_concern_id", value = ConstraintMode.CONSTRAINT))
	@JsonView(FeedbackView.BasicView.class)
	private ProductConcern productConcern;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "service_concern_id", foreignKey = @ForeignKey(name = "fk_t_feedbacks_serviceConcernId_t_service_concern_id", value = ConstraintMode.CONSTRAINT))
	@JsonView(FeedbackView.BasicView.class)
	private ServiceConcern serviceConcern;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "health_concern_id", foreignKey = @ForeignKey(name = "fk_t_feedbacks_healthConcernId_t_health_concern_id", value = ConstraintMode.CONSTRAINT))
	@JsonView(FeedbackView.BasicView.class)
	private HealthConcern healthConcern;

	@JsonView(FeedbackView.BasicView.class)
	String mailingAddressLine1;
	
	@JsonView(FeedbackView.BasicView.class)
	String mailingAddressLine2;
	
	@Enumerated(EnumType.STRING)
	@JsonView(FeedbackView.BasicView.class)
	private VisitType visitType;
	
	@JsonView(FeedbackView.BasicView.class)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate  = new Date();
}
