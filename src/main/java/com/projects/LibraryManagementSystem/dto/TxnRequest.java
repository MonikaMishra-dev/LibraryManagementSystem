package com.projects.LibraryManagementSystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TxnRequest {
    @NotBlank(message = "user email must not be blank")
    private String userEmail;

    @NotBlank(message = "book number must not be blank")
    private String bookNo;
}
