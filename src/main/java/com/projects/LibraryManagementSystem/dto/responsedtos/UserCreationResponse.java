package com.projects.LibraryManagementSystem.dto.responsedtos;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserCreationResponse {

    private String userName;

    private String userEmail;

    private String userAddress;

    private String userPhone;

}
