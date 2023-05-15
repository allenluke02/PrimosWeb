package com.bi.model.location;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.groups.Default;

import com.bi.views.PickupPointView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


////ex: UberEats, DoorDash
@Entity
@Table(name = "service_providers", uniqueConstraints = { @UniqueConstraint(columnNames = {"name"},name="fp_service_providers_name_unique")})
@Getter
@Setter
@Accessors(chain = true)
public class ServiceProvider {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(PickupPointView.BasicView.class)
	private Long id;
	
	@JsonView(PickupPointView.BasicView.class)
	@NotBlank(message = "{err.not.blank}", groups = { Default.class })
	String name;
	
	@JsonView(PickupPointView.BasicView.class)
	String imageUrl;
	
	@OneToMany(mappedBy = "serviceProvider",  cascade = CascadeType.ALL)
    private Set<PickupPointServiceProvider> pickupPtServiceProviders;

	
	
}
