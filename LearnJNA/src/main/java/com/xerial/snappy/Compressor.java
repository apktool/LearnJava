package com.xerial.snappy;

import java.io.File;

/**
 * @author li.wengang
 * @date 2019-02-19 10:39
 */
public interface Compressor {
    public void finish();

    public boolean finished();

    public int compress(File intput, File output);

    public void reset();

    public void init();

    public void exit();
}
