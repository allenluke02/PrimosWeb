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

import com.bi.model.catering.CateringPrice;
import com.bi.services.CateringPriceService;
import com.bi.utils.BIConstant;
import com.bi.utils.PaginationConfig;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class CateringPriceController {
	
	private static final Logger logger = LoggerFactory.getLogger(CateringPriceController.class);

	@Autowired
	private CateringPriceService cateringPriceService;
	
	@ApiOperation(value = "create a new CateringPrice")
	@PostMapping(value = "/cateringPrice", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('CREATE_CATERING_PRICE')")
	public CateringPrice createCateringPrice(@Valid @RequestBody CateringPrice cateringPrice) {
		cateringPrice.setId(null);
		return cateringPriceService.createCateringPrice(cateringPrice);
	}
	
  	@GetMapping("/cateringPrice")
  	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
  			@ApiResponse(code = 500, message = "Internal server error") })
  	@ApiOperation(value = "Get all Catering Prices ")
  	public List<CateringPrice> getAllCateringPrice(
  			@RequestParam(value = "pageSize", required = false,defaultValue = "20") Integer pageSize,
  			@RequestParam(value = "pageNumber", required = false,defaultValue = "0") Integer pageNumber,
  			@RequestParam(value = "search", required = false) String search,
  			@RequestParam(value = "sort", required = false, defaultValue = BIConstant.ASC) String sortDirection,
  			@RequestParam(value = "sortOn", required = false,defaultValue = "id") String sortOn) {
  		PaginationConfig paginationConfig = new PaginationConfig(pageSize, pageNumber, sortDirection, sortOn);
  		return cateringPriceService.getAllCateringPrice(search,paginationConfig);
  	}
  	
  	@GetMapping("/cateringPrice/{id}")
  	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
  			@ApiResponse(code = 500, message = "Internal server error") })
  	@ApiOperation(value = "Get Catering Price with id")
  	public CateringPrice getCateringPriceById(@PathVariable("id") Long id) {
  		return cateringPriceService.getCateringPriceById(id);
  	}
  	
  	@ApiOperation(value = "Update a new Catering Price ")
  	@PutMapping(value = "/cateringPrice/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  	@PreAuthorize("hasAuthority('UPDATE_CATERING_PRICE')")
  	public CateringPrice update(@Valid @PathVariable("id") Long cateringPriceId, @RequestBody CateringPrice cateringPrice) {
  		cateringPrice.setId(cateringPriceId);
  		return cateringPriceService.update(cateringPrice);
  	}
  
  	@ApiOperation(value = "Delete a Catering Price ")
  	@ApiResponses({ @ApiResponse(code = 200, message = "ok"),
  			@ApiResponse(code = 500, message = "Internal server error") })
  	@DeleteMapping("/cateringPrice/{id}")
  	@PreAuthorize("hasAuthority('DELETE_CATERING_PRICE')")
  	public void deleteCateringPriceById(@PathVariable("id") Long id) {
  		cateringPriceService.deleteCateringPriceById(id);
  	}
}
