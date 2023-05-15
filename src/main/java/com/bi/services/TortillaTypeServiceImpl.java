package com.bi.services;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bi.exception.EntityNotFoundException;
import com.bi.model.catering.QTortillaType;
import com.bi.model.catering.TortillaType;
import com.bi.repositories.TortillaTypeRepository;
import com.bi.utils.PaginationConfig;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.BooleanOperation;
import com.querydsl.core.types.dsl.Expressions;

@Service
public class TortillaTypeServiceImpl extends SearchServiceImpl<TortillaType, TortillaTypeRepository> implements TortillaTypeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TortillaTypeServiceImpl.class);

	@Autowired
	private TortillaTypeRepository tortillaTypeRepository;
	

	@Autowired
	private MessageByLocaleService messageByLocaleService;


	@Override
	public TortillaType createTortillaType(@Valid TortillaType tortillaType) {
		return tortillaTypeRepository.save(tortillaType);
	}


	@Override
	public List<TortillaType> getAllTortillaType(String search, Long cateringTypeId,
			PaginationConfig paginationConfig) {
		BooleanExpression predicate = Expressions.TRUE.isTrue();
		if (cateringTypeId != null) {
			predicate = predicate.and(QTortillaType.tortillaType.cateringTypes.any().id.eq(cateringTypeId));
		}
		return search(search, TortillaType.class, paginationConfig, null, (BooleanOperation) predicate, null);
	}


	@Override
	public TortillaType getTortillaTypeById(Long id) {
		return tortillaTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
				messageByLocaleService.getMessage("err.tortilla.type.not.found", id.toString())));
	}


	@Override
	public TortillaType update(TortillaType tortillaType) {
		return tortillaTypeRepository.save(tortillaType);
	}


	@Override
	public void deleteTortillaTypeById(Long id) {
		TortillaType tortillaType = getTortillaTypeById(id);
		tortillaTypeRepository.delete(tortillaType);
	}

}
