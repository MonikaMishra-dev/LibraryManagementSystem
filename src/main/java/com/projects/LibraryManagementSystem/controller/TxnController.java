package com.projects.LibraryManagementSystem.controller;

import com.projects.LibraryManagementSystem.dto.requestdtos.TxnRequest;
import com.projects.LibraryManagementSystem.dto.requestdtos.TxnReturnRequest;
import com.projects.LibraryManagementSystem.exception.BookException;
import com.projects.LibraryManagementSystem.exception.UserException;
import com.projects.LibraryManagementSystem.service.impl.TxnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/txn")
@Validated
public class TxnController {

    @Autowired
    TxnService txnService;

    @PostMapping("/issue")
    public ResponseEntity<String> crateTxn(@RequestBody @Validated TxnRequest txnRequest) throws UserException, BookException {
     String txnId = txnService.createTxn(txnRequest);
     if(txnId != null || !txnId.isEmpty())
         return new ResponseEntity<>(txnId, HttpStatus.OK);
     else
         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @PutMapping("/return")
    public Double returnTxn(@RequestBody TxnReturnRequest txnReturnRequest) throws BookException, UserException {
        return txnService.returnTxn(txnReturnRequest);
    }

}

//issue books
//return books
