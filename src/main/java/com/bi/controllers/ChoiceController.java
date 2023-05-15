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

import com.bi.model.catering.Choice;
import com.bi.services.ChoiceService;
import com.bi.utils.BIConstant;
import com.bi.utils.PaginationConfig;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class ChoiceController {
	
	private static final Logger logger = LoggerFactory.getLogger(ChoiceController.class);

	@Autowired
	private ChoiceService choiceService;
	
	@ApiOperation(value = "create a new Choice ")
	@PostMapping(value = "/choice", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Choice createChoice(@Valid @RequestBody Choice Choice) {
		Choice.setId(null);
		return choiceService.createChoice(Choice);
	}
	
  	@GetMapping("/choice")
  	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
  			@ApiResponse(code = 500, message = "Internal server error") })
  	@ApiOperation(value = "Get all Choices ")
  	public List<Choice> getAllChoice(
  			@RequestParam(value = "cateringTypeId", required = true) Long cateringTypeId,
  			@RequestParam(value = "pageSize", required = false,defaultValue = "10") Integer pageSize,
  			@RequestParam(value = "pageNumber", required = false,defaultValue = "0") Integer pageNumber,
  			@RequestParam(value = "search", required = false) String search,
  			@RequestParam(value = "sort", required = false, defaultValue = BIConstant.ASC) String sortDirection,
  			@RequestParam(value = "sortOn", required = false,defaultValue = "id") String sortOn) {
  		PaginationConfig paginationConfig = new PaginationConfig(pageSize, pageNumber, sortDirection, sortOn);
  		return choiceService.getAllChoice(search,cateringTypeId,paginationConfig);
  	}
  	
  	@GetMapping("/choice/{id}")
  	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
  			@ApiResponse(code = 500, message = "Internal server error") })
  	@ApiOperation(value = "Get Choice with id")
  	public Choice getChoiceById(@PathVariable("id") Long id) {
  		return choiceService.getChoiceById(id);
  	}
  	
  	@ApiOperation(value = "Update a new Choice ")
  	@PutMapping(value = "/choice/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  	public Choice update(@Valid @PathVariable("id") Long choiceId, @RequestBody Choice choice) {
  		choice.setId(choiceId);
  		return choiceService.update(choice);
  	}
  
  	@ApiOperation(value = "Delete a Choice ")
  	@ApiResponses({ @ApiResponse(code = 200, message = "ok"),
  			@ApiResponse(code = 500, message = "Internal server error") })
  	@DeleteMapping("/choice/{id}")
  	public void deleteChoiceById(@PathVariable("id") Long id) {
  		choiceService.deleteChoiceById(id);
  	}
}
