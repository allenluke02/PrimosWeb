package com.bi.repositories;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.bi.model.location.PickupPoint;

@Transactional
@Repository
public interface PickupPointRepository extends JpaRepository<PickupPoint, Long>,QuerydslPredicateExecutor<PickupPoint>  {

}