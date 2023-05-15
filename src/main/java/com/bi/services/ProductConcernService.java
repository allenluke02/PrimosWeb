package com.bi.services;

import java.util.List;

import javax.validation.Valid;

import com.bi.model.ProductConcern;
import com.bi.utils.PaginationConfig;

public interface ProductConcernService {

	ProductConcern createProductConcern(@Valid ProductConcern productConcern);

	List<ProductConcern> getAllProductConcerns(String search, PaginationConfig paginationConfig);

	ProductConcern getProductConcernById(Long id);

	ProductConcern update(ProductConcern productConcern);

	void deleteProductConcernById(Long id);

}
