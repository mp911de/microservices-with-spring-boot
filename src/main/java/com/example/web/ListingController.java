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

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.usecase.CreateListing;

import lombok.RequiredArgsConstructor;

/**
 * @author Mark Paluch
 */
@RestController
@RequiredArgsConstructor
public class ListingController {

    final CreateListing createListing;

    @PostMapping(value = "/listings", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    void createListing(@RequestBody ListingRepresentation listing) {

        Assert.notNull(listing, "Listing body must not be empty!");
        Assert.hasText(listing.getMake(), "Make must not be empty!");
        Assert.hasText(listing.getModel(), "Model must not be empty!");
        Assert.notNull(listing.getPrice(), "Price must not be empty!");

        createListing.createListing(listing.getMake(), listing.getModel(), listing.getPrice());

    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private String handle(IllegalArgumentException e) {
        return e.getMessage();
    }
}
