package com.xerial.snappy.impl;

import com.xerial.snappy.Compressor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.xerial.snappy.Snappy;

import java.io.File;
import java.io.IOException;

/**
 * @author li.wengang
 * @date 2018-12-20 17:07
 */
@RequiredArgsConstructor
public class SnappyCompressor implements Compressor {

    @Override
    public void finish() {
    }

    @Override
    public boolean finished() {
        return false;
    }

    @Override
    public int compress(File inPathName, File outPathName) {
        try {
            byte[] input = FileUtils.readFileToByteArray(inPathName);
            byte[] compressed = Snappy.compress(input);

            FileUtils.writeByteArrayToFile(outPathName, compressed);
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
