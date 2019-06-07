package com.ycsb.demo;

import com.ycsb.generator.ConstantIntegerGenerator;
import com.ycsb.generator.CounterGenerator;
import com.ycsb.generator.NumberGenerator;
import com.ycsb.iterator.ByteIterator;
import com.ycsb.iterator.RandomByteIterator;
import com.ycsb.iterator.StringByteIterator;
import com.ycsb.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private int fieldlength = 5;
    private int fieldcount = 10;
    private NumberGenerator keysequence = new CounterGenerator(0);
    private NumberGenerator fieldlengthgenerator = new ConstantIntegerGenerator(fieldlength);

    public String buildKeyName(long keynum) {
        boolean orderedinserts = false;
        if (!orderedinserts) {
            keynum = Utils.hash(keynum);
        }
        String value = Long.toString(keynum);

        String preKey = "user";

        return preKey + value;
    }

    /**
     * Builds values for all fields.
     */
    private HashMap<String, ByteIterator> buildValues(String key) {
        boolean dataintegrity = true;

        HashMap<String, ByteIterator> values = new HashMap<>();
        List<String> fieldnames = new ArrayList<>();

        for (int i = 0; i < fieldcount; i++) {
            fieldnames.add("filed" + i);
        }

        for (String fieldkey : fieldnames) {
            ByteIterator data;
            if (!dataintegrity) {
                data = new StringByteIterator(buildDeterministicValue(key, fieldkey));
            } else {
                // fill with random data
                data = new RandomByteIterator(fieldlengthgenerator.nextValue().longValue());
            }
            values.put(fieldkey, data);
        }
        return values;
    }

    /**
     * Build a deterministic value given the key information.
     */
    private String buildDeterministicValue(String key, String fieldkey) {
        int size = fieldlengthgenerator.nextValue().intValue();
        StringBuilder sb = new StringBuilder(size);
        sb.append(key);
        sb.append(':');
        sb.append(fieldkey);
        while (sb.length() < size) {
            sb.append(':');
            sb.append(sb.toString().hashCode());
        }
        sb.setLength(size);

        return sb.toString();
    }

    private String buildOneLine() {
        int keynum = keysequence.nextValue().intValue();
        String key = buildKeyName(keynum);
        HashMap<String, ByteIterator> values = buildValues(key);

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, ByteIterator> entry : values.entrySet()) {
            sb.append(",").append(entry.getValue().toString().replace(",", "!"));
        }

        return key + sb;
    }

    public static void main(String[] args) {

        Main tmp = new Main();
        for (int i = 0; i < 3; i++) {
            String line = tmp.buildOneLine();
            System.out.println(line);
        }

    }
}
