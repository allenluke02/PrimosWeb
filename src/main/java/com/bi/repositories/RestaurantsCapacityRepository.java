package com.bi.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bi.model.Country;
import com.bi.model.franchise.RestaurantsCapacity;


@Transactional
@Repository
public interface RestaurantsCapacityRepository extends JpaRepository<RestaurantsCapacity, Long>,QuerydslPredicateExecutor<RestaurantsCapacity>  {

}