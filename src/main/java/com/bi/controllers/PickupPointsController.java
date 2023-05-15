package com.bi.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bi.model.location.PickupPoint;
import com.bi.services.PickupPointsService;
import com.bi.utils.PaginationConfig;
import com.bi.views.PickupPointView;
import com.bi.vo.ApiListResponseVo;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class PickupPointsController {
	
	private static final Logger logger = LoggerFactory.getLogger(PickupPointsController.class);
	
	@Autowired
	private PickupPointsService pickupPointsService;


	@ApiOperation(value = "create a new pickup-point")
	@PostMapping(value = "/pickupPoints", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(PickupPointView.BasicView.class)
	@PreAuthorize("hasAuthority('CREATE_PICKUP_POINT')")
	public PickupPoint createPickupPoint(@Valid @RequestBody PickupPoint pickupPoint) {
		pickupPoint.setId(null);
		return pickupPointsService.createPickupPoint(pickupPoint);
	}
	
	@GetMapping("/pickupPoints")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@JsonView(PickupPointView.BasicView.class)
	@ApiOperation(value = "Get all pickup-point.")
	public ApiListResponseVo<PickupPoint> getAllPickupPoints(
			@RequestParam(value = "countyId", required = false) Long countyId,
			@RequestParam(value = "pickupPointId", required = false) Long pickupPointId,
			@RequestParam(value = "isOutdoorDining", required = false) boolean isOutdoorDining,
			@RequestParam(value = "isActive", required = false) Boolean isActive,
			@RequestParam(value = "isDriveThru", required = false) boolean isDriveThru,
			@RequestParam(value = "pageSize", required = false,defaultValue = "10") Integer pageSize,
			@RequestParam(value = "pageNumber", required = false,defaultValue = "0") Integer pageNumber,
			@RequestParam(value = "lat", required = false) String lat,
			@RequestParam(value = "lan", required = false) String lan,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "sort", required = false) String sortDirection,
			@RequestParam(value = "sortOn", required = false) String sortOn,
			@RequestParam(value = "isIndoorDining", required = false) boolean isIndoorDining) {
		PaginationConfig paginationConfig = new PaginationConfig(pageSize, pageNumber, sortDirection, sortOn);
		return pickupPointsService.getAllPickupPoints(search,countyId,isOutdoorDining,isDriveThru,isActive,paginationConfig,lat,lan,pickupPointId,isIndoorDining);
	}
	
	@GetMapping("/pickupPoints/{id}")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@JsonView(PickupPointView.BasicView.class)
	@ApiOperation(value = "Get pickup-point with id")
	public PickupPoint getPickupPointById(@PathVariable("id") Long id) {
		return pickupPointsService.getPickupPointById(id);
	}
	
	@ApiOperation(value = "Update a new pickup-point ")
	@PutMapping(value = "/pickupPoints/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(PickupPointView.BasicView.class)
	@PreAuthorize("hasAuthority('UPDATE_PICKUP_POINT')")
	public PickupPoint update(@Valid @PathVariable("id") Long pickupPointId, @RequestBody PickupPoint pickupPoint) {
		pickupPoint.setId(pickupPointId); 
		return pickupPointsService.update(pickupPoint);
	}

//	@ApiOperation(value = "Delete a pickup-point ")
//	@ApiResponses({ @ApiResponse(code = 200, message = "ok"),
//			@ApiResponse(code = 500, message = "Internal server error") })
//	@DeleteMapping("/pickupPoints/{id}")
//	@PreAuthorize("hasAuthority('DELETE_PICKUP_POINT')")
//	//public void deletePickupPointById(@PathVariable("id") Long id) {
//		//pickupPointsService.deletePickupPointById(id);
//	}
	
	@ApiOperation(value = "Enable/Disable a pickup-point ")
	@ApiResponses({ @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@PutMapping("/pickupPoints/{id}")
	@PreAuthorize("hasAuthority('DELETE_PICKUP_POINT')")
	public void changePickupPointStatus(@PathVariable("id") Long id,
			 @RequestParam(value = "active", required = true,defaultValue = "true") boolean active) {
		pickupPointsService.changePickupPointStatus(id,active);
	}

}
