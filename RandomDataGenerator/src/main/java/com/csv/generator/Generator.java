package com.csv.generator;

/**
 * @author apktool
 * @package com.csv.demo.com.csv.generator
 * @class Generator
 * @description TODO
 * @date 2020-07-28 20:12
 */
public abstract class Generator<V> {
    protected abstract V nextValue();

    protected abstract V lastValue();

    public final String nextString() {
        V ret = nextValue();
        return ret == null ? null : ret.toString();
    }

    public final String lastString() {
        V ret = lastValue();
        return ret == null ? null : ret.toString();
    }
}
