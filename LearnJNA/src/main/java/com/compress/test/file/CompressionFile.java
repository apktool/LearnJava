package com.compress.test.file;

import com.compress.snappy.Compressor;
import com.compress.snappy.impl.SnappyCompressor;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author li.wengang
 * @date 2018-12-04 10:55
 */
public class CompressionFile {
    public static void main(String[] args) throws IOException {

        final File inPathName = new File(args[0]);
        final File outPathName = new File(args[1]);

        byte[] input = FileUtils.readFileToByteArray(inPathName);
        byte[] compressOutput = new byte[input.length * 2];

        Compressor compressor = new SnappyCompressor();
        compressor.init();
        int len = compressor.compress(input, input.length, compressOutput);
        compressor.exit();

        FileUtils.writeByteArrayToFile(outPathName, Arrays.copyOf(compressOutput, len));
    }
}
