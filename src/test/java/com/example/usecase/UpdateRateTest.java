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

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;

import com.example.data.MakeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.data.ListingEntity;
import com.example.data.ListingRepository;
import com.example.data.MakeEntity;
import com.example.data.RateEntity;
import com.example.data.RateRepository;

/**
 * @author Mark Paluch
 */
public class UpdateRateTest {

	UpdateRate updateRate;

    @Mock
    ListingRepository listingRepository;

    @Mock
    RateRepository rateRepository;

	@Mock
	MakeRepository makeRepository;

    MakeEntity make = new MakeEntity();
    ListingEntity listing1 = new ListingEntity();
    ListingEntity listing10 = new ListingEntity();

    @Before
    public void before() throws Exception {

		MockitoAnnotations.initMocks(this);

		updateRate = new UpdateRate(listingRepository, rateRepository, makeRepository);

        listing1.setPrice(1);
        listing10.setPrice(10);
    }

    @Test
    public void shouldCreateRate() throws Exception {

        when(rateRepository.findRateByMakeNameAndModel("make", "A8")).thenReturn(Optional.empty());
        when(listingRepository.findListingByMakeNameAndModel("make", "A8")).thenReturn(Arrays.asList(listing1, listing10));
		when(makeRepository.findByName("make")).thenReturn(Optional.of(make));

        updateRate.updateRate("make", "A8");

        verify(rateRepository).save(any(RateEntity.class));
    }
}