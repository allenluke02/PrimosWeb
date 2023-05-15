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

import com.bi.model.location.ServiceProvider;
import com.bi.services.ServiceProviderService;
import com.bi.views.PickupPointView;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class ServiceProviderController {

	private static final Logger logger = LoggerFactory.getLogger(ServiceProviderController.class);

	@Autowired
	private ServiceProviderService serviceProviderService;
	
	@ApiOperation(value = "create a new ServiceProvider")
	@PostMapping(value = "/serviceProviders", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('CREATE_SERVICE_PROVIDER')")
	@JsonView(PickupPointView.BasicView.class)
	public ServiceProvider createServiceProvider(@Valid @RequestBody ServiceProvider serviceProvider) {
		serviceProvider.setId(null);
		return serviceProviderService.createServiceProvider(serviceProvider);
	}
	
	@GetMapping("/serviceProviders")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@ApiOperation(value = "Get all ServiceProviders")
	@JsonView(PickupPointView.BasicView.class)
	public List<ServiceProvider> getAllServiceProviders() {
		return serviceProviderService.getAllServiceProviders();
	}
	
	@GetMapping("/serviceProviders/{id}")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@ApiOperation(value = "Get ServiceProvider with id")
	@JsonView(PickupPointView.BasicView.class)
	public ServiceProvider getServiceProviderById(@PathVariable("id") Long id) {
		return serviceProviderService.getServiceProviderById(id);
	}
	
	@ApiOperation(value = "Update a new ServiceProvider ")
	@PutMapping(value = "/serviceProviders/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('UPDATE_SERVICE_PROVIDER')")
	@JsonView(PickupPointView.BasicView.class)
	public ServiceProvider update(@Valid @PathVariable("id") Long serviceProviderId, @RequestBody ServiceProvider serviceProvider) {
		serviceProvider.setId(serviceProviderId);
		return serviceProviderService.update(serviceProvider);
	}

	@ApiOperation(value = "Delete a ServiceProvider ")
	@ApiResponses({ @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@DeleteMapping("/serviceProviders/{id}")
	@PreAuthorize("hasAuthority('DELETE_SERVICE_PROVIDER')")
	public void deleteServiceProvidersById(@PathVariable("id") Long id) {
		serviceProviderService.deleteServiceProvidersById(id);
	}
}
