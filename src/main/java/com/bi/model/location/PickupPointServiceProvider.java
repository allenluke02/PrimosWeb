package com.bi.model.location;

import javax.persistence.ConstraintMode;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.bi.views.PickupPointView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "pickup_points_service_providers")
@Getter
@Setter
@Accessors(chain = true)
public class PickupPointServiceProvider {

	@EmbeddedId
	PickupPointServiceProviderId pickupPointServiceProviderId;

	@ManyToOne()
	@MapsId("pickupPointId")
	@JoinColumn(name = "pickup_point_id", foreignKey = @ForeignKey(name = "fk_t_pickup_pts_sp_pickupPointId_t_pickup_point_id", value = ConstraintMode.CONSTRAINT),insertable = false,updatable = false)
	PickupPoint pickupPoint;

	@ManyToOne()
	@MapsId("serviceProviderId")
	@JoinColumn(name = "service_provider_id", foreignKey = @ForeignKey(name = "fk_t_pickup_pts_sp_serviceProviderId_t_service_provider_id", value = ConstraintMode.CONSTRAINT),insertable = false,updatable = false)
	@JsonView(PickupPointView.BasicView.class)
	ServiceProvider serviceProvider;

	@JsonView(PickupPointView.BasicView.class)
	String pickupProviderUrl;

}
