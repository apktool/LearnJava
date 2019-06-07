package com.hadoop.snappy.impl;

import com.hadoop.snappy.Compressor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author li.wengang
 * @date 2018-12-20 17:07
 */
@RequiredArgsConstructor
public class SnappyCompressor implements Compressor {
    private CompressionCodec codec;
    private FileSystem fs;
    @NonNull
    private Configuration conf;

    @Override
    public void finish() {
    }

    @Override
    public boolean finished() {
        return false;
    }

    @Override
    public int compress(String input, String output) {
        FSDataInputStream fsInputStream = null;
        OutputStream out = null;
        try {
            fsInputStream = fs.open(new Path(input));
            out = codec.createOutputStream(fs.create(new Path(output)));
            IOUtils.copyBytes(fsInputStream, out, conf);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeStream(fsInputStream);
            IOUtils.closeStream(out);
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
