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

import com.bi.model.catering.BeansType;
import com.bi.services.BeansTypeService;
import com.bi.utils.BIConstant;
import com.bi.utils.PaginationConfig;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class BeansTypeController {
	
	private static final Logger logger = LoggerFactory.getLogger(BeansTypeController.class);

	@Autowired
	private BeansTypeService beansTypeService;
	
	@ApiOperation(value = "create a new Beans Type")
	@PostMapping(value = "/beansType", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public BeansType createBeansType(@Valid @RequestBody BeansType beansType) {
		beansType.setId(null);
		return beansTypeService.createBeansType(beansType);
	}
	
  	@GetMapping("/beansType")
  	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
  			@ApiResponse(code = 500, message = "Internal server error") })
  	@ApiOperation(value = "Get all BeansTypes ")
  	public List<BeansType> getAllBeansType(
  			@RequestParam(value = "cateringTypeId", required = true) Long cateringTypeId,
  			@RequestParam(value = "pageSize", required = false,defaultValue = "10") Integer pageSize,
  			@RequestParam(value = "pageNumber", required = false,defaultValue = "0") Integer pageNumber,
  			@RequestParam(value = "search", required = false) String search,
  			@RequestParam(value = "sort", required = false, defaultValue = BIConstant.ASC) String sortDirection,
  			@RequestParam(value = "sortOn", required = false,defaultValue = "id") String sortOn) {
  		PaginationConfig paginationConfig = new PaginationConfig(pageSize, pageNumber, sortDirection, sortOn);
  		return beansTypeService.getAllBeansType(search,cateringTypeId,paginationConfig);
  	}
  	
  	@GetMapping("/beansType/{id}")
  	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
  			@ApiResponse(code = 500, message = "Internal server error") })
  	@ApiOperation(value = "Get BeansType with id")
  	public BeansType getBeansTypeById(@PathVariable("id") Long id) {
  		return beansTypeService.getBeansTypeById(id);
  	}
  	
  	@ApiOperation(value = "Update a new BeansType ")
  	@PutMapping(value = "/beansType/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  	public BeansType update(@Valid @PathVariable("id") Long beansTypeId, @RequestBody BeansType beansType) {
  		beansType.setId(beansTypeId);
  		return beansTypeService.update(beansType);
  	}
  
  	@ApiOperation(value = "Delete a BeansType ")
  	@ApiResponses({ @ApiResponse(code = 200, message = "ok"),
  			@ApiResponse(code = 500, message = "Internal server error") })
  	@DeleteMapping("/beansType/{id}")
  	public void deleteBeansTypeById(@PathVariable("id") Long id) {
  		beansTypeService.deleteBeansTypeById(id);
  	}
}
