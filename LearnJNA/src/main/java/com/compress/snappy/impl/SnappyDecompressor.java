package com.compress.snappy.impl;

import com.compress.snappy.Decompressor;
import com.compress.snappy.SnappyLibrary;
import com.sun.jna.ptr.IntByReference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author li.wengang
 * @date 2018-12-03 15:53
 */
public class SnappyDecompressor implements Decompressor {
    private static final Logger logger = LogManager.getLogger(SnappyDecompressor.class);

    private boolean finished;

    private SnappyLibrary snappyLibrary;

    public SnappyDecompressor() {
        this.snappyLibrary = SnappyLibrary.INSTANCE;
    }

    @Override
    public boolean finished() {
        return this.finished;
    }

    @Override
    public int decompress(byte[] input, int len, byte[] output) {
        logger.debug("Decompression is in progress...");

        IntByReference tmp = new IntByReference(output.length);
        SnappyLibrary.INSTANCE.snappy_uncompress(input, len, output, tmp);
        this.finished = true;
        return tmp.getValue();
    }

    @Override
    public void reset() {
        this.finished = false;
    }

    @Override
    public void init() {
        snappyLibrary.CC_apiSysInit(true);
    }

    @Override
    public void exit() {
        snappyLibrary.CC_apiSysExit();
    }
}
