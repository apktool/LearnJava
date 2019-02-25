package com.xerial.test.file;

import com.xerial.snappy.Decompressor;
import com.xerial.snappy.impl.SnappyDecompressor;

import java.io.File;

/**
 * @author li.wengang
 * @date 2018-12-04 10:55
 */
public class DecompressionFile {
    public static void main(String[] args) {

        final File inPathName = new File(args[0]);
        final File outPathName = new File(args[1]);

        Decompressor decompressor = new SnappyDecompressor();
        decompressor.decompress(inPathName, outPathName);

    }
}
