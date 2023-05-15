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

import com.bi.model.State;
import com.bi.services.StateService;
import com.bi.views.FranchiseView;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class StateController {
	
	private static final Logger logger = LoggerFactory.getLogger(StateController.class);

	@Autowired
	private StateService stateService;
	
	@ApiOperation(value = "create a new State")
	@PostMapping(value = "/states", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(FranchiseView.BasicView.class)
	public State createState(@Valid @RequestBody State state) {
		state.setId(null);
		return stateService.createState(state);
	}
	
	@GetMapping("/states")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@JsonView(FranchiseView.BasicView.class)
	@ApiOperation(value = "Get all States")
	public List<State> getAllStates() {
		return stateService.getAllStates();
	}
	
	@GetMapping("/states/{id}")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@JsonView(FranchiseView.BasicView.class)
	@ApiOperation(value = "Get State with id")
	public State getStatesById(@PathVariable("id") Long id) {
		return stateService.getStateById(id);
	}
	
	@ApiOperation(value = "Update a new States ")
	@PutMapping(value = "/states/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(FranchiseView.BasicView.class)
	public State update(@Valid @PathVariable("id") Long stateId, @RequestBody State state) {
		state.setId(stateId);
		return stateService.update(state);
	}

	@ApiOperation(value = "Delete a States ")
	@ApiResponses({ @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@DeleteMapping("/states/{id}")
	public void deleteStatesById(@PathVariable("id") Long id) {
		stateService.deleteStateById(id);
	}
}
