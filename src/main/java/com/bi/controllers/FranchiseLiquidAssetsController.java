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

import com.bi.model.franchise.FranchiseLiquidAsset;
import com.bi.services.FranchiseLiquidAssetsService;
import com.bi.views.FranchiseView;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class FranchiseLiquidAssetsController {
	
	private static final Logger logger = LoggerFactory.getLogger(FranchiseLiquidAssetsController.class);

	@Autowired
	private FranchiseLiquidAssetsService franchiseLiquidAssetService;
	
	@ApiOperation(value = "create a new FranchiseLiquidAsset")
	@PostMapping(value = "/franchiseLiquidAssets", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(FranchiseView.BasicView.class)
	public FranchiseLiquidAsset createFranchiseLiquidAssets(@Valid @RequestBody FranchiseLiquidAsset franchiseLiquidAsset) {
		franchiseLiquidAsset.setId(null);
		return franchiseLiquidAssetService.createFranchiseLiquidAssets(franchiseLiquidAsset);
	}
	
	
	@GetMapping("/franchiseLiquidAssets")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@JsonView(FranchiseView.BasicView.class)
	@ApiOperation(value = "Get all FranchiseLiquidAssets")
	public List<FranchiseLiquidAsset> getAllFranchiseLiquidAssets() {
		return franchiseLiquidAssetService.getAllFranchiseLiquidAssets();
	}
	
	@GetMapping("/franchiseLiquidAssets/{id}")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@JsonView(FranchiseView.BasicView.class)
	@ApiOperation(value = "Get FranchiseLiquidAsset with id")
	public FranchiseLiquidAsset getFranchiseLiquidAssetsById(@PathVariable("id") Long id) {
		return franchiseLiquidAssetService.getFranchiseLiquidAssetsById(id);
	}
	
	@ApiOperation(value = "Update a new FranchiseLiquidAssets ")
	@PutMapping(value = "/franchiseLiquidAssets/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(FranchiseView.BasicView.class)
	public FranchiseLiquidAsset update(@Valid @PathVariable("id") Long franchiseLiquidAssetId, @RequestBody FranchiseLiquidAsset franchiseLiquidAsset) {
		franchiseLiquidAsset.setId(franchiseLiquidAssetId);
		return franchiseLiquidAssetService.update(franchiseLiquidAsset);
	}

	@ApiOperation(value = "Delete a FranchiseLiquidAssets ")
	@ApiResponses({ @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@DeleteMapping("/franchiseLiquidAssets/{id}")
	public void deleteFranchiseLiquidAssetsById(@PathVariable("id") Long id) {
		franchiseLiquidAssetService.deleteFranchiseLiquidAssetsById(id);
	}
	
}
