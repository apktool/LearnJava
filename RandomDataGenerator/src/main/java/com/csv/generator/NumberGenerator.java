package com.csv.generator;


/**
 * @author apktool
 * @package com.csv.generator
 * @class NumberGenerator
 * @description TODO
 * @date 2020-07-28 21:06
 */

public abstract class NumberGenerator extends Generator<Number> {
    private Number lastVal;


    protected void setLastValue(Number last) {
        lastVal = last;
    }


    @Override
    public Number lastValue() {
        return lastVal;
    }

    public abstract double mean();
}

