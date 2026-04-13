package com.projects.LibraryManagementSystem.service.impl;

import com.projects.LibraryManagementSystem.dto.requestdtos.TxnRequest;
import com.projects.LibraryManagementSystem.exception.BookException;
import com.projects.LibraryManagementSystem.exception.UserException;
import com.projects.LibraryManagementSystem.model.Book;
import com.projects.LibraryManagementSystem.model.Txn;
import com.projects.LibraryManagementSystem.model.User;
import com.projects.LibraryManagementSystem.repository.TxnRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.util.ReflectionUtils;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class TxnServiceTest {

    @Mock
    private TxnRepository txnRepository;

    @Mock
    private UserService userService;

    @Mock
    private BookService bookService;

    @InjectMocks
    private TxnService txnService;


    @Test
    void testCreateNullUser()throws UserException,BookException{
        TxnRequest txnRequest = TxnRequest.builder().
                bookNo("bookNo1").
                userEmail("userEmail").
                build();
        when(userService.checkForValidUser(any())).thenReturn(null);
        assertThrows(UserException.class,()->txnService.createTxn(txnRequest));
    }

    @Test
    void testCreateNullBook()throws BookException,UserException{
        TxnRequest txnRequest = TxnRequest.builder().
                bookNo("bookNo1").
                userEmail("userEmail").
                build();
        User user = User.builder().id(1).email("user").build();
        when(userService.checkForValidUser(any())).thenReturn(user);
        when(bookService.checkForValidBook(any())).thenReturn(null);
        assertThrows(BookException.class,()->txnService.createTxn(txnRequest));
    }

    @Test
    void testCalculateSettlementAmount() throws ParseException {
        ReflectionTestUtils.setField(txnService, "validUpto", 10);
        ReflectionTestUtils.setField(txnService, "finePerDay", 1);
        Date issuedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2026-03-13 12:12:12");
        Txn txn = Txn.builder().id(1).issuedDate(issuedDate).build();
        Book book = Book.builder().id(1).securityAmount(100.0).build();
        assertEquals(79.0,txnService.calculateSettlementAmount(txn,book));

    }

}
