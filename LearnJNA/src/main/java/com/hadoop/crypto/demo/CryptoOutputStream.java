package com.hadoop.crypto.demo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.crypto.CryptoCodec;
import org.apache.hadoop.crypto.CryptoStreamUtils;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.crypto.CryptoFSDataOutputStream;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.IOException;

/**
 * @author li.wengang
 * @date 2018-12-27 10:36
 */
public class CryptoOutputStream {
    private static final byte[] key = {0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16};
    private static final byte[] iv = {0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08};

    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        Class klass = org.apache.hadoop.crypto.CCAesCtrCryptoCodec.class;

        conf.set("hadoop.security.crypto.codec.classes.aes.ctr.nopadding", klass.getName());

        FileSystem fs = FileSystem.get(conf);
        CryptoCodec c = (CryptoCodec) ReflectionUtils.newInstance(klass, conf);
        System.out.println(c);

        CryptoCodec codec = CryptoCodec.getInstance(conf);
        System.out.println(codec);
        CryptoStreamUtils.getBufferSize(codec.getConf());

        CryptoFSDataOutputStream cfos = new CryptoFSDataOutputStream(fs.create(new Path("/tmp/a.out")), codec, key, iv);
        cfos.close();
        fs.close();
    }
}
