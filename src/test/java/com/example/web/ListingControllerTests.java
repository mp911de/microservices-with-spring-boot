/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.web;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.usecase.ExpensiveService;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.usecase.CreateListing;

/**
 * @author Mark Paluch
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ListingController.class)
public class ListingControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CreateListing createListingMock;

    @MockBean
    ExpensiveService expensiveService;

    @Test
    public void createListing() throws Exception {

        mockMvc.perform(post("/listings") //
                .content("{ \"make\":\"Honda\", \"model\":\"Civic\", \"price\": 42.01 }")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)) //
                .andExpect(status().isCreated());

        verify(createListingMock).createListing("Honda", "Civic", 42.01);
    }

    @Test
    public void shouldTranslateException() throws Exception {

        mockMvc.perform(post("/listings") //
                .content("{ \"price\": 42.01 }").header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)) //
                .andExpect(status().isBadRequest());
    }

}