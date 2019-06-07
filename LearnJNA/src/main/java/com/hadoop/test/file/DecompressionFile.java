package com.hadoop.test.file;

import com.hadoop.snappy.Decompressor;
import com.hadoop.snappy.impl.SnappyDecompressor;
import org.apache.hadoop.conf.Configuration;

/**
 * @author li.wengang
 * @date 2018-12-20 11:29
 */
public class DecompressionFile {
    public static void main(String[] args) {
        Configuration conf = new Configuration();

        Decompressor compressor = new SnappyDecompressor(conf);

        compressor.init();
        int len = compressor.decompress(args[0], args[1]);
        compressor.exit();
    }
}
