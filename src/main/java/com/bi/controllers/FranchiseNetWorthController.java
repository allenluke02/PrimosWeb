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

import com.bi.model.franchise.FranchiseNetWorth;
import com.bi.services.FranchiseNetWorthService;
import com.bi.views.FranchiseView;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class FranchiseNetWorthController {
	
	private static final Logger logger = LoggerFactory.getLogger(FranchiseNetWorthController.class);

	@Autowired
	private FranchiseNetWorthService franchiseNetWorthService;
	
	@ApiOperation(value = "create a new Franchise Net Worth")
	@PostMapping(value = "/franchiseNetWorths", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(FranchiseView.BasicView.class)
	public FranchiseNetWorth createfranchiseNetWorths(@Valid @RequestBody FranchiseNetWorth franchiseNetWorth) {
		franchiseNetWorth.setId(null);
		return franchiseNetWorthService.createFranchiseNetWorths(franchiseNetWorth);
	}
	
	
	@GetMapping("/franchiseNetWorths")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@JsonView(FranchiseView.BasicView.class)
	@ApiOperation(value = "Get all franchiseNetWorths")
	public List<FranchiseNetWorth> getAllfranchiseNetWorths() {
		return franchiseNetWorthService.getAllFranchiseNetWorths();
	}
	
	@GetMapping("/franchiseNetWorths/{id}")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@JsonView(FranchiseView.BasicView.class)
	@ApiOperation(value = "Get franchiseNetWorths with id")
	public FranchiseNetWorth getfranchiseNetWorthsById(@PathVariable("id") Long id) {
		return franchiseNetWorthService.getFranchiseNetWorthsById(id);
	}
	
	@ApiOperation(value = "Update a new franchiseNetWorths ")
	@PutMapping(value = "/franchiseNetWorths/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(FranchiseView.BasicView.class)
	public FranchiseNetWorth update(@Valid @PathVariable("id") Long franchiseNetWorthId, @RequestBody FranchiseNetWorth franchiseNetWorth) {
		franchiseNetWorth.setId(franchiseNetWorthId);
		return franchiseNetWorthService.update(franchiseNetWorth);
	}

	@ApiOperation(value = "Delete a franchiseNetWorths ")
	@ApiResponses({ @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@DeleteMapping("/franchiseNetWorths/{id}")
	public void deletefranchiseNetWorthsById(@PathVariable("id") Long id) {
		franchiseNetWorthService.deleteFranchiseNetWorthsById(id);
	}
	
}
