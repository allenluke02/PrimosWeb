package com.bi.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bi.model.catering.CateringPrice;


@Transactional
@Repository
public interface CateringPriceRepository extends JpaRepository<CateringPrice, Long>,QuerydslPredicateExecutor<CateringPrice>  {

}