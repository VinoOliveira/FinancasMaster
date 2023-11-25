package com.api.financasMaster.services;

import com.api.financasMaster.domain.transaction.Transaction;
import com.api.financasMaster.domain.transaction.TransactionType;
import com.api.financasMaster.domain.user.User;
import com.api.financasMaster.dto.TransactionDTO;
import com.api.financasMaster.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserService userService;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, UserService userService) {
        this.transactionRepository = transactionRepository;
        this.userService = userService;
    }

    public Transaction validateRegisterTransaction(TransactionDTO transaction) throws Exception {
        LocalDate localDate = LocalDate.now();
        User user = userService.findUserById(transaction.userId());

        validateTransactionAmount(transaction.amount());
        Transaction newTransaction = createTransaction(user, transaction, localDate);
        processTransaction(user, transaction);

        transactionRepository.save(newTransaction);
        userService.saveUser(user);

        return newTransaction;
    }

    public List<TransactionDTO> findTransactionByUserId(Integer userId) {
        List <Transaction> transactions = transactionRepository.findByUserId(userId);
        return mapTransactionsToDTOs(transactions);
    }

    public List<TransactionDTO> findTransactionsByTypeToday(Integer userId , TransactionType profitType) {
        LocalDate date = LocalDate.now();
        TransactionType profit = profitType;
        List<Transaction> profitList = transactionRepository.findByDateAndTransactionType(date, profit);

        return mapTransactionsToDTOs(profitList);
    }

    private void validateSufficientBalance(User user, BigDecimal transactionAmount) {
        if (user.getBalance().compareTo(transactionAmount) < 0) {
            throw new IllegalArgumentException("Insufficient balance to complete the transaction.");
        }
    }

    private void validateTransactionAmount(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("The transaction amount must be greater than zero.");
        }
    }

    private Transaction createTransaction(User user, TransactionDTO transaction, LocalDate localDate) {
        return new Transaction(null, user, transaction.amount(), localDate, transaction.transactionType());
    }

    //soma o valor das transações
    public BigDecimal sum(List<TransactionDTO> list) {
        return list.stream()
                .map(TransactionDTO::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    //atualiza o saldo o usuario com base no tipo de transação feita
    private void processTransaction(User user, TransactionDTO transaction) {
        BigDecimal transactionAmount = transaction.amount();
        TransactionType transactionType = transaction.transactionType();

        if (transactionType == TransactionType.PROFIT) {
            user.setBalance(user.getBalance().add(transactionAmount));
        } else if (transactionType == TransactionType.EXPENSE) {
            validateSufficientBalance(user, transactionAmount);
            user.setBalance(user.getBalance().subtract(transactionAmount));
        }
    }

    // transforma uma lista de Transaction em TrnsactionDTO
    private List<TransactionDTO> mapTransactionsToDTOs(List<Transaction> transactions) {
        return transactions.stream()
                .map(transaction -> new TransactionDTO(
                        transaction.getId(),
                        transaction.getAmount(),
                        transaction.getDate(),
                        transaction.getTransactionType()
                ))
                .collect(Collectors.toList());
    }
}
