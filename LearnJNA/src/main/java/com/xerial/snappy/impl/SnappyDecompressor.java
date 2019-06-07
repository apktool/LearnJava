package com.xerial.snappy.impl;

import com.xerial.snappy.Decompressor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.xerial.snappy.Snappy;

import java.io.File;
import java.io.IOException;

/**
 * @author li.wengang
 * @date 2019-02-19 10:47
 */
@RequiredArgsConstructor
public class SnappyDecompressor implements Decompressor {

    @Override
    public boolean finished() {
        return false;
    }

    @Override
    public int decompress(File inPathName, File outPathName) {
        try {
            byte[] input = FileUtils.readFileToByteArray(inPathName);
            byte[] uncompressed = Snappy.uncompress(input);

            FileUtils.writeByteArrayToFile(outPathName, uncompressed);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public void reset() {

    }

    @Override
    public void init() {
    }

    @Override
    public void exit() {

    }
}
