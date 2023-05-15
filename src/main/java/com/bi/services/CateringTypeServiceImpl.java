package com.bi.services;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bi.exception.EntityNotFoundException;
import com.bi.model.catering.CateringType;
import com.bi.repositories.CateringTypeRepository;
import com.bi.utils.PaginationConfig;

@Service
public class CateringTypeServiceImpl extends SearchServiceImpl<CateringType, CateringTypeRepository> implements CateringTypeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CateringTypeServiceImpl.class);

	@Autowired
	private CateringTypeRepository cateringTypeRepository;

	@Autowired
	private MessageByLocaleService messageByLocaleService;

	@Override
	public CateringType createCateringType(@Valid CateringType cateringType) {
		return cateringTypeRepository.save(cateringType);
	}

	@Override
	public List<CateringType> getAllCateringType(String search, PaginationConfig paginationConfig) {
		return search(search, CateringType.class, paginationConfig, null, null,null);
	}

	@Override
	public CateringType getCateringTypeById(Long id) {
		return cateringTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
				messageByLocaleService.getMessage("err.catering.type.not.found", id.toString())));
	}

	@Override
	public CateringType update(CateringType cateringType) {
		return cateringTypeRepository.save(cateringType);
	}

	@Override
	public void deleteCateringTypeById(Long id) {
		CateringType cateringType = getCateringTypeById(id);
		cateringTypeRepository.delete(cateringType);
		
	}


}
