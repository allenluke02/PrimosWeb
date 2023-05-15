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

import com.bi.model.catering.TortillaType;
import com.bi.services.TortillaTypeService;
import com.bi.utils.BIConstant;
import com.bi.utils.PaginationConfig;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class TortillaTypeController {
	
	private static final Logger logger = LoggerFactory.getLogger(TortillaTypeController.class);

	@Autowired
	private TortillaTypeService tortillaTypeService;
	
	@ApiOperation(value = "create a new Tortilla Type")
	@PostMapping(value = "/tortillaType", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public TortillaType createTortillaType(@Valid @RequestBody TortillaType tortillaType) {
		tortillaType.setId(null);
		return tortillaTypeService.createTortillaType(tortillaType);
	}
	
  	@GetMapping("/tortillaType")
  	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
  			@ApiResponse(code = 500, message = "Internal server error") })
  	@ApiOperation(value = "Get all Tortilla Types ")
  	public List<TortillaType> getAllTortillaType(
  			@RequestParam(value = "cateringTypeId", required = true) Long cateringTypeId,
  			@RequestParam(value = "pageSize", required = false,defaultValue = "10") Integer pageSize,
  			@RequestParam(value = "pageNumber", required = false,defaultValue = "0") Integer pageNumber,
  			@RequestParam(value = "search", required = false) String search,
  			@RequestParam(value = "sort", required = false, defaultValue = BIConstant.ASC) String sortDirection,
  			@RequestParam(value = "sortOn", required = false,defaultValue = "id") String sortOn) {
  		PaginationConfig paginationConfig = new PaginationConfig(pageSize, pageNumber, sortDirection, sortOn);
  		return tortillaTypeService.getAllTortillaType(search,cateringTypeId,paginationConfig);
  	}
  	
  	@GetMapping("/tortillaType/{id}")
  	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
  			@ApiResponse(code = 500, message = "Internal server error") })
  	@ApiOperation(value = "Get TortillaType with id")
  	public TortillaType getTortillaTypeById(@PathVariable("id") Long id) {
  		return tortillaTypeService.getTortillaTypeById(id);
  	}
  	
  	@ApiOperation(value = "Update a new Tortilla Type ")
  	@PutMapping(value = "/tortillaType/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  	public TortillaType update(@Valid @PathVariable("id") Long tortillaTypeId, @RequestBody TortillaType tortillaType) {
  		tortillaType.setId(tortillaTypeId);
  		return tortillaTypeService.update(tortillaType);
  	}
  
  	@ApiOperation(value = "Delete a TortillaType ")
  	@ApiResponses({ @ApiResponse(code = 200, message = "ok"),
  			@ApiResponse(code = 500, message = "Internal server error") })
  	@DeleteMapping("/tortillaType/{id}")
  	public void deleteTortillaTypeById(@PathVariable("id") Long id) {
  		tortillaTypeService.deleteTortillaTypeById(id);
  	}
}
