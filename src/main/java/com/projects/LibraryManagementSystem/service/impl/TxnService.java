package com.projects.LibraryManagementSystem.service.impl;

import com.projects.LibraryManagementSystem.dto.requestdtos.TxnRequest;
import com.projects.LibraryManagementSystem.dto.requestdtos.TxnReturnRequest;
import com.projects.LibraryManagementSystem.enums.TxnStatus;
import com.projects.LibraryManagementSystem.exception.BookException;
import com.projects.LibraryManagementSystem.exception.TxnException;
import com.projects.LibraryManagementSystem.exception.UserException;
import com.projects.LibraryManagementSystem.model.Book;
import com.projects.LibraryManagementSystem.model.Txn;
import com.projects.LibraryManagementSystem.model.User;
import com.projects.LibraryManagementSystem.repository.TxnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TxnService {

    @Autowired
    TxnRepository txnRepository;

    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    @Value("${user.valid.days}")
    int validUpto;

    @Value("${user.delayed.finePerDay}")
    int finePerDay;

    @Transactional(rollbackFor = {BookException.class,UserException.class})
    public String createTxn(TxnRequest txnRequest) throws UserException, BookException {
        // user is trying to get the book, check if user is valid or not
        User userFromDb = userService.checkForValidUser(txnRequest.getUserEmail());
        if (userFromDb == null) {
            throw new UserException("User is not valid");
        }

        // book no that user is requesting for is present in my library or not
        Book bookFromDb = bookService.checkForValidBook(txnRequest.getBookNo());
        if (bookFromDb == null) {
            throw new BookException("Book is not valid");
        }

        // book requested by user should not be assigned to some user
        if (bookFromDb.getUser() != null) {
            throw new BookException("Book is not free to be issued");
        }
        return createTxn(userFromDb,bookFromDb);
    }

    @Transactional
    private String createTxn(User userFromDb, Book bookFromDb) {
        String txnId = UUID.randomUUID().toString();
        Txn txn = Txn.builder().
                user(userFromDb).
                book(bookFromDb).
                txnId(txnId).
                txnStatus(TxnStatus.ISSUED).
                issuedDate(new Date()).
                build();
        txnRepository.save(txn);
        bookService.markBookAsUnavailable(bookFromDb, userFromDb);
        return txnId;
    }

    @Transactional(rollbackFor = {UserException.class,BookException.class})
    public Double returnTxn(TxnReturnRequest txnRequest) throws UserException, BookException {
        // user is trying to get the book, check if user is valid or not
        User userFromDb = userService.checkForValidUser(txnRequest.getUserEmail());
        if (userFromDb == null) {
            throw new UserException("User is not valid");
        }

        // book no that user is requesting for is present in my library or not
        Book bookFromDb = bookService.checkForValidBook(txnRequest.getBookNo());
        if (bookFromDb == null) {
            throw new BookException("Book is not valid");
        }

        //check is the book sent in the request is issued or not and also if is issued
        // it is issued to the same user who is making the request or not
        if(bookFromDb.getUser() != null && bookFromDb.getUser().equals(userFromDb)){
         Txn txnFromDb = txnRepository.findByTxnId(txnRequest.getTxnId());
         if(txnFromDb == null)
             throw new TxnException("No transaction has been found in my db with this txn id");
         Double amount = calculateSettlementAmount(txnFromDb,bookFromDb);
         if(amount == bookFromDb.getSecurityAmount())
             txnFromDb.setTxnStatus(TxnStatus.RETURNED);
         else
             txnFromDb.setTxnStatus(TxnStatus.FINED);
         txnFromDb.setSettlementAmount(amount);
         txnFromDb.setSubmittedDate(new Date());
         //mark the book as available
         bookFromDb.setUser(null);

         txnRepository.save(txnFromDb);
         return amount;

        }
        else{
            throw new TxnException("Book is assigned to someone else or not at all assigned");

        }

    }

    public  Double calculateSettlementAmount(Txn txnFromDb, Book bookFromDb) {
        long issueTime = txnFromDb.getIssuedDate().getTime();
        long returnTime = System.currentTimeMillis();
        long diff = returnTime - issueTime;
        int daysPassed = (int)TimeUnit.DAYS.convert(diff,TimeUnit.MILLISECONDS);
        if(daysPassed>validUpto){
            int fineAmount = (daysPassed-validUpto)*finePerDay;
            return bookFromDb.getSecurityAmount() - fineAmount;
        }
      return bookFromDb.getSecurityAmount();
    }
}
