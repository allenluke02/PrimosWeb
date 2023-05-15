package com.bi.services;

import java.util.List;

import javax.validation.Valid;

import com.bi.model.Country;

public interface CountryService {

	Country createCountry(@Valid Country country);

	List<Country> getAllCountry();

	Country getCountryById(Long id);

	Country update(Country country);

	void deleteCountryById(Long id);

}
