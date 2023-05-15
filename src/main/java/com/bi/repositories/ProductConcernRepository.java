package com.bi.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bi.model.ProductConcern;

import io.swagger.annotations.Api;


@Transactional
@Repository
@Api(value="Fetches record of User by login id")
public interface ProductConcernRepository extends JpaRepository<ProductConcern, Long>,QuerydslPredicateExecutor<ProductConcern>  {

}