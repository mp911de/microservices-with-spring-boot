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

package com.example.usecase;

import org.springframework.stereotype.Service;

import com.example.data.ListingEntity;
import com.example.data.ListingRepository;
import com.example.data.MakeEntity;
import com.example.data.MakeRepository;
import com.example.data.RateRepository;

import lombok.AllArgsConstructor;

/**
 * @author Mark Paluch
 */
@Service
@AllArgsConstructor
public class CreateListing {

    UpdateRate updateRate;

    ListingRepository listingRepository;
    MakeRepository makeRepository;
    RateRepository rateRepository;

    public ListingEntity createListing(String makeName, String model, double price) {

        MakeEntity make = makeRepository.findByName(makeName)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find make " + makeName));

        ListingEntity listing = new ListingEntity();
        listing.setMake(make);
        listing.setModel(model);
        listing.setPrice(price);

        listingRepository.saveAndFlush(listing);

        updateRate.updateRate(makeName, model);

        return listing;
    }
}
