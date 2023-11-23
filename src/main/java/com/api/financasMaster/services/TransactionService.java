package com.api.financasMaster.services;

import com.api.financasMaster.domain.transaction.Transaction;
import com.api.financasMaster.domain.transaction.TransactionType;
import com.api.financasMaster.domain.user.User;
import com.api.financasMaster.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UserService userService;

    //valida o registro de uma nova transação
    public Transaction validateRegisterTransaction(BigDecimal amount, Integer userId, TransactionType type) throws Exception {
        LocalDate localDate = LocalDate.now();
        User user = userService.findUserById(userId);
        //valida se o valor da transação é maior que zero
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("The transaction amount must be greater than zero.");
        }

        // Criação da transação com base no tipo
        else if (type == TransactionType.PROFIT) {

            //adiciona o valor da transação ao saldo do usuario
            user.setBalance(user.getBalance().add(amount));
            return new Transaction(user, amount, localDate, TransactionType.PROFIT);

        } else if (type == TransactionType.EXPENSE) {
            // Valida se o saldo do usuário é maior ou igual ao valor da transação
            if (user.getBalance().compareTo(amount) < 0) {
                //subtrai o valor da transação do saldo do usuario
                user.setBalance(user.getBalance().subtract(amount));
                throw new IllegalArgumentException("Insufficient balance to complete the transaction.");

            } else {
                return new Transaction(user, amount, localDate, TransactionType.EXPENSE);
            }
        }

        throw new IllegalArgumentException("Unsupported transaction type: " + type);
    }

    public List<Transaction> findTransactionByUserId(Integer userId) {
        List<Transaction> transactions = new ArrayList<>();
        Transaction transaction = transactionRepository.findByUserId(userId);
        return transactions;
    }

    //retorna os lucros do dia atual
    public BigDecimal calculateDailyProfits() {
        LocalDate hoje = LocalDate.now();
        List<Transaction> transacoesDoDia = transactionRepository.findByTransactionDateAndType(hoje, TransactionType.PROFIT);
        return calculateTotal(transacoesDoDia);
    }

    //retorna as despesas do dia atual
    public BigDecimal calculateDailyExpenses() {
        LocalDate hoje = LocalDate.now();
        List<Transaction> transacoesDoDia = transactionRepository.findByTransactionDateAndType(hoje, TransactionType.EXPENSE);
        return calculateTotal(transacoesDoDia);
    }

    //faz o calculo das transações
    private BigDecimal calculateTotal(List<Transaction> transacoes) {
        return transacoes.stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
