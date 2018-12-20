package com.hadoop.snappy.impl;

import com.hadoop.snappy.Decompressor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author li.wengang
 * @date 2018-12-20 17:07
 */
@RequiredArgsConstructor
public class SnappyDecompressor implements Decompressor {
    private CompressionCodec codec;
    private FileSystem fs;

    @NonNull
    private Configuration conf;

    @Override
    public boolean finished() {
        return false;
    }

    @Override
    public int decompress(String input, String output) {
        FSDataOutputStream fsOutputStream = null;
        InputStream in = null;

        try {
            fsOutputStream = fs.create(new Path(output));
            in = codec.createInputStream(fs.open(new Path(input)));
            IOUtils.copyBytes(in, fsOutputStream, conf);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeStream(in);
            IOUtils.closeStream(fsOutputStream);
        }
        return 0;
    }

    @Override
    public void reset() {

    }

    @Override
    public void init() {
        this.codec = ReflectionUtils.newInstance(org.apache.hadoop.io.compress.SnappyCodec.class, conf);

        try {
            this.fs = FileSystem.get(conf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exit() {

    }
}
