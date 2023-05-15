package com.bi.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bi.model.location.PickupHours;
import com.bi.services.PickupHourService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class PickupHourController {

	private static final Logger logger = LoggerFactory.getLogger(PickupHourController.class);

	@Autowired
	private PickupHourService pickupHoursService;
	
	@ApiOperation(value = "create a new PickupHours")
	@PostMapping(value = "/pickupHours", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('CREATE_PICKUP_HOUR')")
	public PickupHours createPickupHour(@Valid @RequestBody PickupHours pickupHours) {
		pickupHours.setId(null);
		return pickupHoursService.createPickupHour(pickupHours);
	}
	
	@GetMapping("/pickupHours")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@ApiOperation(value = "Get all PickupHours")
	public List<PickupHours> getAllPickupHours() {
		return pickupHoursService.getAllPickupHours();
	}
	
	@GetMapping("/pickupHours/{id}")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@ApiOperation(value = "Get PickupHours with id")
	public PickupHours getPickupHourById(@PathVariable("id") Long id) {
		return pickupHoursService.getPickupHourById(id);
	}
	
	@ApiOperation(value = "Update a new PickupHours ")
	@PutMapping(value = "/pickupHours/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('UPDATE_PICKUP_HOUR')")
	public PickupHours update(@Valid @PathVariable("id") Long pickupHourId, @RequestBody PickupHours pickupHours) {
		pickupHours.setId(pickupHourId);
		return pickupHoursService.update(pickupHours);
	}

	@ApiOperation(value = "Delete a PickupHours ")
	@ApiResponses({ @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@DeleteMapping("/pickupHours/{id}")
	@PreAuthorize("hasAuthority('DELETE_PICKUP_HOUR')")
	public void deletePickupHourById(@PathVariable("id") Long id) {
		pickupHoursService.deletePickupHourById(id);
	}
}
