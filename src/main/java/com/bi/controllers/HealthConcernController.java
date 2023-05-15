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

import com.bi.model.HealthConcern;
import com.bi.services.HealthConcernService;
import com.bi.utils.BIConstant;
import com.bi.utils.PaginationConfig;
import com.bi.views.HealthConcernView;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class HealthConcernController {
	
	private static final Logger logger = LoggerFactory.getLogger(HealthConcernController.class);
	
	@Autowired
	private HealthConcernService healthConcernService;
	
	@ApiOperation(value = "create a new Health Concern")
	@PostMapping(value = "/healthConcerns", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(HealthConcernView.BasicView.class)
	@PreAuthorize("hasAuthority('CREATE_HEALTH_CONCERN')")
	public HealthConcern createHealthConcern(@Valid @RequestBody HealthConcern healthConcern) {
		healthConcern.setId(null);
		return healthConcernService.createHealthConcern(healthConcern);
	}
	
	@GetMapping("/healthConcerns")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@JsonView(HealthConcernView.BasicView.class)
	@ApiOperation(value = "Get all Health Concerns")
	public List<HealthConcern> getAllHealthConcerns(
			@RequestParam(value = "pageSize", required = false,defaultValue = "10") Integer pageSize,
			@RequestParam(value = "pageNumber", required = false,defaultValue = "0") Integer pageNumber,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "sort", required = false, defaultValue = BIConstant.ASC) String sortDirection,
			@RequestParam(value = "sortOn", required = false,defaultValue = "id") String sortOn) {
		PaginationConfig paginationConfig = new PaginationConfig(pageSize, pageNumber, sortDirection, sortOn);
		return healthConcernService.getAllHealthConcerns(search,paginationConfig);
	}
	
	@GetMapping("/healthConcerns/{id}")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@JsonView(HealthConcernView.BasicView.class)
	@ApiOperation(value = "Get healthconcern with id")
	public HealthConcern getHealthConcernById(@PathVariable("id") Long id) {
		return healthConcernService.getHealthConcernById(id);
	}
	
	@ApiOperation(value = "Update a new Health Concern ")
	@PutMapping(value = "/healthConcerns/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(HealthConcernView.BasicView.class)
	@PreAuthorize("hasAuthority('UPDATE_HEALTH_CONCERN')")
	public HealthConcern update(@Valid @PathVariable("id") Long healthConcernId, @RequestBody HealthConcern healthConcern) {
		healthConcern.setId(healthConcernId);
		return healthConcernService.update(healthConcern);
	}

	@ApiOperation(value = "Delete a Health Concern ")
	@ApiResponses({ @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@DeleteMapping("/healthConcerns/{id}")
	@PreAuthorize("hasAuthority('DELETE_HEALTH_CONCERN')")
	public void deleteHealthConcernById(@PathVariable("id") Long id) {
		healthConcernService.deleteHealthConcernById(id);
	}

}
