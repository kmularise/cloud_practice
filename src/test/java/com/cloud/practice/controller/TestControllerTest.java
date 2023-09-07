package com.cloud.practice.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@WebMvcTest(TestController.class)
class TestControllerTest {
    @Autowired
    MockMvc mock;
    @Test
    @DisplayName("sample test")
    void testSample() throws Exception {
        //given
        String value = "hello-world";
        //when

        //then
        mock.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(value));
    }

}
