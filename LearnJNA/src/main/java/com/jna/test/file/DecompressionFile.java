package com.jna.test.file;

import com.jna.snappy.Decompressor;
import com.jna.snappy.impl.SnappyDecompressor;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author li.wengang
 * @date 2018-12-04 10:55
 */
public class DecompressionFile {
    public static void main(String[] args) throws IOException {

        final File inPathName = new File(args[0]);
        final File outPathName = new File(args[1]);

        byte[] input = FileUtils.readFileToByteArray(inPathName);

        int abc = input.length * 2;
        for (int i = 2; i < 50; i++) {
            int abs = Math.abs(input.length * i);
            if (abs < Integer.MAX_VALUE - 1) {
                abc = abs;
            }
        }

        byte[] decompressOutput = new byte[abc];

        Decompressor decompressor = new SnappyDecompressor();
        decompressor.init();
        int len = decompressor.decompress(input, input.length, decompressOutput);
        decompressor.exit();

        FileUtils.writeByteArrayToFile(outPathName, Arrays.copyOf(decompressOutput, len));
    }
}
