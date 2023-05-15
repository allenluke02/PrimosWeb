package com.bi.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bi.model.franchise.RestaurantsCapacity;
import com.bi.services.RestaurantsCapacityService;
import com.bi.views.FranchiseView;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class RestaurantsCapacityController {
	
	private static final Logger logger = LoggerFactory.getLogger(RestaurantsCapacityController.class);

	@Autowired
	private RestaurantsCapacityService restaurantCapacitieservice;
	
	@ApiOperation(value = "create a new RestaurantCapacity")
	@PostMapping(value = "/restaurantCapacities", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(FranchiseView.BasicView.class)
	public RestaurantsCapacity createRestaurantCapacity(@Valid @RequestBody RestaurantsCapacity restaurantCapacity) {
		restaurantCapacity.setId(null);
		return restaurantCapacitieservice.createRestaurantCapacity(restaurantCapacity);
	}
	
	@GetMapping("/restaurantCapacities")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@JsonView(FranchiseView.BasicView.class)
	@ApiOperation(value = "Get all restaurantCapacities")
	public List<RestaurantsCapacity> getAllRestaurantCapacities() {
		return restaurantCapacitieservice.getAllRestaurantCapacity();
	}
	
	@GetMapping("/restaurantCapacities/{id}")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@JsonView(FranchiseView.BasicView.class)
	@ApiOperation(value = "Get RestaurantCapacity with id")
	public RestaurantsCapacity getRestaurantCapacitiesById(@PathVariable("id") Long id) {
		return restaurantCapacitieservice.getRestaurantCapacityById(id);
	}
	
	@ApiOperation(value = "Update a new RestaurantCapacity ")
	@PutMapping(value = "/restaurantCapacities/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(FranchiseView.BasicView.class)
	public RestaurantsCapacity update(@Valid @PathVariable("id") Long restaurantCapacityId, @RequestBody RestaurantsCapacity restaurantCapacity) {
		restaurantCapacity.setId(restaurantCapacityId);
		return restaurantCapacitieservice.update(restaurantCapacity);
	}

	@ApiOperation(value = "Delete a RestaurantCapacity ")
	@ApiResponses({ @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@DeleteMapping("/restaurantCapacities/{id}")
	public void deleteRestaurantCapacitiesById(@PathVariable("id") Long id) {
		restaurantCapacitieservice.deleteRestaurantCapacityById(id);
	}
}
