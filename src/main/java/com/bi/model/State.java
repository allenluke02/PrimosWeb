package com.bi.model;

import java.io.Serializable;

import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.groups.Default;

import com.bi.views.CareerView;
import com.bi.views.CateringView;
import com.bi.views.FeedbackView;
import com.bi.views.FranchiseView;
import com.bi.views.JobView;
import com.bi.views.PickupPointView;
import com.bi.views.SiteSubmitterView;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "state")
@Getter
@Setter
@Accessors(chain = true)
public class State implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8782781612651555324L;

	@Id
	@JsonView({FeedbackView.BasicView.class,PickupPointView.BasicView.class,
		JobView.BasicView.class,FranchiseView.BasicView.class,SiteSubmitterView.BasicView.class,CareerView.BasicView.class,CateringView.BasicView.class})
	private Long id;
	
	@JsonView({FeedbackView.BasicView.class,PickupPointView.BasicView.class,JobView.BasicView.class,
		FranchiseView.BasicView.class,SiteSubmitterView.BasicView.class,CareerView.BasicView.class,CateringView.BasicView.class})
	@NotBlank(message = "{err.not.blank}", groups = { Default.class })
	@Searchable
	String name;
	
	// For State Name : California and code : CA
	@JsonView({FeedbackView.BasicView.class,PickupPointView.BasicView.class,
		JobView.BasicView.class,FranchiseView.BasicView.class,SiteSubmitterView.BasicView.class,CareerView.BasicView.class})
	@NotBlank(message = "{err.not.blank}", groups = { Default.class })
	@Searchable
	String stateCode;
	
	@ManyToOne
	@JoinColumn(name = "country_id", foreignKey = @ForeignKey(name = "fk_t_country_stateId_t_state_id", value = ConstraintMode.CONSTRAINT))
	//@NotNull(message = "{err.country.not.exists}", groups = { Default.class })
	private Country country;
}
