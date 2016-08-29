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

package com.example.data;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Mark Paluch
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class MakeRepositoryTests {

    @Autowired
    MakeRepository makeRepository;

    @Test
    public void shouldReturnEmptyWhenRecordIsAbsent() throws Exception {

        Optional<MakeEntity> absent = makeRepository.findByName("Hello");
        assertThat(absent.isPresent()).isFalse();
    }

    @Test
    public void shouldReturnRecordIfPresent() throws Exception {

        MakeEntity make = new MakeEntity();
        make.setName("World");

        makeRepository.save(make);

        Optional<MakeEntity> present = makeRepository.findByName("World");
        assertThat(present.isPresent()).isTrue();
        assertThat(present.get()).isEqualTo(make);
    }
}