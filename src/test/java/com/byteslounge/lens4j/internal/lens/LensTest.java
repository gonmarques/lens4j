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

package com.byteslounge.lens4j.internal.lens;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LensTest {

    @Test
    public void getValue() {
        Person person = new Person("john", new Employer("acme"));
        Lens<Person, String> personNameLens = new Lens<>(p -> p.name, Person::setName);
        String name = personNameLens.get(person);
        assertEquals("john" , name);
    }

    @Test
    public void setValue() {
        Person person = new Person("john", new Employer("acme"));
        Lens<Person, String> personNameLens = new Lens<>(p -> p.name, Person::setName);
        Person other = personNameLens.set(person, "smith");
        assertEquals("john" , person.name);
        assertEquals("smith" , other.name);
    }

    @Test
    public void getNestedValue() {
        Person person = new Person("john", new Employer("acme"));
        Lens<Person, String> personEmployerNameLens = new Lens<>(p -> p.employer, Person::setEmployer)
                .compose(new Lens<>(e -> e.name, Employer::setName));
        String name = personEmployerNameLens.get(person);
        assertEquals("acme" , name);
    }

    @Test
    public void setNestedValue() {
        Person person = new Person("john", new Employer("acme"));
        Lens<Person, String> personEmployerNameLens = new Lens<>(p -> p.employer, Person::setEmployer)
                .compose(new Lens<>(e -> e.name, Employer::setName));
        Person other = personEmployerNameLens.set(person, "other");
        assertEquals("acme" , person.employer.name);
        assertEquals("other" , other.employer.name);
    }

    private class Person {
        final String name;
        final Employer employer;
        Person(String name, Employer employer) {
            this.name = name;
            this.employer = employer;
        }
        Person setName(String name){
            return new Person(name, employer);
        }
        Person setEmployer(Employer employer){
            return new Person(name, employer);
        }
    }

    private class Employer {
        final String name;
        private Employer(String name) {
            this.name = name;
        }
        Employer setName(String name){
            return new Employer(name);
        }
    }
}
