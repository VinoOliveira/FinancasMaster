package com.api.financasMaster.repositories;

import com.api.financasMaster.domain.transaction.Transaction;
import com.api.financasMaster.domain.transaction.TransactionType;
import com.api.financasMaster.dto.TransactionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List <Transaction> findByUserId(Integer id);
    List<Transaction> findByDateAndTransactionType(LocalDate date, TransactionType type);
}
