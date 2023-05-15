package com.bi.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bi.model.catering.CateringType;


@Transactional
@Repository
public interface CateringTypeRepository extends JpaRepository<CateringType, Long>,QuerydslPredicateExecutor<CateringType>  {

}