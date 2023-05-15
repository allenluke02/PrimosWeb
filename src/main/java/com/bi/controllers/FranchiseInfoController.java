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

import com.bi.model.franchise.FranchiseInfo;
import com.bi.services.FranchiseInfoService;
import com.bi.views.FranchiseView;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class FranchiseInfoController {
	
	private static final Logger logger = LoggerFactory.getLogger(FranchiseInfoController.class);

	@Autowired
	private FranchiseInfoService franchiseInfoService;
	
	@ApiOperation(value = "create a new FranchiseInfo")
	@PostMapping(value = "/franchiseInfos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(FranchiseView.BasicView.class)
	public FranchiseInfo createFranchiseInfo(@Valid @RequestBody FranchiseInfo franchiseInfo) {
		franchiseInfo.setId(null);
		return franchiseInfoService.createFranchiseInfo(franchiseInfo);
	}
	
	
	@GetMapping("/franchiseInfos")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@JsonView(FranchiseView.BasicView.class)
	@ApiOperation(value = "Get all FranchiseInfos")
	public List<FranchiseInfo> getAllFranchiseInfos() {
		return franchiseInfoService.getAllFranchiseInfos();
	}
	
	@GetMapping("/franchiseInfos/{id}")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@JsonView(FranchiseView.BasicView.class)
	@ApiOperation(value = "Get FranchiseInfo with id")
	public FranchiseInfo getFranchiseInfosById(@PathVariable("id") Long id) {
		return franchiseInfoService.getFranchiseInfosById(id);
	}
	
	@ApiOperation(value = "Update a new FranchiseInfos ")
	@PutMapping(value = "/franchiseInfos/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(FranchiseView.BasicView.class)
	public FranchiseInfo update(@Valid @PathVariable("id") Long franchiseInfoId, @RequestBody FranchiseInfo franchiseInfo) {
		franchiseInfo.setId(franchiseInfoId);
		return franchiseInfoService.update(franchiseInfo);
	}

	@ApiOperation(value = "Delete a FranchiseInfos ")
	@ApiResponses({ @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@DeleteMapping("/franchiseInfos/{id}")
	public void deleteFranchiseInfosById(@PathVariable("id") Long id) {
		franchiseInfoService.deleteFranchiseInfosById(id);
	}
	
}
