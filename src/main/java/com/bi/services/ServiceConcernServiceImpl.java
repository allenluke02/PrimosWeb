package com.bi.services;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bi.exception.EntityNotFoundException;
import com.bi.model.ServiceConcern;
import com.bi.repositories.ServiceConcernRepository;
import com.bi.utils.PaginationConfig;

@Service
public class ServiceConcernServiceImpl  extends SearchServiceImpl<ServiceConcern, ServiceConcernRepository> implements ServiceConcernService{

	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceConcernServiceImpl.class);

	@Autowired
	private ServiceConcernRepository serviceConcernRepository;

	@Autowired
	private MessageByLocaleService messageByLocaleService;

	
	@Override
	public ServiceConcern createServiceConcern(@Valid ServiceConcern serviceConcern) {
		return serviceConcernRepository.save(serviceConcern);
	}

	@Override
	public List<ServiceConcern> getAllServiceConcerns(String search, PaginationConfig paginationConfig) {
		return search(search, ServiceConcern.class, paginationConfig, null,  null,null);
	}

	@Override
	public ServiceConcern getServiceConcernById(Long id) {
		return serviceConcernRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
				messageByLocaleService.getMessage("err.service.concern.not.found", id.toString())));
	}

	@Override
	public ServiceConcern update(ServiceConcern serviceConcern) {
		return serviceConcernRepository.save(serviceConcern);
	}

	@Override
	public void deleteServiceConcernById(Long id) {
		ServiceConcern serviceConcern = getServiceConcernById(id);
		serviceConcernRepository.delete(serviceConcern);
	}

}
