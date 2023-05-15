package com.bi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.groups.Default;

import com.bi.views.CareerView;
import com.bi.views.FeedbackView;
import com.bi.views.FranchiseView;
import com.bi.views.JobView;
import com.bi.views.PickupPointView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "country", uniqueConstraints = { @UniqueConstraint(columnNames = {"name","countryIATACode"},name="country_name_country_code_name_countryCode_unique")})
@Getter
@Setter
@Accessors(chain = true)
public class Country {

	@Id
	@JsonView({PickupPointView.BasicView.class,JobView.BasicView.class,FranchiseView.BasicView.class,FeedbackView.BasicView.class,CareerView.BasicView.class})
	private Long id;

	@JsonView({PickupPointView.BasicView.class,JobView.BasicView.class,FranchiseView.BasicView.class,FeedbackView.BasicView.class,CareerView.BasicView.class})
	@NotBlank(message = "{err.not.blank}", groups = { Default.class })
	String name;

	@JsonView({PickupPointView.BasicView.class,JobView.BasicView.class,FranchiseView.BasicView.class,FeedbackView.BasicView.class,CareerView.BasicView.class})
	String countryIATACode;

}
