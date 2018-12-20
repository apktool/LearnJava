package com.jna.snappy;

/**
 * @author li.wengang
 * @date 2018-12-03 15:54
 */
public interface Decompressor {
    public boolean finished();

    public int decompress(byte[] input, int len, byte[] output);

    public void reset();

    public void init();

    public void exit();
}
