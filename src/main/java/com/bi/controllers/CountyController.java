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

import com.bi.model.location.County;
import com.bi.services.CountyService;
import com.bi.views.CountyView;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class CountyController {

	private static final Logger logger = LoggerFactory.getLogger(CountyController.class);

	@Autowired
	private CountyService countyService;
	
	@ApiOperation(value = "create a new County")
	@PostMapping(value = "/counties", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('CREATE_COUNTY')")
	@JsonView(CountyView.BasicView.class)
	public County createCounty(@Valid @RequestBody County county) {
		county.setId(null);
		return countyService.createCounty(county);
	}
	
	@GetMapping("/counties")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@ApiOperation(value = "Get all Counties")
	@JsonView(CountyView.BasicView.class)
	public List<County> getAllCounties() {
		return countyService.getAllCounties();
	}
	
	@GetMapping("/counties/{id}")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@ApiOperation(value = "Get County with id")
	public County getCountyById(@PathVariable("id") Long id) {
		return countyService.getCountyById(id);
	}
	
	@ApiOperation(value = "Update a new County ")
	@PutMapping(value = "/counties/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('UPDATE_COUNTY')")
	@JsonView(CountyView.BasicView.class)
	public County update(@Valid @PathVariable("id") Long countId, @RequestBody County county) {
		county.setId(countId);
		return countyService.update(county);
	}

	@ApiOperation(value = "Delete a County ")
	@ApiResponses({ @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@DeleteMapping("/counties/{id}")
	@PreAuthorize("hasAuthority('DELETE_COUNTY')")
	@JsonView(CountyView.BasicView.class)
	public void deleteCountiesById(@PathVariable("id") Long id) {
		countyService.deleteCountiesById(id);
	}
}
