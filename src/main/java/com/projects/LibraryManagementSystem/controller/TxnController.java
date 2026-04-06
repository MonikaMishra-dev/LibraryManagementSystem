package com.projects.LibraryManagementSystem.controller;

import com.projects.LibraryManagementSystem.dto.TxnRequest;
import com.projects.LibraryManagementSystem.dto.TxnReturnRequest;
import com.projects.LibraryManagementSystem.exception.BookException;
import com.projects.LibraryManagementSystem.exception.UserException;
import com.projects.LibraryManagementSystem.service.impl.TxnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/txn")
@Validated
public class TxnController {

    @Autowired
    TxnService txnService;

    @PostMapping("/issue")
    public String crateTxn(@RequestBody TxnRequest txnRequest) throws UserException, BookException {
     return txnService.createTxn(txnRequest);
    }

    @PutMapping("/return")
    public Double returnTxn(@RequestBody TxnReturnRequest txnReturnRequest) throws BookException, UserException {
        return txnService.returnTxn(txnReturnRequest);
    }

}

//issue books
//return books
