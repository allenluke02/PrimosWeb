package com.bi.services;

import javax.validation.Valid;

import com.bi.model.franchise.Franchise;
import com.bi.utils.PaginationConfig;
import com.bi.vo.ApiListResponseVo;

public interface FranchiseService {

	Franchise createFranchise(@Valid Franchise franchise);

	ApiListResponseVo<Franchise> getAllFranchises(String search, PaginationConfig paginationConfig);

	Franchise getFranchisesById(Long id);

	Franchise update(Franchise franchise);

	void deleteFranchisesById(Long id);

}
