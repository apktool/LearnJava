package com.hadoop.snappy;

/**
 * @author li.wengang
 * @date 2018-12-03 15:54
 */
public interface Decompressor {
    public boolean finished();

    public int decompress(String input, String output);

    public void reset();

    public void init();

    public void exit();
}
