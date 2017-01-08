package com.seorgy.repository;

import com.seorgy.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: NotePad.by
 * Date: 1/8/2017.
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
