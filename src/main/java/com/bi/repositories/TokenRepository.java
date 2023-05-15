package com.bi.repositories;

import com.bi.model.Document.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long>, QuerydslPredicateExecutor<Token>{

}
