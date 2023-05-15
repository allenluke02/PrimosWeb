package com.bi.services;

import java.util.List;

import javax.validation.Valid;

import com.bi.model.location.ServiceProvider;

public interface ServiceProviderService {

	ServiceProvider createServiceProvider(@Valid ServiceProvider serviceProvider);

	List<ServiceProvider> getAllServiceProviders();

	ServiceProvider getServiceProviderById(Long id);

	ServiceProvider update(ServiceProvider serviceProvider);

	void deleteServiceProvidersById(Long id);

}
