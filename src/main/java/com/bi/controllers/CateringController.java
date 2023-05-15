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

import com.bi.model.catering.Catering;
import com.bi.services.CateringService;
import com.bi.utils.BIConstant;
import com.bi.utils.PaginationConfig;
import com.bi.views.CateringView;
import com.bi.vo.ApiListResponseVo;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class CateringController {
	
	private static final Logger logger = LoggerFactory.getLogger(CateringController.class);

	@Autowired
	private CateringService cateringService;
	
	@ApiOperation(value = "create a new Catering")
	@PostMapping(value = "/catering", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(CateringView.BasicView.class)
	public Catering createCatering(@Valid @RequestBody Catering catering) {
		catering.setId(null);
		return cateringService.createCatering(catering);
	}
	
  	@GetMapping("/catering")
  	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
  			@ApiResponse(code = 500, message = "Internal server error") })
  	@ApiOperation(value = "Get all Caterings ")
	@JsonView(CateringView.BasicView.class)
  	public ApiListResponseVo<Catering> getAllCatering(
  			@RequestParam(value = "pageSize", required = false,defaultValue = "10") Integer pageSize,
  			@RequestParam(value = "pageNumber", required = false,defaultValue = "0") Integer pageNumber,
  			@RequestParam(value = "search", required = false) String search,
  			@RequestParam(value = "sort", required = false, defaultValue = BIConstant.ASC) String sortDirection,
  			@RequestParam(value = "sortOn", required = false,defaultValue = "id") String sortOn) {
  		PaginationConfig paginationConfig = new PaginationConfig(pageSize, pageNumber, sortDirection, sortOn);
  		return cateringService.getAllCatering(search,paginationConfig);
  	}
  	
  	@GetMapping("/catering/{id}")
  	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
  			@ApiResponse(code = 500, message = "Internal server error") })
  	@ApiOperation(value = "Get Catering with id")
	@JsonView(CateringView.BasicView.class)
  	public Catering getCateringById(@PathVariable("id") Long id) {
  		return cateringService.getCateringById(id);
  	}
  	
  	@ApiOperation(value = "Update a new Catering ")
  	@PutMapping(value = "/catering/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(CateringView.BasicView.class)
  	public Catering update(@Valid @PathVariable("id") Long CateringId, @RequestBody Catering catering) {
  		catering.setId(CateringId);
  		return cateringService.update(catering);
  	}
  
  	@ApiOperation(value = "Delete a Catering ")
  	@ApiResponses({ @ApiResponse(code = 200, message = "ok"),
  			@ApiResponse(code = 500, message = "Internal server error") })
  	@DeleteMapping("/catering/{id}")
	@JsonView(CateringView.BasicView.class)
  	public void deleteCateringById(@PathVariable("id") Long id) {
  		cateringService.deleteCateringById(id);
  	}
}
