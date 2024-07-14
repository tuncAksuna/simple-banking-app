package com.tuncode.bankingapp.repository;

import com.tuncode.bankingapp.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
