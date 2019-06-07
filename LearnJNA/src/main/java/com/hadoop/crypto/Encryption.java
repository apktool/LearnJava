package com.hadoop.crypto;

/**
 * @author li.wengang
 * @date 2018-12-25 18:05
 */
public interface Encryption {
    public void init();

    public int encrypt(String intput, String output);
}