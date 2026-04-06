package com.projects.LibraryManagementSystem.dto;


import com.projects.LibraryManagementSystem.enums.BookType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BookCreationResponse {

    private String bookTitle;

    private String bookNo;

    private Double securityAmount;
}
