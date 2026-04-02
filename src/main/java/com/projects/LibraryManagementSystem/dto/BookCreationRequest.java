package com.projects.LibraryManagementSystem.dto;

import com.projects.LibraryManagementSystem.enums.BookType;
import com.projects.LibraryManagementSystem.model.Book;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BookCreationRequest {

    private String bookTitle;

    @NotBlank(message = "book number must not be blank")
    private String bookNo;

    private BookType bookType;

    private Double bookSecurityAmount;

    public Book toBook(){
        return Book.builder().
                title(this.bookTitle).
                bookNo(this.bookNo).
                bookType(bookType).
                securityAmount(this.bookSecurityAmount).
                build();
    }
}
