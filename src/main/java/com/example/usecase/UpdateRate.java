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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.data.MakeRepository;
import org.springframework.stereotype.Service;

import com.example.data.ListingEntity;
import com.example.data.ListingRepository;
import com.example.data.MakeEntity;
import com.example.data.RateEntity;
import com.example.data.RateRepository;

import lombok.AllArgsConstructor;

/**
 * @author Mark Paluch
 */
@Service
@AllArgsConstructor
public class UpdateRate {

    ListingRepository listingRepository;
    RateRepository rateRepository;
	MakeRepository makeRepository;

    public void updateRate(String makeName, String model) {

		Optional<MakeEntity> makeEntity = makeRepository.findByName(makeName);
		List<ListingEntity> listingByMakeAndModel = listingRepository.findListingByMakeNameAndModel(makeName, model);

        Double avg = listingByMakeAndModel.stream().collect(Collectors.averagingDouble(ListingEntity::getPrice));

        Optional<RateEntity> optionalRate = rateRepository.findRateByMakeNameAndModel(makeName, model);

        RateEntity rate = optionalRate.orElseGet(RateEntity::new);
        rate.setMake(makeEntity.get());
        rate.setModel(model);
        rate.setAsk(avg);

        rateRepository.save(rate);
    }
}
