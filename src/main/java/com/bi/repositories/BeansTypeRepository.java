package com.bi.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bi.model.catering.BeansType;


@Transactional
@Repository
public interface BeansTypeRepository extends JpaRepository<BeansType, Long>,QuerydslPredicateExecutor<BeansType>  {

}