package com.bi.services;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bi.exception.EntityNotFoundException;
import com.bi.model.franchise.FranchiseLiquidAsset;
import com.bi.repositories.FranchiseLiquidAssetsRepository;

@Service
public class FranchiseLiquidAssetsServiceImpl implements FranchiseLiquidAssetsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FranchiseLiquidAssetsServiceImpl.class);

	@Autowired
	private FranchiseLiquidAssetsRepository franchiseLiquidAssetsRepository;

	@Autowired
	private MessageByLocaleService messageByLocaleService;

	@Override
	public FranchiseLiquidAsset createFranchiseLiquidAssets(@Valid FranchiseLiquidAsset franchiseLiquidAsset) {
		return franchiseLiquidAssetsRepository.save(franchiseLiquidAsset);
	}

	@Override
	public List<FranchiseLiquidAsset> getAllFranchiseLiquidAssets() {
	return franchiseLiquidAssetsRepository.findAll();
	}

	@Override
	public FranchiseLiquidAsset getFranchiseLiquidAssetsById(Long id) {
		return franchiseLiquidAssetsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
				messageByLocaleService.getMessage("err.franchise.liquid.assets.not.found", id.toString())));
	}

	@Override
	public FranchiseLiquidAsset update(FranchiseLiquidAsset franchiseLiquidAsset) {
		return franchiseLiquidAssetsRepository.save(franchiseLiquidAsset);
	}

	@Override
	public void deleteFranchiseLiquidAssetsById(Long id) {
		FranchiseLiquidAsset franchiseLiquidAsset = getFranchiseLiquidAssetsById(id);
		franchiseLiquidAssetsRepository.delete(franchiseLiquidAsset);
	}

	


}
