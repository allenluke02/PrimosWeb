package com.bi.services;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bi.exception.EntityNotFoundException;
import com.bi.model.franchise.RestaurantsCapacity;
import com.bi.repositories.RestaurantsCapacityRepository;

@Service
public class RestaurantsCapacityServiceImpl implements RestaurantsCapacityService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantsCapacityServiceImpl.class);

	@Autowired
	private RestaurantsCapacityRepository restaurantsCapacityRepository;

	@Autowired
	private MessageByLocaleService messageByLocaleService;

	@Override
	public RestaurantsCapacity createRestaurantCapacity(@Valid RestaurantsCapacity restaurantCapacity) {
		return restaurantsCapacityRepository.save(restaurantCapacity);
	}

	@Override
	public void deleteRestaurantCapacityById(Long id) {
		RestaurantsCapacity restCap = getRestaurantCapacityById(id);
		restaurantsCapacityRepository.delete(restCap);
		
	}

	@Override
	public List<RestaurantsCapacity> getAllRestaurantCapacity() {
		return restaurantsCapacityRepository.findAll();
	}

	@Override
	public RestaurantsCapacity getRestaurantCapacityById(Long id) {
		return restaurantsCapacityRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
				messageByLocaleService.getMessage("err.restaurant.capacity.not.found", id.toString())));
	}

	@Override
	public RestaurantsCapacity update(RestaurantsCapacity restaurantCapacity) {
		return restaurantsCapacityRepository.save(restaurantCapacity);
	}

	


}
