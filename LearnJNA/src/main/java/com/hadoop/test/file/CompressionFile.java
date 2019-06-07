package com.hadoop.test.file;


import com.hadoop.snappy.Compressor;
import com.hadoop.snappy.impl.SnappyCompressor;
import org.apache.hadoop.conf.Configuration;


/**
 * @author li.wengang
 * @date 2018-12-20 11:29
 */
public class CompressionFile {
    public static void main(String[] args) {
        Configuration conf = new Configuration();

        Compressor compressor = new SnappyCompressor(conf);

        compressor.init();
        int len = compressor.compress(args[0], args[1]);
        compressor.exit();
    }
}
