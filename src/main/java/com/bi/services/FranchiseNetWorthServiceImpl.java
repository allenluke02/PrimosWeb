package com.bi.services;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bi.exception.EntityNotFoundException;
import com.bi.model.franchise.FranchiseNetWorth;
import com.bi.repositories.FranchiseNetWorthRepository;

@Service
public class FranchiseNetWorthServiceImpl implements FranchiseNetWorthService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FranchiseNetWorthServiceImpl.class);

	@Autowired
	private FranchiseNetWorthRepository franchiseNetWorthRepository;

	@Autowired
	private MessageByLocaleService messageByLocaleService;

	@Override
	public FranchiseNetWorth createFranchiseNetWorths(@Valid FranchiseNetWorth franchiseNetWorth) {
		return franchiseNetWorthRepository.save(franchiseNetWorth);
	}

	@Override
	public List<FranchiseNetWorth> getAllFranchiseNetWorths() {
		return  franchiseNetWorthRepository.findAll();
	}

	@Override
	public FranchiseNetWorth getFranchiseNetWorthsById(Long id) {
		return franchiseNetWorthRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
				messageByLocaleService.getMessage("err.franchise.networth.not.found", id.toString())));
	}

	@Override
	public FranchiseNetWorth update(FranchiseNetWorth franchiseNetWorth) {
		return franchiseNetWorthRepository.save(franchiseNetWorth);
	}

	@Override
	public void deleteFranchiseNetWorthsById(Long id) {
		FranchiseNetWorth franchiseNetWorth = getFranchiseNetWorthsById(id);
		franchiseNetWorthRepository.delete(franchiseNetWorth);
		
	}

	

	


}
