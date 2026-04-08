package com.projects.LibraryManagementSystem.controller;


import com.projects.LibraryManagementSystem.dto.GenericResponse;
import com.projects.LibraryManagementSystem.dto.requestdtos.BookCreationRequest;
import com.projects.LibraryManagementSystem.dto.responsedtos.BookCreationResponse;
import com.projects.LibraryManagementSystem.dto.responsedtos.BookFilterResponse;
import com.projects.LibraryManagementSystem.enums.BookFilter;
import com.projects.LibraryManagementSystem.enums.Operator;
import com.projects.LibraryManagementSystem.service.impl.BookService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<GenericResponse> addBook(@RequestBody  BookCreationRequest request){
        BookCreationResponse response = bookService.addBook(request);
        GenericResponse genericResponse = GenericResponse.builder().
                data(response).
                build();
        if(response!=null){
            genericResponse.setMsg("Successful");
            genericResponse.setErrorCode(1);
        }
        else{
            genericResponse.setMsg("Failure");
            genericResponse.setErrorCode(0);
        }
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }

    @GetMapping("/filter")
    public List<BookFilterResponse> filterBook(@NotNull(message = "filterBy must not be null") @RequestParam("filterBy") BookFilter filterBy,
                                               @NotNull(message = "operator must not be null") @RequestParam("operator") Operator operator,
                                               @NotNull(message = "value must not be blank") @RequestParam("value") String value){
        return bookService.filterBook(filterBy,operator,value);
    }
}
