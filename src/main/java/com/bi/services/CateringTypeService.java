package com.bi.services;

import java.util.List;

import javax.validation.Valid;

import com.bi.model.catering.CateringType;
import com.bi.utils.PaginationConfig;

public interface CateringTypeService {

	CateringType createCateringType(@Valid CateringType cateringType);

	List<CateringType> getAllCateringType(String search, PaginationConfig paginationConfig);

	CateringType getCateringTypeById(Long id);

	CateringType update(CateringType cateringType);

	void deleteCateringTypeById(Long id);


}
