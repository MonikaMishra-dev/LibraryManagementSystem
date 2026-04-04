package com.projects.LibraryManagementSystem.controller;


import com.projects.LibraryManagementSystem.dto.BookCreationRequest;
import com.projects.LibraryManagementSystem.dto.BookCreationResponse;
import com.projects.LibraryManagementSystem.dto.BookFilterResponse;
import com.projects.LibraryManagementSystem.enums.BookFilter;
import com.projects.LibraryManagementSystem.enums.Operator;
import com.projects.LibraryManagementSystem.model.Book;
import com.projects.LibraryManagementSystem.service.impl.BookService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@Validated
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/addBook")
    public BookCreationResponse addBook(@RequestBody  BookCreationRequest request){
        return bookService.addBook(request);
    }

    @GetMapping("/filter")
    public List<BookFilterResponse> filterBook(@NotNull(message = "filterBy must not be null") @RequestParam("filterBy") BookFilter filterBy,
                                               @NotNull(message = "operator must not be null") @RequestParam("operator") Operator operator,
                                               @NotNull(message = "value must not be blank") @RequestParam("value") String value){
        return bookService.filterBook(filterBy,operator,value);
    }
}
