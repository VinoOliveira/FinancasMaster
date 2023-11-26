package com.api.financasMaster.domain.transaction;

import com.api.financasMaster.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private User user;
    private BigDecimal amount;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
}

