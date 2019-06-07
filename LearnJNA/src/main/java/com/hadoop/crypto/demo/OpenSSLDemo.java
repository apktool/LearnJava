package com.hadoop.crypto.demo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.crypto.CryptoCodec;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.crypto.CryptoFSDataInputStream;
import org.apache.hadoop.fs.crypto.CryptoFSDataOutputStream;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;

/**
 * @author li.wengang
 * @date 2018-12-25 14:19
 */
public class OpenSSLDemo {
    private static final byte[] key = {0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16};
    private static final byte[] iv = {0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08};

    public static void main(String[] args) throws IOException {
        String a = "/tmp/a.out";
        String b = "/tmp/b.out";
        String c = "/tmp/c.out";

        Configuration conf = new Configuration();
        conf.set("hadoop.security.crypto.codec.classes.aes.ctr.nopadding", org.apache.hadoop.crypto.OpensslAesCtrCryptoCodec.class.getName());
        FileSystem fs = FileSystem.get(conf);

        CryptoCodec codec = CryptoCodec.getInstance(conf);

        System.out.println();

        // encrypt
        FSDataOutputStream fsOutputStream = fs.create(new Path(b));
        CryptoFSDataOutputStream cfos = new CryptoFSDataOutputStream(fsOutputStream, codec, key, iv);

        FSDataInputStream inputStream = fs.open(new Path(a));
        IOUtils.copyBytes(inputStream, cfos, conf);

        fsOutputStream.close();
        inputStream.close();
        cfos.close();

        /*--------------------------------------------------*/

        // decrypt
        FSDataInputStream fsInputStream = fs.open(new Path(b));
        CryptoFSDataInputStream fsis = new CryptoFSDataInputStream(fsInputStream, codec, key, iv);

        FSDataOutputStream outputStream = fs.create(new Path(c));
        IOUtils.copyBytes(fsis, outputStream, conf);

        fsInputStream.close();
        fsis.close();
        outputStream.close();
    }
}
