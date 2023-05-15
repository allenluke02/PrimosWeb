package com.bi.services;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bi.exception.EntityNotFoundException;
import com.bi.model.HealthConcern;
import com.bi.repositories.HealthConcernRepository;
import com.bi.utils.PaginationConfig;

@Service
public class HealthConcernServiceImpl  extends SearchServiceImpl<HealthConcern, HealthConcernRepository> implements HealthConcernService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HealthConcernServiceImpl.class);

	@Autowired
	private HealthConcernRepository healthConcernRepository;

	@Autowired
	private MessageByLocaleService messageByLocaleService;

	@Override
	public HealthConcern createHealthConcern(@Valid HealthConcern healthConcern) {
		return healthConcernRepository.save(healthConcern);
	}

	@Override
	public List<HealthConcern> getAllHealthConcerns(String search, PaginationConfig paginationConfig) {
		return search(search, HealthConcern.class, paginationConfig, null,  null,null);
	}

	@Override
	public HealthConcern getHealthConcernById(Long id) {
		return healthConcernRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
				messageByLocaleService.getMessage("err.healthconcern.not.found", id.toString())));
	}

	@Override
	public HealthConcern update(HealthConcern healthConcern) {
		return healthConcernRepository.save(healthConcern);
	}

	@Override
	public void deleteHealthConcernById(Long id) {
		HealthConcern healthConcern = getHealthConcernById(id);
		healthConcernRepository.delete(healthConcern);
		
	}

}
