package com.bi.model.location;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.groups.Default;

import com.bi.views.PickupPointView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "pickup_hours", uniqueConstraints = { @UniqueConstraint(columnNames = {"name"},name="fp_pickup_hours_name_unique")})
@Getter
@Setter
@Accessors(chain = true)
public class PickupHours {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(PickupPointView.BasicView.class)
	private Long id;
	
	//ex: 7am - 10pm Daily
	@JsonView(PickupPointView.BasicView.class)
	@NotBlank(message = "{err.not.blank}", groups = { Default.class })
	String name;
	

	
	
}
