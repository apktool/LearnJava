package com.compress;

import com.compress.snappy.Compressor;
import com.compress.snappy.Decompressor;
import com.compress.snappy.impl.SnappyCompressor;
import com.compress.snappy.impl.SnappyDecompressor;

/**
 * @author li.wengang
 * @date 2018-12-03 16:08
 */
public class Main {
    public static void main(String[] args) {
        final byte[] input = {
                0x61, 0x62, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68,
                0x61, 0x62, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68,
                0x68, 0x67, 0x66, 0x65, 0x64, 0x63, 0x62, 0x61,
                0x68, 0x67, 0x66, 0x65, 0x64, 0x63, 0x62, 0x61
        };

        byte[] compressOutput = new byte[input.length * 2];
        Compressor compressor = new SnappyCompressor();
        compressor.init();
        int clen = compressor.compress(input, input.length, compressOutput);
        compressor.exit();

        byte[] uncompressOutput = new byte[input.length];
        Decompressor decompressor = new SnappyDecompressor();
        decompressor.init();
        int dlen = decompressor.decompress(compressOutput, clen, uncompressOutput);
        decompressor.exit();

        for (int i = 0; i < dlen; i++) {
            if (i % 8 == 0) {
                System.out.println();
            }
            System.out.printf("0x%02x, ", uncompressOutput[i]);
        }
        System.out.println();
    }
}
