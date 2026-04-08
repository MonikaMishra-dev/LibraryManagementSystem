package com.projects.LibraryManagementSystem.dto.requestdtos;

import com.projects.LibraryManagementSystem.enums.BookType;
import com.projects.LibraryManagementSystem.model.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BookCreationRequest {

    @NotBlank(message = "book title must not be blank")
    private String bookTitle;

    @NotBlank(message = "book number must not be blank")
    private String bookNo;

    @NotNull(message = "book type must not be null")
    private BookType bookType;

    @Positive(message = "positive values are expected")
    private Double bookSecurityAmount;

    @NotBlank(message = "author name must not be blank")
    private String authorName;

    @NotBlank(message = "author email must not be blank")
    private String authorEmail;

    public Book toBook(){
        return Book.builder().
                title(this.bookTitle).
                bookNo(this.bookNo).
                bookType(bookType).
                securityAmount(this.bookSecurityAmount).
                build();
    }
}
