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

import com.bi.model.ProductConcern;
import com.bi.services.ProductConcernService;
import com.bi.utils.BIConstant;
import com.bi.utils.PaginationConfig;
import com.bi.views.ProductConcernView;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class ProductConcernController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductConcernController.class);
	
	@Autowired
	private ProductConcernService productConcernService;
	
	@ApiOperation(value = "create a new Product Concern")
	@PostMapping(value = "/productConcerns", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(ProductConcernView.BasicView.class)
	@PreAuthorize("hasAuthority('CREATE_PRODUCT_CONCERN')")
	public ProductConcern createProductConcern(@Valid @RequestBody ProductConcern productConcern) {
		productConcern.setId(null);
		return productConcernService.createProductConcern(productConcern);
	}
	
	@GetMapping("/productConcerns")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@JsonView(ProductConcernView.BasicView.class)
	@ApiOperation(value = "Get all Product Concerns")
	public List<ProductConcern> getAllProductConcerns(
			@RequestParam(value = "pageSize", required = false,defaultValue = "10") Integer pageSize,
			@RequestParam(value = "pageNumber", required = false,defaultValue = "0") Integer pageNumber,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "sort", required = false, defaultValue = BIConstant.ASC) String sortDirection,
			@RequestParam(value = "sortOn", required = false,defaultValue = "id") String sortOn) {
		PaginationConfig paginationConfig = new PaginationConfig(pageSize, pageNumber, sortDirection, sortOn);
		return productConcernService.getAllProductConcerns(search,paginationConfig);
	}
	
	@GetMapping("/productConcerns/{id}")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@JsonView(ProductConcernView.BasicView.class)
	@ApiOperation(value = "Get healthconcern with id")
	public ProductConcern getProductConcernById(@PathVariable("id") Long id) {
		return productConcernService.getProductConcernById(id);
	}
	
	@ApiOperation(value = "Update a new Product Concern ")
	@PutMapping(value = "/productConcerns/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(ProductConcernView.BasicView.class)
	@PreAuthorize("hasAuthority('UPDATE_PRODUCT_CONCERN')")
	public ProductConcern update(@Valid @PathVariable("id") Long productConcernId, @RequestBody ProductConcern productConcern) {
		productConcern.setId(productConcernId);
		return productConcernService.update(productConcern);
	}

	@ApiOperation(value = "Delete a Product Concern ")
	@ApiResponses({ @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@DeleteMapping("/productConcerns/{id}")
	@PreAuthorize("hasAuthority('DELETE_PRODUCT_CONCERN')")
	public void deleteProductConcernById(@PathVariable("id") Long id) {
		productConcernService.deleteProductConcernById(id);
	}
	

}
