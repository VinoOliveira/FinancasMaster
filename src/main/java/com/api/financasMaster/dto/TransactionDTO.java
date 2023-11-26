package com.api.financasMaster.dto;

import com.api.financasMaster.domain.transaction.TransactionType;
import java.math.BigDecimal;

public record TransactionDTO(Integer userId,BigDecimal amount,String date,TransactionType transactionType)  {


}