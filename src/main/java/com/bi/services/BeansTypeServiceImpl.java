package com.bi.services;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bi.exception.EntityNotFoundException;
import com.bi.model.catering.BeansType;
import com.bi.model.catering.QBeansType;
import com.bi.repositories.BeansTypeRepository;
import com.bi.utils.PaginationConfig;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.BooleanOperation;
import com.querydsl.core.types.dsl.Expressions;

@Service
public class BeansTypeServiceImpl extends SearchServiceImpl<BeansType, BeansTypeRepository> implements BeansTypeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BeansTypeServiceImpl.class);

	@Autowired
	private BeansTypeRepository beansTypeRepository;
	

	@Autowired
	private MessageByLocaleService messageByLocaleService;


	@Override
	public BeansType createBeansType(@Valid BeansType beansType) {
		return beansTypeRepository.save(beansType);
	}


	@Override
	public List<BeansType> getAllBeansType(String search, Long cateringTypeId, PaginationConfig paginationConfig) {
		BooleanExpression predicate = Expressions.TRUE.isTrue();
		if (cateringTypeId != null) {
			predicate = predicate.and(QBeansType.beansType.cateringTypes.any().id.eq(cateringTypeId));
		}
		return search(search, BeansType.class, paginationConfig, null, (BooleanOperation) predicate, null);
	}


	@Override
	public BeansType getBeansTypeById(Long id) {
		return beansTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
				messageByLocaleService.getMessage("err.beans.type.not.found", id.toString())));
	}


	@Override
	public BeansType update(BeansType beansType) {
		return beansTypeRepository.save(beansType);
	}


	@Override
	public void deleteBeansTypeById(Long id) {
		BeansType catering = getBeansTypeById(id);
		beansTypeRepository.delete(catering);
	}

}
