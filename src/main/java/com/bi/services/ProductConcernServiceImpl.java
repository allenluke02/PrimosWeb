package com.bi.services;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bi.exception.EntityNotFoundException;
import com.bi.model.ProductConcern;
import com.bi.model.hire.Job;
import com.bi.repositories.JobRepository;
import com.bi.repositories.ProductConcernRepository;
import com.bi.utils.PaginationConfig;
import com.querydsl.core.types.Ops;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.BooleanOperation;
import com.querydsl.core.types.dsl.Expressions;

@Service
public class ProductConcernServiceImpl  extends SearchServiceImpl<ProductConcern, ProductConcernRepository> implements ProductConcernService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductConcernServiceImpl.class);

	@Autowired
	private ProductConcernRepository productConcernRepository;

	@Autowired
	private MessageByLocaleService messageByLocaleService;

	@Override
	public ProductConcern createProductConcern(@Valid ProductConcern productConcern) {
		return productConcernRepository.save(productConcern);
	}

	@Override
	public List<ProductConcern> getAllProductConcerns(String search, PaginationConfig paginationConfig) {
		return search(search, ProductConcern.class, paginationConfig, null,  null,null);
	}

	@Override
	public ProductConcern getProductConcernById(Long id) {
		return productConcernRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
				messageByLocaleService.getMessage("err.product.concern.not.found", id.toString())));
	}

	@Override
	public ProductConcern update(ProductConcern productConcern) {
		return productConcernRepository.save(productConcern);
	}

	@Override
	public void deleteProductConcernById(Long id) {
		ProductConcern productConcern = getProductConcernById(id);
		productConcernRepository.delete(productConcern);
	}

}
