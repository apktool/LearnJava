package com.xerial.snappy;

import java.io.File;

/**
 * @author li.wengang
 * @date 2019-02-19 10:39
 */

public interface Decompressor {
    public boolean finished();

    public int decompress(File input, File output);

    public void reset();

    public void init();

    public void exit();
}
