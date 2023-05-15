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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bi.model.ServiceConcern;
import com.bi.services.ServiceConcernService;
import com.bi.utils.BIConstant;
import com.bi.utils.PaginationConfig;
import com.bi.views.ServiceConcernView;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class ServiceConcernController {

    private static final Logger logger = LoggerFactory.getLogger(ServiceConcernController.class);
	
	@Autowired
	private ServiceConcernService serviceConcernService;
	
	@ApiOperation(value = "create a new Service Concern")
	@PostMapping(value = "/serviceConcerns", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(ServiceConcernView.BasicView.class)
	@PreAuthorize("hasAuthority('CREATE_SERVICE_CONCERN')")
	public ServiceConcern createServiceConcern(@Valid @RequestBody ServiceConcern serviceConcern) {
		serviceConcern.setId(null);
		return serviceConcernService.createServiceConcern(serviceConcern);
	}
	
	@GetMapping("/serviceConcerns")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@JsonView(ServiceConcernView.BasicView.class)
	@ApiOperation(value = "Get all Service Concerns")
	public List<ServiceConcern> getAllServiceConcerns(@RequestParam(value = "pageSize", required = false,defaultValue = "10") Integer pageSize,
			@RequestParam(value = "pageNumber", required = false,defaultValue = "0") Integer pageNumber,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "sort", required = false, defaultValue = BIConstant.ASC) String sortDirection,
			@RequestParam(value = "sortOn", required = false,defaultValue = "id") String sortOn) {
		PaginationConfig paginationConfig = new PaginationConfig(pageSize, pageNumber, sortDirection, sortOn);
		return serviceConcernService.getAllServiceConcerns(search,paginationConfig);
	}
	
	@GetMapping("/serviceConcerns/{id}")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@JsonView(ServiceConcernView.BasicView.class)
	@ApiOperation(value = "Get Service Concern with id")
	public ServiceConcern getServiceConcernById(@PathVariable("id") Long id) {
		return serviceConcernService.getServiceConcernById(id);
	}
	
	@ApiOperation(value = "Update a new Service Concern ")
	@PutMapping(value = "/serviceConcerns/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(ServiceConcernView.BasicView.class)
	@PreAuthorize("hasAuthority('UPDATE_SERVICE_CONCERN')")
	public ServiceConcern update(@Valid @PathVariable("id") Long serviceConcernId, @RequestBody ServiceConcern serviceConcern) {
		serviceConcern.setId(serviceConcernId);
		return serviceConcernService.update(serviceConcern);
	}

	@ApiOperation(value = "Delete a Service Concern ")
	@ApiResponses({ @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@DeleteMapping("/serviceConcerns/{id}")
	@PreAuthorize("hasAuthority('DELETE_SERVICE_CONCERN')")
	public void deleteServiceConcernById(@PathVariable("id") Long id) {
		serviceConcernService.deleteServiceConcernById(id);
	}
	
}
