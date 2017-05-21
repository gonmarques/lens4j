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

import java.util.function.BiFunction;
import java.util.function.Function;

public class Lens<T, U> {

    private final Function<T, U> getter;
    private final BiFunction<T, U, T> setter;

    public Lens(Function<T, U> getter, BiFunction<T, U, T> setter) {
        this.getter = getter;
        this.setter = setter;
    }

    public U get(T target){
        return getter.apply(target);
    }

    public T set(T target, U value){
        return setter.apply(target, value);
    }

    public <R> Lens<T, R> compose(Lens<U, R> lens){
        return new Lens<>(
                target -> lens.getter.apply(getter.apply(target)),
                (target, value) -> setter.apply(
                        target,
                        lens.setter.apply(
                                getter.apply(target),
                                value
                        )
                )
        );
    }
}
