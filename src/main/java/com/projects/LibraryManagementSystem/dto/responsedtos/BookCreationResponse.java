package com.projects.LibraryManagementSystem.dto.responsedtos;


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
