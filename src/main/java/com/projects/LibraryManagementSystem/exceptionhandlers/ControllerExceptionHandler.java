package com.projects.LibraryManagementSystem.exceptionhandlers;

import com.projects.LibraryManagementSystem.exception.BookException;
import com.projects.LibraryManagementSystem.exception.TxnException;
import com.projects.LibraryManagementSystem.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handle(MethodArgumentNotValidException ex){
        return new ResponseEntity<>(ex.getBindingResult().getFieldError().getDefaultMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = TxnException.class)
    public ResponseEntity<Object> handleTxnException(TxnException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BookException.class)
    public ResponseEntity<Object> handleBookException(BookException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UserException.class)
    public ResponseEntity<Object> handleUserException(UserException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
