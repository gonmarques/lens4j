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

package com.byteslounge.lens4j.output.domain;

import com.byteslounge.lens4j.internal.lens.Lens;

public class AddressLens {

    public static final Lens<Address, String> addressStreetLens = Lens.of(Address::getStreet, AddressLens::lens$withStreet);
    public static final Lens<Address, Integer> addressNumberLens = Lens.of(Address::getNumber, AddressLens::lens$withNumber);

    private static Address lens$withStreet(Address address, String street){
        return new Address(street, address.getNumber());
    }

    private static Address lens$withNumber(Address address, Integer number){
        return new Address(address.getStreet(), number);
    }
}
