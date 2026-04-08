package com.projects.LibraryManagementSystem.service;


import com.projects.LibraryManagementSystem.dto.responsedtos.BookFilterResponse;
import com.projects.LibraryManagementSystem.model.Book;

public class BookMapperUtil {

    public static BookFilterResponse toBookFilterResponse(Book book) {
        return BookFilterResponse.builder()
                .bookTitle(book.getTitle())
                .bookNo(book.getBookNo())
                .bookType(book.getBookType())
                .authorEmail(book.getAuthor().getEmail())
                .authorName(book.getAuthor().getName())
                .build();
    }
}
