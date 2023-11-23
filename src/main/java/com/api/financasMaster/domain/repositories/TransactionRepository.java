package com.api.financasMaster.domain.repositories;

import com.api.financasMaster.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Optional<Transaction> FindByUserId(Integer id);
}
