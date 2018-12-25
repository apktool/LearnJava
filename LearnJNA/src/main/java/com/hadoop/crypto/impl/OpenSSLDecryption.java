package com.hadoop.crypto.impl;

import com.hadoop.crypto.Decryption;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.crypto.CryptoCodec;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.crypto.CryptoFSDataInputStream;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;

/**
 * @author li.wengang
 * @date 2018-12-25 18:13
 */
@RequiredArgsConstructor
public class OpenSSLDecryption implements Decryption {
    private static final byte[] key = {0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16};
    private static final byte[] iv = {0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08};

    private FileSystem fs;
    private CryptoCodec codec;
    @NonNull
    private Configuration conf;

    @Override
    public void init() {
        codec = CryptoCodec.getInstance(conf);
        try {
            this.fs = FileSystem.get(conf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int decrypt(String input, String output) {
        // decrypt
        FSDataInputStream fsInputStream = null;
        CryptoFSDataInputStream cfis = null;
        FSDataOutputStream outputStream = null;

        try {
            fsInputStream = fs.open(new Path(input));
            cfis = new CryptoFSDataInputStream(fsInputStream, codec, key, iv);

            outputStream = fs.create(new Path(output));
            IOUtils.copyBytes(cfis, outputStream, conf);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fsInputStream.close();
                outputStream.close();
                cfis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return 0;
    }
}
