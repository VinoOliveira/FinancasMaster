package com.api.financasMaster.domain.transaction;

import com.api.financasMaster.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name="transactions")
@Table(name="transactions")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User userID;
    private BigDecimal amount;
    private LocalDateTime timestemp;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    public Transaction() {}
}
