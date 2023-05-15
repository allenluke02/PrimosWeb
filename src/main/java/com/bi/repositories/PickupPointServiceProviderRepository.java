package com.bi.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bi.model.location.PickupPointServiceProvider;


@Transactional
@Repository
public interface PickupPointServiceProviderRepository extends JpaRepository<PickupPointServiceProvider, Long>,QuerydslPredicateExecutor<PickupPointServiceProvider>  {

}