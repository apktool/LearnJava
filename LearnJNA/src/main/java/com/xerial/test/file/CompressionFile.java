package com.xerial.test.file;

import com.xerial.snappy.Compressor;
import com.xerial.snappy.impl.SnappyCompressor;

import java.io.File;

/**
 * @author li.wengang
 * @date 2018-12-04 10:55
 */
public class CompressionFile {
    public static void main(String[] args) {

        final File inPathName = new File(args[0]);
        final File outPathName = new File(args[1]);

        Compressor compressor = new SnappyCompressor();
        compressor.compress(inPathName, outPathName);
    }
}
