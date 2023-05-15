package com.bi.services;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bi.exception.EntityNotFoundException;
import com.bi.model.franchise.FranchiseInfo;
import com.bi.repositories.FranchiseInfoRepository;

@Service
public class FranchiseInfoServiceImpl implements FranchiseInfoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FranchiseInfoServiceImpl.class);

	@Autowired
	private FranchiseInfoRepository franchiseInfoRepository;

	@Autowired
	private MessageByLocaleService messageByLocaleService;

	@Override
	public FranchiseInfo createFranchiseInfo(@Valid FranchiseInfo franchiseInfo) {
		return franchiseInfoRepository.save(franchiseInfo);
	}

	@Override
	public List<FranchiseInfo> getAllFranchiseInfos() {
		return franchiseInfoRepository.findAll();
	}

	@Override
	public FranchiseInfo getFranchiseInfosById(Long id) {
		return franchiseInfoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
				messageByLocaleService.getMessage("err.franchise.info.not.found", id.toString())));
	}

	@Override
	public FranchiseInfo update(FranchiseInfo franchiseInfo) {
		return franchiseInfoRepository.save(franchiseInfo);
	}

	@Override
	public void deleteFranchiseInfosById(Long id) {
		FranchiseInfo franchiseInfo = getFranchiseInfosById(id);
		franchiseInfoRepository.delete(franchiseInfo);
		
	}


}
