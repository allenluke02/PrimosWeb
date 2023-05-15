package com.bi.services;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bi.exception.EntityNotFoundException;
import com.bi.model.State;
import com.bi.repositories.StateRepository;

@Service
public class StateServiceImpl implements StateService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StateServiceImpl.class);

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private MessageByLocaleService messageByLocaleService;

	@Override
	public State update(State state) {
		return stateRepository.save(state);
	}

	@Override
	public void deleteStateById(Long id) {
		State state = getStateById(id);
		stateRepository.delete(state);
	}

	@Override
	public State getStateById(Long id) {
		return stateRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
				messageByLocaleService.getMessage("err.state.not.found", id.toString())));
	}

	@Override
	public List<State> getAllStates() {
		return stateRepository.findAll();
	}

	@Override
	public State createState(@Valid State state) {
		return stateRepository.save(state);
	}



}
