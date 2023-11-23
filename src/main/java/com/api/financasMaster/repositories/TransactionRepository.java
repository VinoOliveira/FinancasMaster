package com.api.financasMaster.repositories;

import com.api.financasMaster.domain.transaction.Transaction;
import com.api.financasMaster.domain.transaction.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Transaction findByUserId(Integer id);
    List<Transaction> findByTransactionDateAndType(LocalDate date, TransactionType type);
}
