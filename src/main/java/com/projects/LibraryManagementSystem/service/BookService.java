package com.projects.LibraryManagementSystem.service;

import com.projects.LibraryManagementSystem.dto.BookCreationRequest;
import com.projects.LibraryManagementSystem.dto.BookCreationResponse;
import com.projects.LibraryManagementSystem.enums.BookFilter;
import com.projects.LibraryManagementSystem.enums.BookType;
import com.projects.LibraryManagementSystem.enums.Operator;
import com.projects.LibraryManagementSystem.model.Book;
import com.projects.LibraryManagementSystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public BookCreationResponse addBook(BookCreationRequest request) {
        //author is already present or not

        Book book = request.toBook();
        Book bookFromDb = bookRepository.save(book);
        return BookCreationResponse.builder().
                bookTitle(bookFromDb.getTitle()).
                bookNo(bookFromDb.getBookNo()).
                bookSecurityAmount(bookFromDb.getSecurityAmount()).
                bookType(bookFromDb.getBookType()).
                build();
    }

    public List<Book> filterBook(BookFilter filterBy, Operator operator, String value) {
        switch (filterBy){
            case TITLE -> {
                switch (operator){
                    case EQUALS -> {
                        return bookRepository.findByTitle(value);
                    }
                    case LIKE -> {
                        return bookRepository.findByTitleLike("%"+value+"%");
                    }
                    case CONTAINS -> {
                        return bookRepository.findByTitleContains(value);
                    }
                }
            }
            case BOOK_NO -> {
                switch (operator){
                    case EQUALS -> {return bookRepository.findByBookNo(value);}
                    case LIKE -> {return bookRepository.findByBookNoLike("%"+value+"%");}
                    case CONTAINS -> {return bookRepository.findByBookNoContains(value);}
                }
            }
            case BOOK_TYPE -> {
                switch (operator){
                    case EQUALS -> {return bookRepository.findByBookType(BookType.valueOf(value));}
                    case CONTAINS -> {return bookRepository.findByBookTypeContains(BookType.valueOf(value));}
                }
            }
            case AUTHOR -> {}
            case SECURITY_AMOUNT -> {
                switch (operator){
                    case EQUALS -> {return bookRepository.findBySecurityAmount(Double.valueOf(value));}
                    case LESS_THAN -> {return bookRepository.findBySecurityAmountLessThan(Double.valueOf(value));}
                    case LESS_THAN_EQUAL -> {return bookRepository.findBySecurityAmountLessThanEqual(Double.valueOf(value));}
                }
            }
        }

        return new ArrayList<>();
    }
}
