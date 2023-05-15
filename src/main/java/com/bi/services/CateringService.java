package com.bi.services;

import com.bi.model.catering.Catering;
import com.bi.utils.PaginationConfig;
import com.bi.vo.ApiListResponseVo;

public interface CateringService {

	Catering createCatering(Catering catering);

	ApiListResponseVo<Catering> getAllCatering(String search, PaginationConfig paginationConfig);

	Catering getCateringById(Long id);

	Catering update(Catering catering);

	void deleteCateringById(Long id);

}
