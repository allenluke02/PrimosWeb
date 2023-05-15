package com.bi.services;

import java.util.List;

import javax.validation.Valid;

import com.bi.model.catering.BeansType;
import com.bi.utils.PaginationConfig;

public interface BeansTypeService {

	BeansType createBeansType(@Valid BeansType beansType);

	List<BeansType> getAllBeansType(String search, Long cateringTypeId, PaginationConfig paginationConfig);

	BeansType getBeansTypeById(Long id);

	BeansType update(BeansType beansType);

	void deleteBeansTypeById(Long id);


}
