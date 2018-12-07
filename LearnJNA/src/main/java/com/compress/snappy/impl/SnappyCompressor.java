package com.compress.snappy.impl;

import com.compress.snappy.Compressor;
import com.compress.snappy.SnappyLibrary;
import com.sun.jna.ptr.IntByReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author li.wengang
 * @date 2018-12-03 15:22
 */
public class SnappyCompressor implements Compressor {
    private static final Logger logger = LoggerFactory.getLogger(SnappyCompressor.class);

    private boolean finish, finished;

    private SnappyLibrary snappyLibrary;

    public SnappyCompressor() {
        this.snappyLibrary = SnappyLibrary.INSTANCE;
    }

    @Override
    public void finish() {
        this.finish = true;
    }

    @Override
    public boolean finished() {
        return finish && finished;
    }

    @Override
    public int compress(byte[] input, int len, byte[] output) {
        logger.debug("CompressionFiles is in progress...");

        IntByReference tmp = new IntByReference(output.length);
        snappyLibrary.snappy_compress(input, len, output, tmp);
        finished = true;

        return tmp.getValue();
    }

    @Override
    public void reset() {
        finish = false;
        finished = false;
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
