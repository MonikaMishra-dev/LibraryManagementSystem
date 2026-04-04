package com.projects.LibraryManagementSystem.service.impl;

import com.projects.LibraryManagementSystem.dto.BookCreationRequest;
import com.projects.LibraryManagementSystem.dto.BookCreationResponse;
import com.projects.LibraryManagementSystem.dto.BookFilterResponse;
import com.projects.LibraryManagementSystem.enums.BookFilter;
import com.projects.LibraryManagementSystem.enums.Operator;
import com.projects.LibraryManagementSystem.model.Author;
import com.projects.LibraryManagementSystem.model.Book;
import com.projects.LibraryManagementSystem.repository.BookRepository;
import com.projects.LibraryManagementSystem.service.BookFilterFactory;
import com.projects.LibraryManagementSystem.service.BookFilterStrategy;
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

    @Autowired
    private BookFilterFactory bookFilterFactory;

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

    public List<BookFilterResponse> filterBook(BookFilter filterBy, Operator operator, String value) {
        BookFilterStrategy strategy = bookFilterFactory.getStrategy(filterBy);
        return strategy.getFilteredBook(operator,value);
    }
}
