package com.bi.services;

import java.util.List;

import com.bi.model.catering.Catering;
import com.bi.model.catering.CateringPrice;
import com.bi.utils.PaginationConfig;

public interface CateringPriceService {

	CateringPrice createCateringPrice(CateringPrice CateringPrice);

	CateringPrice getCateringPriceById(Long id);

	List<CateringPrice> getAllCateringPrice(String search, PaginationConfig paginationConfig);

	CateringPrice update(CateringPrice CateringPrice);

	void deleteCateringPriceById(Long id);

}
