package com.bi.model.location;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true) 
@Embeddable
public class PickupPointServiceProviderId implements Serializable{
	private static final long serialVersionUID = 1L;

	@Column(name = "pickup_point_id")
	private Long pickupPointId;

	@Column(name = "service_provider_id")
	private Long serviceProviderId;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		PickupPointServiceProviderId that = (PickupPointServiceProviderId) o;
		return Objects.equals(pickupPointId, that.pickupPointId)
				&& Objects.equals(serviceProviderId, that.serviceProviderId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(pickupPointId, serviceProviderId);

	}

	public PickupPointServiceProviderId() {
		super();
	}

}
