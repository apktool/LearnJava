package com.hadoop.crypto;


/**
 * @author li.wengang
 * @date 2018-12-25 18:05
 */
public interface Decryption {
    public void init();

    public int decrypt(String intput, String output);
}
