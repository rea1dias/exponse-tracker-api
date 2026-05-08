package com.disa.expensetrackerapi.repo;

import com.disa.expensetrackerapi.domain.entity.Transaction;
import com.disa.expensetrackerapi.enums.CategoryType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@Qualifier
public interface TransactionRepository extends JpaRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction> {

    List<Transaction> findByUserId(Long userId);

    @Query("""
    SELECT t FROM Transaction t
    JOIN t.category c
    WHERE t.user.id = :userId
      AND (COALESCE(:type, t.category.type) = t.category.type)
      AND (COALESCE(:categoryId, c.id) = c.id)
      AND (COALESCE(:from, t.occurredAt) <= t.occurredAt)
      AND (COALESCE(:to, t.occurredAt) >= t.occurredAt)
      """)

    List<Transaction> findTransaction(
            @Param("userId") Long userId,
            @Param("type") CategoryType type,
            @Param("categoryId") Long categoryId,
            @Param("from") LocalDate from,
            @Param("to") LocalDate to
    );


}
