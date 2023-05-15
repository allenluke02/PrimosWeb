package com.bi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bi.exception.EntityNotFoundException;
import com.bi.model.location.PickupHours;
import com.bi.repositories.PickupHourRepository;

@Service
public class PickupHourServiceImpl implements PickupHourService{
	
	@Autowired
	PickupHourRepository pickupHourRepository;
	
	@Autowired
	private MessageByLocaleService messageByLocaleService;

	@Override
	public PickupHours createPickupHour(PickupHours pickupHours) {
		return pickupHourRepository.save(pickupHours);
	}

	@Override
	public List<PickupHours> getAllPickupHours() {
		return pickupHourRepository.findAll();
	}

	@Override
	public PickupHours getPickupHourById(Long id) {
		return pickupHourRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
				messageByLocaleService.getMessage("err.pickup.hours.not.found", id.toString())));
	}

	@Override
	public PickupHours update(PickupHours pickupHours) {
		return pickupHourRepository.save(pickupHours);
	}

	@Override
	public void deletePickupHourById(Long id) {
		PickupHours pickupHours = getPickupHourById(id);
		pickupHourRepository.delete(pickupHours);
	}

}
