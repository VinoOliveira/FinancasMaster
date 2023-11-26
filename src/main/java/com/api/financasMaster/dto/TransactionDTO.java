package com.api.financasMaster.dto;

import com.api.financasMaster.domain.transaction.TransactionType;
import java.math.BigDecimal;
import java.time.LocalDate;


public record TransactionDTO(Integer userId,BigDecimal amount,LocalDate date,TransactionType transactionType)  {


}