package com.projects.LibraryManagementSystem.controller;

import com.projects.LibraryManagementSystem.dto.requestdtos.TxnRequest;
import com.projects.LibraryManagementSystem.service.impl.TxnService;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(TxnController.class)
public class TxnControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TxnService txnService;

    @Test
    void testCrateTxn() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userEmail","userEmail1");
        jsonObject.put("bookNo","bookNo1");
        when(txnService.createTxn(any(TxnRequest.class))).thenReturn("txnId1");
        mockMvc.perform(post("/txn/issue").
                contentType(MediaType.APPLICATION_JSON).
                content(jsonObject.toString())).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(content().string("txnId1"));

    }

}
