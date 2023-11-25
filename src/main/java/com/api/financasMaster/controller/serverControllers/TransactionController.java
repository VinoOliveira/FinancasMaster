package com.api.financasMaster.controller.serverControllers;

import com.api.financasMaster.domain.transaction.Transaction;
import com.api.financasMaster.dto.TransactionDTO;
import com.api.financasMaster.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping("/register")
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transaction){
        try{
            Transaction newTransaction = transactionService.validateRegisterTransaction(transaction);
            return new ResponseEntity<>(newTransaction,HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
