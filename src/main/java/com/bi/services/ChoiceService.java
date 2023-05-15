package com.bi.services;

import java.util.List;

import javax.validation.Valid;

import com.bi.model.catering.Choice;
import com.bi.utils.PaginationConfig;

public interface ChoiceService {

	Choice createChoice(@Valid Choice choice);

	List<Choice> getAllChoice(String search, Long cateringTypeId, PaginationConfig paginationConfig);

	Choice getChoiceById(Long id);

	Choice update(Choice choice);

	void deleteChoiceById(Long id);



}
