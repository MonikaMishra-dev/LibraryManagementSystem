package com.projects.LibraryManagementSystem.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TxnReturnRequest {

    @NotBlank(message = "book number must not be blank")
    private String bookNo;

    @NotBlank(message = "transaction id must not be blank")
    private String txnId;

    @NotBlank(message = "user email  must not be blank")
    private String userEmail;
}
