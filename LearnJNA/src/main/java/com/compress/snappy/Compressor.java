package com.compress.snappy;

/**
 * @author li.wengang
 * @date 2018-12-03 15:20
 */
public interface Compressor {
    public void finish();

    public boolean finished();

    public int compress(byte[] input, int len, byte[] output);

    public void reset();

    public void init();

    public void exit();
}
