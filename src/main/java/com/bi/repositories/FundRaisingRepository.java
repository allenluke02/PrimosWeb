package com.bi.repositories;

import com.bi.model.FundRaising;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface FundRaisingRepository extends JpaRepository<FundRaising, Long>, QuerydslPredicateExecutor<FundRaising> {
}
