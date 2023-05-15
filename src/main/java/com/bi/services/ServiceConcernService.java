package com.bi.services;

import java.util.List;

import javax.validation.Valid;

import com.bi.model.ServiceConcern;
import com.bi.utils.PaginationConfig;

public interface ServiceConcernService {

	ServiceConcern createServiceConcern(@Valid ServiceConcern serviceConcern);

	List<ServiceConcern> getAllServiceConcerns(String search, PaginationConfig paginationConfig);

	ServiceConcern getServiceConcernById(Long id);

	ServiceConcern update(ServiceConcern serviceConcern);

	void deleteServiceConcernById(Long id);

}
