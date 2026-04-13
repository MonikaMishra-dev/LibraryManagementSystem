package com.projects.LibraryManagementSystem.controller;

import com.projects.LibraryManagementSystem.dto.requestdtos.BookCreationRequest;
import com.projects.LibraryManagementSystem.dto.responsedtos.BookCreationResponse;
import com.projects.LibraryManagementSystem.service.impl.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    void testAddBook() throws Exception {
        BookCreationResponse response = BookCreationResponse.builder()
                .bookTitle("bookTitle")
                .bookNo("bookNo")
                .securityAmount(100.0)
                .build();

        when(bookService.addBook(any(BookCreationRequest.class))).thenReturn(response);

        String requestJson = """
            {
              "bookTitle": "title",
              "securityAmount": 100.0,
              "bookNo": "bookNo",
              "bookType": "EDUCATIONAL",
              "authorName": "authorName",
              "authorEmail": "authorEmail"
            }
            """;

        mockMvc.perform(post("/book/addBook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.bookNo").value("bookNo"))
                .andExpect(jsonPath("$.data.bookTitle").value("bookTitle"))
                .andExpect(jsonPath("$.data.securityAmount").value(100.0))
                .andExpect(jsonPath("$.msg").value("Successful"))
                .andExpect(jsonPath("$.errorCode").value(1));
    }
}