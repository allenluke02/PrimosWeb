package com.bi.services;

import java.util.List;

import javax.validation.Valid;

import com.bi.model.franchise.FranchiseNetWorth;

public interface FranchiseNetWorthService {

	FranchiseNetWorth createFranchiseNetWorths(@Valid FranchiseNetWorth franchiseNetWorth);

	List<FranchiseNetWorth> getAllFranchiseNetWorths();

	FranchiseNetWorth getFranchiseNetWorthsById(Long id);

	FranchiseNetWorth update(FranchiseNetWorth franchiseNetWorth);

	void deleteFranchiseNetWorthsById(Long id);


}
