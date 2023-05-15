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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bi.model.Feedback;
import com.bi.model.hire.Career;
import com.bi.services.CareerService;
import com.bi.utils.BIConstant;
import com.bi.utils.PaginationConfig;
import com.bi.views.CareerView;
import com.bi.vo.ApiListResponseVo;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class CareerController {
	
	private static final Logger logger = LoggerFactory.getLogger(CareerController.class);

	@Autowired
	private CareerService careerService;
	
	@ApiOperation(value = "create a new Career")
	@PostMapping(value = "/careers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(CareerView.BasicView.class)
	public Career createCareer(@Valid @RequestBody Career career) {
		career.setId(null);
		return careerService.createCareer(career);
	}
	
	@GetMapping("/careers")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@JsonView(CareerView.BasicView.class)
	@ApiOperation(value = "Get all Careers")
	public ApiListResponseVo<Career> getAllCareers(@RequestParam(value = "pageSize", required = false,defaultValue = "10") Integer pageSize,
			@RequestParam(value = "pageNumber", required = false,defaultValue = "0") Integer pageNumber,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "sort", required = false, defaultValue = BIConstant.ASC) String sortDirection,
			@RequestParam(value = "sortOn", required = false,defaultValue = "id") String sortOn) {
		PaginationConfig paginationConfig = new PaginationConfig(pageSize, pageNumber, sortDirection, sortOn);
		return careerService.getAllCareers(search,paginationConfig);
	}
	
	@GetMapping("/careers/{id}")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@JsonView(CareerView.BasicView.class)
	@ApiOperation(value = "Get Career with id")
	public Career getCareersById(@PathVariable("id") Long id) {
		return careerService.getCareersById(id);
	}
	
	@ApiOperation(value = "Update a new Career ")
	@PutMapping(value = "/careers/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(CareerView.BasicView.class)
	public Career update(@Valid @PathVariable("id") Long careerId, @RequestBody Career career) {
		career.setId(careerId);
		return careerService.update(career);
	}

	@ApiOperation(value = "Delete a Career ")
	@ApiResponses({ @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@DeleteMapping("/careers/{id}")
	public void deleteCareersById(@PathVariable("id") Long id) {
		careerService.deleteCareersById(id);
	}
}
