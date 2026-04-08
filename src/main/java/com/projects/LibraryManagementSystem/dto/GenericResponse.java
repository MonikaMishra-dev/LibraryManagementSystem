package com.projects.LibraryManagementSystem.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenericResponse {
    private Object data;
    private String error;
    private String msg;
    private int errorCode;
}
