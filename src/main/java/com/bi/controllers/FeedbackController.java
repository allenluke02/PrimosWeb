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

import com.bi.model.Feedback;
import com.bi.services.FeedbackService;
import com.bi.utils.BIConstant;
import com.bi.utils.PaginationConfig;
import com.bi.views.FeedbackView;
import com.bi.vo.ApiListResponseVo;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class FeedbackController {
	
	private static final Logger logger = LoggerFactory.getLogger(FeedbackController.class);

	@Autowired
	private FeedbackService feedbackService;
	
	@ApiOperation(value = "create a new Feedback")
	@PostMapping(value = "/feedbacks", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(FeedbackView.BasicView.class)
	public Feedback createFeedback(@Valid @RequestBody Feedback feedback) {
		feedback.setId(null);
		return feedbackService.createFeedback(feedback);
	}
	
	@GetMapping("/feedbacks")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@ApiOperation(value = "Get all Feedbacks")
	@JsonView(FeedbackView.BasicView.class)
	public ApiListResponseVo<Feedback> getAllFeedbacks(
			@RequestParam(value = "pageSize", required = false,defaultValue = "10") Integer pageSize,
			@RequestParam(value = "pageNumber", required = false,defaultValue = "0") Integer pageNumber,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "sort", required = false, defaultValue = BIConstant.ASC) String sortDirection,
			@RequestParam(value = "sortOn", required = false,defaultValue = "id") String sortOn) {
		PaginationConfig paginationConfig = new PaginationConfig(pageSize, pageNumber, sortDirection, sortOn);
		return feedbackService.getAllFeedbacks(search,paginationConfig);
	}
	
	@GetMapping("/feedbacks/{id}")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@ApiOperation(value = "Get Feedback with id")
	@JsonView(FeedbackView.BasicView.class)
	public Feedback getFeedbacksById(@PathVariable("id") Long id) {
		return feedbackService.getFeedbacksById(id);
	}
	
	@ApiOperation(value = "Update a new Feedbacks ")
	@PutMapping(value = "/feedbacks/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(FeedbackView.BasicView.class)
	public Feedback update(@Valid @PathVariable("id") Long feedbackId, @RequestBody Feedback feedback) {
		feedback.setId(feedbackId);
		return feedbackService.update(feedback);
	}

	@ApiOperation(value = "Delete a Feedbacks ")
	@ApiResponses({ @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@DeleteMapping("/feedbacks/{id}")
	public void deleteFeedbacksById(@PathVariable("id") Long id) {
		feedbackService.deleteFeedbacksById(id);
	}
}
