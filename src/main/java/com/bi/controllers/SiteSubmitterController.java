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

import com.bi.model.franchise.SiteSubmitter;
import com.bi.services.SiteSubmitterService;
import com.bi.utils.BIConstant;
import com.bi.utils.PaginationConfig;
import com.bi.views.SiteSubmitterView;
import com.bi.vo.ApiListResponseVo;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class SiteSubmitterController {
	
	private static final Logger logger = LoggerFactory.getLogger(SiteSubmitterController.class);

	@Autowired
	private SiteSubmitterService siteSubmitterService;
	
	@ApiOperation(value = "create a new SiteSubmitter")
	@PostMapping(value = "/siteSubmitters", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(SiteSubmitterView.BasicView.class)
	public SiteSubmitter createSiteSubmitters(@Valid @RequestBody SiteSubmitter siteSubmitter) {
		siteSubmitter.setId(null);
		return siteSubmitterService.createSiteSubmitters(siteSubmitter);
	}
	
	
	@GetMapping("/siteSubmitters")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@JsonView(SiteSubmitterView.BasicView.class)
	@ApiOperation(value = "Get all Site Submitters")
	public ApiListResponseVo<SiteSubmitter> getAllSiteSubmitters(	@RequestParam(value = "pageSize", required = false,defaultValue = "10") Integer pageSize,
			@RequestParam(value = "pageNumber", required = false,defaultValue = "0") Integer pageNumber,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "sort", required = false, defaultValue = BIConstant.ASC) String sortDirection,
			@RequestParam(value = "sortOn", required = false,defaultValue = "id") String sortOn) {
		PaginationConfig paginationConfig = new PaginationConfig(pageSize, pageNumber, sortDirection, sortOn);
		return siteSubmitterService.getAllSiteSubmitters(search,paginationConfig);
	}
	
	@GetMapping("/siteSubmitters/{id}")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@JsonView(SiteSubmitterView.BasicView.class)
	@ApiOperation(value = "Get Site Submitter with id")
	public SiteSubmitter getSiteSubmittersById(@PathVariable("id") Long id) {
		return siteSubmitterService.getSiteSubmittersById(id);
	}
	
	@ApiOperation(value = "Update a new Site Submitters ")
	@PutMapping(value = "/siteSubmitters/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(SiteSubmitterView.BasicView.class)
	public SiteSubmitter update(@Valid @PathVariable("id") Long siteSubmitterId, @RequestBody SiteSubmitter siteSubmitter) {
		siteSubmitter.setId(siteSubmitterId);
		return siteSubmitterService.update(siteSubmitter);
	}

	@ApiOperation(value = "Delete a Site Submitters ")
	@ApiResponses({ @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@DeleteMapping("/siteSubmitters/{id}")
	public void deleteSiteSubmittersById(@PathVariable("id") Long id) {
		siteSubmitterService.deleteSiteSubmittersById(id);
	}
	
}
