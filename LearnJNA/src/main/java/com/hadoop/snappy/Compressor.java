package com.hadoop.snappy;

/**
 * @author li.wengang
 * @date 2018-12-20 17:06
 */
public interface Compressor {
    public void finish();

    public boolean finished();

    public int compress(String intput, String output);

    public void reset();

    public void init();

    public void exit();
}
