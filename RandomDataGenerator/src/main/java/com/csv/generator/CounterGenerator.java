package com.csv.generator;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author apktool
 * @package com.csv.generator
 * @class CounterGenerator
 * @description TODO
 * @date 2020-07-28 21:07
 */
public class CounterGenerator extends NumberGenerator {
    private final AtomicLong counter;

    public CounterGenerator(long countstart) {
        counter = new AtomicLong(countstart);
    }

    @Override
    public Long nextValue() {
        return counter.getAndIncrement();
    }

    @Override
    public Long lastValue() {
        return counter.get() - 1;
    }

    @Override
    public double mean() {
        throw new UnsupportedOperationException("Can't compute mean of non-stationary distribution!");
    }
}

