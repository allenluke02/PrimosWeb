package com.bi.services;

import java.util.List;

import javax.validation.Valid;

import com.bi.model.franchise.RestaurantsCapacity;

public interface RestaurantsCapacityService {

	RestaurantsCapacity createRestaurantCapacity(@Valid RestaurantsCapacity restaurantCapacity);

	void deleteRestaurantCapacityById(Long id);

	List<RestaurantsCapacity> getAllRestaurantCapacity();

	RestaurantsCapacity getRestaurantCapacityById(Long id);

	RestaurantsCapacity update(RestaurantsCapacity restaurantCapacity);

}
