package com.bi.services;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bi.exception.EntityNotFoundException;
import com.bi.model.Country;
import com.bi.repositories.CountryRepository;

@Service
public class CountryServiceImpl implements CountryService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CountryServiceImpl.class);

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private MessageByLocaleService messageByLocaleService;

	@Override
	public Country createCountry(@Valid Country country) {
		return countryRepository.save(country);
	}

	@Override
	public List<Country> getAllCountry() {
		return countryRepository.findAll();
	}

	@Override
	public Country getCountryById(Long id) {
		return countryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
				messageByLocaleService.getMessage("err.country.not.found", id.toString())));
	}

	@Override
	public Country update(Country country) {
		return countryRepository.save(country);
	}

	@Override
	public void deleteCountryById(Long id) {
		Country country = getCountryById(id);
		countryRepository.delete(country);
		
	}


}
