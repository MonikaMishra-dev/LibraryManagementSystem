package com.projects.LibraryManagementSystem.service.impl;

import com.projects.LibraryManagementSystem.dto.BookFilterResponse;
import com.projects.LibraryManagementSystem.enums.Operator;
import com.projects.LibraryManagementSystem.model.Book;
import com.projects.LibraryManagementSystem.repository.BookRepository;
import com.projects.LibraryManagementSystem.service.BookFilterStrategy;
import com.projects.LibraryManagementSystem.service.BookMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class BookNoFilterImpl implements BookFilterStrategy {

    @Autowired
    BookRepository bookRepository;

    @Override
    public List<BookFilterResponse> getFilteredBook(Operator operator, String value) {
        if (!operator.equals(Operator.EQUALS)) {
            throw new IllegalArgumentException("Only Equals is expected with book number");
        }
        List<Book> bookList = bookRepository.findByBookNo(value);
        return bookList.stream().map(BookMapperUtil::toBookFilterResponse).collect(Collectors.toList());
    }

}
