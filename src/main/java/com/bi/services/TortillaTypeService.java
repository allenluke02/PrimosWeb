package com.bi.services;

import java.util.List;

import javax.validation.Valid;

import com.bi.model.catering.TortillaType;
import com.bi.utils.PaginationConfig;

public interface TortillaTypeService {

	TortillaType createTortillaType(@Valid TortillaType tortillaType);

	List<TortillaType> getAllTortillaType(String search, Long cateringTypeId, PaginationConfig paginationConfig);

	TortillaType getTortillaTypeById(Long id);

	TortillaType update(TortillaType tortillaType);

	void deleteTortillaTypeById(Long id);



}
