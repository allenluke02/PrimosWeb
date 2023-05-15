package com.bi.services;

import java.util.List;

import com.bi.model.location.PickupHours;

public interface PickupHourService {

	PickupHours createPickupHour(PickupHours pickupHours);

	List<PickupHours> getAllPickupHours();

	PickupHours getPickupHourById(Long id);

	PickupHours update(PickupHours pickupHours);

	void deletePickupHourById(Long id);

}
