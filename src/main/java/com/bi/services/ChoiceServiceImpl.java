package com.bi.services;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bi.exception.EntityNotFoundException;
import com.bi.model.catering.Choice;
import com.bi.model.catering.QChoice;
import com.bi.repositories.ChoiceRepository;
import com.bi.utils.PaginationConfig;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.BooleanOperation;
import com.querydsl.core.types.dsl.Expressions;

@Service
public class ChoiceServiceImpl extends SearchServiceImpl<Choice, ChoiceRepository> implements ChoiceService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ChoiceServiceImpl.class);

	@Autowired
	private ChoiceRepository choiceRepository;
	
	@Autowired
	private MessageByLocaleService messageByLocaleService;


	@Override
	public Choice createChoice(@Valid Choice choice) {
		return choiceRepository.save(choice);
	}


	@Override
	public List<Choice> getAllChoice(String search, Long cateringTypeId, PaginationConfig paginationConfig) {
		BooleanExpression predicate = Expressions.TRUE.isTrue();
		if (cateringTypeId != null) {
			predicate = predicate.and(QChoice.choice.cateringTypes.any().id.eq(cateringTypeId));
		}
		return search(search, Choice.class, paginationConfig, null, (BooleanOperation) predicate, null);
	
	}


	@Override
	public Choice getChoiceById(Long id) {
		return choiceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
				messageByLocaleService.getMessage("err.choice.type.not.found", id.toString())));
	}


	@Override
	public Choice update(Choice choice) {
		return choiceRepository.save(choice);
	}


	@Override
	public void deleteChoiceById(Long id) {
		Choice choice = getChoiceById(id);
		choiceRepository.delete(choice);
	}

}
