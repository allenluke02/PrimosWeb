package com.bi.controllers;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bi.model.franchise.Franchise;
import com.bi.services.FranchiseService;
import com.bi.utils.BIConstant;
import com.bi.utils.PaginationConfig;
import com.bi.views.FranchiseView;
import com.bi.vo.ApiListResponseVo;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class FranchiseController {
	
	private static final Logger logger = LoggerFactory.getLogger(FranchiseController.class);

	@Autowired
	private FranchiseService franchiseService;
	
	@ApiOperation(value = "create a new Franchise")
	@PostMapping(value = "/franchises", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(FranchiseView.BasicView.class)
	public Franchise createFranchise(@Valid @RequestBody Franchise franchise) {
		franchise.setId(null);
		return franchiseService.createFranchise(franchise);
	}
	
	
	@GetMapping("/franchises")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@JsonView(FranchiseView.BasicView.class)
	@ApiOperation(value = "Get all Franchises")
	public ApiListResponseVo<Franchise> getAllFranchises(
			@RequestParam(value = "pageSize", required = false,defaultValue = "10") Integer pageSize,
			@RequestParam(value = "pageNumber", required = false,defaultValue = "0") Integer pageNumber,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "sort", required = false, defaultValue = BIConstant.ASC) String sortDirection,
			@RequestParam(value = "sortOn", required = false,defaultValue = "id") String sortOn) {
		PaginationConfig paginationConfig = new PaginationConfig(pageSize, pageNumber, sortDirection, sortOn);
		return franchiseService.getAllFranchises(search,paginationConfig);
	}
	
	@GetMapping("/franchises/{id}")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@JsonView(FranchiseView.BasicView.class)
	@ApiOperation(value = "Get Franchise with id")
	public Franchise getFranchisesById(@PathVariable("id") Long id) {
		return franchiseService.getFranchisesById(id);
	}
	
	@ApiOperation(value = "Update a new Franchises ")
	@PutMapping(value = "/franchises/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(FranchiseView.BasicView.class)
	public Franchise update(@Valid @PathVariable("id") Long FranchiseId, @RequestBody Franchise franchise) {
		franchise.setId(FranchiseId);
		return franchiseService.update(franchise);
	}

	@ApiOperation(value = "Delete a Franchises ")
	@ApiResponses({ @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@DeleteMapping("/franchises/{id}")
	public void deleteFranchisesById(@PathVariable("id") Long id) {
		franchiseService.deleteFranchisesById(id);
	}
	
}
