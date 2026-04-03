package com.projects.LibraryManagementSystem.service;

import com.projects.LibraryManagementSystem.dto.BookCreationRequest;
import com.projects.LibraryManagementSystem.dto.BookCreationResponse;
import com.projects.LibraryManagementSystem.enums.BookFilter;
import com.projects.LibraryManagementSystem.enums.BookType;
import com.projects.LibraryManagementSystem.enums.Operator;
import com.projects.LibraryManagementSystem.model.Author;
import com.projects.LibraryManagementSystem.model.AuthorCompositeKey;
import com.projects.LibraryManagementSystem.model.Book;
import com.projects.LibraryManagementSystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorService authorService;

    public BookCreationResponse addBook(BookCreationRequest request) {
        //author is already present or not
        Author authorFromDb = authorService.findAuthorFromDb(request.getAuthorEmail());
        if (authorFromDb == null) {
            authorFromDb = authorService.saveMyAuthor(Author.builder().
                    id(UUID.randomUUID().toString()).
                    email(request.getAuthorEmail()).
                    name(request.getAuthorName()).
                    build());
        }
        Book book = request.toBook();
        book.setAuthor(authorFromDb);
        book = bookRepository.save(book);
        return BookCreationResponse.builder().
                bookTitle(book.getTitle()).
                bookNo(book.getBookNo()).
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
