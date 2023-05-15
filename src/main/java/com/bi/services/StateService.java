package com.bi.services;

import java.util.List;

import javax.validation.Valid;

import com.bi.model.State;

public interface StateService {

	State update(State state);

	void deleteStateById(Long id);

	State getStateById(Long id);

	List<State> getAllStates();

	State createState(@Valid State state);


}
