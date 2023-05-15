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

import com.bi.model.catering.CateringType;
import com.bi.services.CateringTypeService;
import com.bi.utils.BIConstant;
import com.bi.utils.PaginationConfig;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class CateringTypeController {
	
	private static final Logger logger = LoggerFactory.getLogger(CateringTypeController.class);

	@Autowired
	private CateringTypeService cateringTypeService;
	
	@ApiOperation(value = "create a new Catering Type")
	@PostMapping(value = "/cateringType", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('CREATE_CATERING_TYPE')")
	public CateringType createCateringType(@Valid @RequestBody CateringType cateringType) {
		cateringType.setId(null);
		return cateringTypeService.createCateringType(cateringType);
	}
	
  	@GetMapping("/cateringType")
  	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
  			@ApiResponse(code = 500, message = "Internal server error") })
  	@ApiOperation(value = "Get all Catering Type ")
  	public List<CateringType> getAllCateringType(
  			@RequestParam(value = "pageSize", required = false,defaultValue = "10") Integer pageSize,
  			@RequestParam(value = "pageNumber", required = false,defaultValue = "0") Integer pageNumber,
  			@RequestParam(value = "search", required = false) String search,
  			@RequestParam(value = "sort", required = false, defaultValue = BIConstant.ASC) String sortDirection,
  			@RequestParam(value = "sortOn", required = false,defaultValue = "id") String sortOn) {
  		PaginationConfig paginationConfig = new PaginationConfig(pageSize, pageNumber, sortDirection, sortOn);
  		return cateringTypeService.getAllCateringType(search,paginationConfig);
  	}
  	
  	@GetMapping("/cateringType/{id}")
  	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
  			@ApiResponse(code = 500, message = "Internal server error") })
  	@ApiOperation(value = "Get Catering Price with id")
  	public CateringType getCateringTypeById(@PathVariable("id") Long id) {
  		return cateringTypeService.getCateringTypeById(id);
  	}
  	
  	@ApiOperation(value = "Update a new Catering Type ")
  	@PutMapping(value = "/cateringType/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  	@PreAuthorize("hasAuthority('UPDATE_CATERING_TYPE')")
  	public CateringType update(@Valid @PathVariable("id") Long cateringTypeId, @RequestBody CateringType cateringType) {
  		cateringType.setId(cateringTypeId);
  		return cateringTypeService.update(cateringType);
  	}
  
  	@ApiOperation(value = "Delete a Catering Type ")
  	@ApiResponses({ @ApiResponse(code = 200, message = "ok"),
  			@ApiResponse(code = 500, message = "Internal server error") })
  	@DeleteMapping("/cateringType/{id}")
  	@PreAuthorize("hasAuthority('DELETE_CATERING_TYPE')")
  	public void deleteCateringTypeById(@PathVariable("id") Long id) {
  		cateringTypeService.deleteCateringTypeById(id);
  	}
}
