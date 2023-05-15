package com.bi.services;

import java.util.List;

import javax.validation.Valid;

import com.bi.model.franchise.FranchiseInfo;
import com.bi.model.franchise.FranchiseLiquidAsset;

public interface FranchiseLiquidAssetsService {

	FranchiseLiquidAsset createFranchiseLiquidAssets(@Valid FranchiseLiquidAsset franchiseLiquidAsset);

	List<FranchiseLiquidAsset> getAllFranchiseLiquidAssets();

	FranchiseLiquidAsset getFranchiseLiquidAssetsById(Long id);

	FranchiseLiquidAsset update(FranchiseLiquidAsset franchiseLiquidAsset);

	void deleteFranchiseLiquidAssetsById(Long id);


}
