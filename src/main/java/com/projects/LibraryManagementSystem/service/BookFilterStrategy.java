package com.projects.LibraryManagementSystem.service;

import com.projects.LibraryManagementSystem.dto.responsedtos.BookFilterResponse;
import com.projects.LibraryManagementSystem.enums.Operator;

import java.util.List;

public interface BookFilterStrategy {

    List<BookFilterResponse> getFilteredBook(Operator operator, String value);
}
