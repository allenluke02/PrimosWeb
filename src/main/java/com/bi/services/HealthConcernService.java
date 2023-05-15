package com.bi.services;

import java.util.List;

import javax.validation.Valid;

import com.bi.model.HealthConcern;
import com.bi.utils.PaginationConfig;

public interface HealthConcernService {

	HealthConcern createHealthConcern(@Valid HealthConcern healthConcern);

	List<HealthConcern> getAllHealthConcerns(String search, PaginationConfig paginationConfig);

	HealthConcern getHealthConcernById(Long id);

	HealthConcern update(HealthConcern healthConcern);

	void deleteHealthConcernById(Long id);

}
