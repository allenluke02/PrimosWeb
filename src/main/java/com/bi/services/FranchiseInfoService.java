package com.bi.services;

import java.util.List;

import javax.validation.Valid;

import com.bi.model.franchise.FranchiseInfo;

public interface FranchiseInfoService {

	FranchiseInfo createFranchiseInfo(@Valid FranchiseInfo franchiseInfo);

	List<FranchiseInfo> getAllFranchiseInfos();

	FranchiseInfo getFranchiseInfosById(Long id);

	FranchiseInfo update(FranchiseInfo franchiseInfo);

	void deleteFranchiseInfosById(Long id);

}
