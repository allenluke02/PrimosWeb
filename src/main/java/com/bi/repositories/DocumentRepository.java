package com.bi.repositories;

import com.bi.model.Document.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface DocumentRepository extends JpaRepository<Document, Long>,QuerydslPredicateExecutor<Document>  {

}
