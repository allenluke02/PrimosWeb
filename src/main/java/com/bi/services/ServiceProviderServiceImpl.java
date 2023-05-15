package com.bi.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bi.exception.EntityNotFoundException;
import com.bi.model.location.ServiceProvider;
import com.bi.repositories.ServiceProviderRepository;

@Service
public class ServiceProviderServiceImpl implements ServiceProviderService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FeedbackServiceImpl.class);

	@Autowired
	private ServiceProviderRepository serviceProviderRepository;

	@Autowired
	private MessageByLocaleService messageByLocaleService;

	@Override
	public ServiceProvider createServiceProvider(ServiceProvider serviceProvider) {
		return serviceProviderRepository.save(serviceProvider);
	}

	@Override
	public List<ServiceProvider> getAllServiceProviders() {
		return serviceProviderRepository.findAll();
	}

	@Override
	public ServiceProvider getServiceProviderById(Long id) {
		return serviceProviderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
				messageByLocaleService.getMessage("err.service.provider.not.found", id.toString())));
	}

	@Override
	public ServiceProvider update(ServiceProvider serviceProvider) {
		return serviceProviderRepository.save(serviceProvider);
	}

	@Override
	public void deleteServiceProvidersById(Long id) {
		ServiceProvider serviceProvider = getServiceProviderById(id);
		serviceProviderRepository.delete(serviceProvider);
	}

}
