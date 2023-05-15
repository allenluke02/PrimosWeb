package com.bi.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bi.exception.EntityNotFoundException;
import com.bi.model.location.County;
import com.bi.model.location.QCounty;
import com.bi.repositories.CountyRepository;

@Service
public class CountyServiceImpl implements CountyService{
	
	@Autowired
	CountyRepository countyRepository;
	
	@Autowired
	private MessageByLocaleService messageByLocaleService;

	@Override
	public County createCounty(@Valid County county) {
		return countyRepository.save(county);
	}

	@Override
	public List<County> getAllCounties() {
		return (List<County>) countyRepository.findAll(QCounty.county.id.asc());
	}

	@Override
	public County getCountyById(Long id) {
		return countyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
				messageByLocaleService.getMessage("err.county.not.found", id.toString())));
	}

	@Override
	public County update(County county) {
		return countyRepository.save(county);
	}

	@Override
	public void deleteCountiesById(Long id) {
		County county = getCountyById(id);
		countyRepository.delete(county);
		
	}

}
