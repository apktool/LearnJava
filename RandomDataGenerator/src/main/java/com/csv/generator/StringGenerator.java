package com.csv.generator;

import com.ycsb.utils.Utils;
import lombok.AllArgsConstructor;

/**
 * @author apktool
 * @package com.csv.generator
 * @class StringGenerator
 * @description TODO
 * @date 2020-07-28 20:43
 */
@AllArgsConstructor
public class StringGenerator extends Generator<String> {
    private final long length;

    @Override
    public String nextValue() {
        return Long.toString(Utils.hash(length));
    }

    @Override
    public String lastValue() {
        return null;
    }
}
