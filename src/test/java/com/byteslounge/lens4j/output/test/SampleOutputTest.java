/*
 * MIT License
 *
 * Copyright (c) 2017 byteslounge.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.byteslounge.lens4j.output.test;

import com.byteslounge.lens4j.output.domain.Address;
import com.byteslounge.lens4j.output.domain.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.byteslounge.lens4j.output.domain.Address.addressNumberLens;
import static com.byteslounge.lens4j.output.domain.Address.addressStreetLens;
import static com.byteslounge.lens4j.output.domain.Person.personAddressLens;

public class SampleOutputTest {

    @Test
    public void testSampleOutput(){
        Person person1 = new Person("person1", new Address("street1", 3));
        Person person2 = personAddressLens.compose(addressNumberLens).set(person1, 25);

        Address address = addressStreetLens.set(person1.getAddress(), "other");

        Assertions.assertEquals(person1.getAddress().getNumber(), Integer.valueOf(3));
        Assertions.assertEquals(person2.getAddress().getNumber(), Integer.valueOf(25));

        Assertions.assertEquals(person1.getAddress().getStreet(), "street1");
        Assertions.assertEquals(person2.getAddress().getStreet(), "street1");
        Assertions.assertEquals(address.getStreet(), "other");
    }
}
