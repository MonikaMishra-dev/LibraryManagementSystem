package com.projects.LibraryManagementSystem.dto.responsedtos;

import com.projects.LibraryManagementSystem.enums.BookType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BookFilterResponse {
    private String bookTitle;
    private String bookNo;
    private BookType bookType;
    private String authorName;
    private String authorEmail;
}
