package com.bi.services;

import java.util.List;

import javax.validation.Valid;

import com.bi.model.location.County;

public interface CountyService {

	County createCounty(@Valid County county);

	List<County> getAllCounties();

	County getCountyById(Long id);

	County update(County county);

	void deleteCountiesById(Long id);

}
