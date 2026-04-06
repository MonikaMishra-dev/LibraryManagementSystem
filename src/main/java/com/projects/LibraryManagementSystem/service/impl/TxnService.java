package com.projects.LibraryManagementSystem.service.impl;

import com.projects.LibraryManagementSystem.dto.TxnRequest;
import com.projects.LibraryManagementSystem.enums.TxnStatus;
import com.projects.LibraryManagementSystem.exception.BookException;
import com.projects.LibraryManagementSystem.exception.UserException;
import com.projects.LibraryManagementSystem.model.Book;
import com.projects.LibraryManagementSystem.model.Txn;
import com.projects.LibraryManagementSystem.model.User;
import com.projects.LibraryManagementSystem.repository.TxnRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
public class TxnService {

    @Autowired
    TxnRepository txnRepository;

    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

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

}
