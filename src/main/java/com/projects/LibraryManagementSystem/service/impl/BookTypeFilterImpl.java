package com.projects.LibraryManagementSystem.service.impl;

import com.projects.LibraryManagementSystem.dto.BookFilterResponse;
import com.projects.LibraryManagementSystem.enums.BookType;
import com.projects.LibraryManagementSystem.enums.Operator;
import com.projects.LibraryManagementSystem.model.Book;
import com.projects.LibraryManagementSystem.repository.BookRepository;
import com.projects.LibraryManagementSystem.service.BookFilterStrategy;
import com.projects.LibraryManagementSystem.service.BookMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookTypeFilterImpl implements BookFilterStrategy {

    @Autowired
    BookRepository bookRepository;

    @Override
    public List<BookFilterResponse> getFilteredBook(Operator operator, String value) {
        List<Book> bookList;
        if (operator.equals(Operator.EQUALS))
            bookList = bookRepository.findByBookType(BookType.valueOf(value));
        else if (operator.equals((Operator.CONTAINS)))
            bookList = bookRepository.findByBookTypeContains(BookType.valueOf(value));
        else
            throw new IllegalArgumentException("Only Equals and Contains are allowed for Book type");
        return bookList.stream().map(BookMapperUtil::toBookFilterResponse).collect(Collectors.toList());
    }


}
