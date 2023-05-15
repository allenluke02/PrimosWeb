package com.bi.controllers;

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

import com.bi.model.hire.Job;
import com.bi.services.JobService;
import com.bi.utils.BIConstant;
import com.bi.utils.PaginationConfig;
import com.bi.views.JobView;
import com.bi.vo.ApiListResponseVo;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class JobController {
	
	private static final Logger logger = LoggerFactory.getLogger(JobController.class);

	@Autowired
	private JobService jobService;
	
	@ApiOperation(value = "create a new Job")
	@PostMapping(value = "/jobs", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(JobView.BasicView.class)
	@PreAuthorize("hasAuthority('CREATE_JOB')")
	public Job createJob(@Valid @RequestBody Job job) {
		job.setId(null);
		return jobService.createJob(job);
	}
	
	@GetMapping("/jobs")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@JsonView(JobView.BasicView.class)
	@ApiOperation(value = "Get all Jobs")
	public ApiListResponseVo<Job> getAllJobs(
			@RequestParam(value = "jobTypeId", required = false) Long jobTypeId,
			@RequestParam(value = "pickupPointId", required = false) Long pickupPointId,
			@RequestParam(value = "pageSize", required = false,defaultValue = "10") Integer pageSize,
			@RequestParam(value = "pageNumber", required = false,defaultValue = "0") Integer pageNumber,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "isExpired", required = false) Boolean isExpired,
			@RequestParam(value = "sort", required = false, defaultValue = BIConstant.DESC) String sortDirection,
			@RequestParam(value = "sortOn", required = false,defaultValue = "postingDt") String sortOn
			) {
		PaginationConfig paginationConfig = new PaginationConfig(pageSize, pageNumber, sortDirection, sortOn);
		return jobService.getAllJobs(search,jobTypeId,pickupPointId,paginationConfig,isExpired);
	}
	
	@GetMapping("/jobs/{id}")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@JsonView(JobView.BasicView.class)
	@ApiOperation(value = "Get Job with id")
	public Job getJobsById(@PathVariable("id") Long id) {
		return jobService.getJobsById(id);
	}
	
	@ApiOperation(value = "Update a new Jobs ")
	@PutMapping(value = "/jobs/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(JobView.BasicView.class)
	@PreAuthorize("hasAuthority('UPDATE_JOB')")
	public Job update(@Valid @PathVariable("id") Long jobId, @RequestBody Job job) {
		job.setId(jobId);
		return jobService.update(job);
	}

	@ApiOperation(value = "Delete a Jobs ")
	@ApiResponses({ @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@DeleteMapping("/jobs/{id}")
	@PreAuthorize("hasAuthority('DELETE_JOB')")
	public void deleteJobsById(@PathVariable("id") Long id) {
		jobService.deleteJobsById(id);
	}
}
