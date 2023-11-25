package com.api.financasMaster.dto;

import com.api.financasMaster.domain.user.User;

import java.math.BigDecimal;
import java.util.List;

public record UserTransactionDTO(User user,BigDecimal totalProfitToday, BigDecimal totalExpenseToday,List<TransactionDTO> listTransactions) {

}
