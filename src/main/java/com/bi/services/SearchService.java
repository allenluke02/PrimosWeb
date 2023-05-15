package com.bi.services;

import java.util.List;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.bi.utils.PaginationConfig;
import com.querydsl.core.types.Ops;
import com.querydsl.core.types.dsl.BooleanOperation;

public interface SearchService<T, R extends QuerydslPredicateExecutor<T>> {

	Iterable<T> search(String searchText, Class<T> clazz, PaginationConfig paginationConfig);

	Iterable<T> search(String searchText, Class<T> clazz[], PaginationConfig paginationConfig);
	
	List<T> search(String searchText, Class<T> clazz[], PaginationConfig paginationConfig,List<String> filteredNode,BooleanOperation booleanEx);
	
	List<T>  search(String searchText, Class<T> clazz, PaginationConfig paginationConfig, 
			List<String> filteredNode,BooleanOperation booleanAndEx,BooleanOperation booleanOrEx);

	Long count(String searchText, Class<T> clazz, List<String> filteredNode, BooleanOperation booleanAndEx,
			BooleanOperation booleanOrEx);


}
