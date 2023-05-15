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

import com.bi.model.Country;
import com.bi.services.CountryService;
import com.bi.views.FranchiseView;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class CountryController {
	
	private static final Logger logger = LoggerFactory.getLogger(CountryController.class);

	@Autowired
	private CountryService countryService;
	
	@ApiOperation(value = "create a new Country")
	@PostMapping(value = "/countries", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Country createCountry(@Valid @RequestBody Country country) {
		country.setId(null);
		return countryService.createCountry(country);
	}
	
	@GetMapping("/countries")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@ApiOperation(value = "Get all Country")
	public List<Country> getAllCountry() {
		return countryService.getAllCountry();
	}
	
	@GetMapping("/countries/{id}")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@ApiOperation(value = "Get Country with id")
	public Country getCountrysById(@PathVariable("id") Long id) {
		return countryService.getCountryById(id);
	}
	
	@ApiOperation(value = "Update a new Countrys ")
	@PutMapping(value = "/countries/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Country update(@Valid @PathVariable("id") Long countryId, @RequestBody Country country) {
		country.setId(countryId);
		return countryService.update(country);
	}

	@ApiOperation(value = "Delete a Countrys ")
	@ApiResponses({ @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@DeleteMapping("/countries/{id}")
	public void deleteCountrysById(@PathVariable("id") Long id) {
		countryService.deleteCountryById(id);
	}
}
