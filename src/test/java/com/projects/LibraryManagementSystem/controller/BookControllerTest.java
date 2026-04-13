package com.projects.LibraryManagementSystem.controller;

import com.projects.LibraryManagementSystem.dto.requestdtos.BookCreationRequest;
import com.projects.LibraryManagementSystem.dto.responsedtos.BookCreationResponse;
import com.projects.LibraryManagementSystem.service.impl.BookService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookService bookService;

    @Test
    void testAddBook() throws Exception {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("bookTitle" , "title");
        jsonObject.put("securityAmount" , 100.0);
        jsonObject.put("bookNo" , "bookNo");
        jsonObject.put("bookType" , "EDUCATIONAL");
        jsonObject.put("authorName" , "authorName");
        jsonObject.put("authorEmail" , "authorEmail");
        BookCreationResponse response = BookCreationResponse.builder().
                bookTitle("bookTitle").
                bookNo("bookNo").
                securityAmount(100.0).
                build();
        when(bookService.addBook(any(BookCreationRequest.class))).thenReturn(response);
        mockMvc.perform(post("/book/addBook").
                contentType(MediaType.APPLICATION_JSON).
                content(jsonObject.toString())).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(jsonPath("$.data.bookNo").value("bookNo")).
                andExpect(jsonPath("$.data.bookTitle").value("bookTitle")).
                andExpect(jsonPath("$.data.securityAmount").value(100.0)).
                andExpect(jsonPath("$.msg").value("Successful")).
                andExpect(jsonPath("$.errorCode").value(1));

    }
}
