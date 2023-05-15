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
import org.springframework.web.bind.annotation.RestController;

import com.bi.model.hire.JobType;
import com.bi.services.JobTypeService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class JobTypeController {
	
	private static final Logger logger = LoggerFactory.getLogger(JobTypeController.class);

	@Autowired
	private JobTypeService jobTypeService;
	
	@ApiOperation(value = "create a new JobType")
	@PostMapping(value = "/jobTypes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('CREATE_JOB_TYPE')")
	public JobType createJobType(@Valid @RequestBody JobType jobType) {
		jobType.setId(null);
		return jobTypeService.createJobType(jobType);
	}
	
	@GetMapping("/jobTypes")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@ApiOperation(value = "Get all JobTypes")
	public List<JobType> getAllJobTypes() {
		return jobTypeService.getAllJobTypes();
	}
	
	@GetMapping("/jobTypes/{id}")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@ApiOperation(value = "Get JobType with id")
	public JobType getJobTypesById(@PathVariable("id") Long id) {
		return jobTypeService.getJobTypesById(id);
	}
	
	@ApiOperation(value = "Update a new Job Type ")
	@PutMapping(value = "/jobTypes/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('UPDATE_JOB_TYPE')")
	public JobType update(@Valid @PathVariable("id") Long jobTypeId, @RequestBody JobType jobType) {
		jobType.setId(jobTypeId);
		return jobTypeService.update(jobType);
	}

	@ApiOperation(value = "Delete a Job Type ")
	@ApiResponses({ @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@DeleteMapping("/jobTypes/{id}")
	@PreAuthorize("hasAuthority('DELETE_JOB_TYPE')")
	public void deleteJobTypesById(@PathVariable("id") Long id) {
		jobTypeService.deleteJobTypesById(id);
	}
}
