package com.api.financasMaster.domain.transaction;

import com.api.financasMaster.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name="transactions")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private User userID;
    private BigDecimal amount;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    public Transaction() {}

    public Transaction(User userID, BigDecimal amount, LocalDate date, TransactionType transactionType) {
        this.userID = userID;
        this.amount = amount;
        this.date = date;
        this.transactionType = transactionType;
    }
}
