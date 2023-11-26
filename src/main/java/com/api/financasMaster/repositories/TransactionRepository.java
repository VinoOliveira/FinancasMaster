package com.api.financasMaster.repositories;

import com.api.financasMaster.domain.transaction.Transaction;
import com.api.financasMaster.domain.transaction.TransactionType;
import com.api.financasMaster.dto.TransactionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List <Transaction> findByUserId(Integer id);
    @Query("SELECT t FROM transactions t WHERE t.date = :date AND t.transactionType = :transactionType AND t.user.id = :userId")
    List<Transaction> findByDateAndTransactionType(
            @Param("date") LocalDate date,
            @Param("transactionType") TransactionType transactionType,
            @Param("userId") Integer userId
    );

}
