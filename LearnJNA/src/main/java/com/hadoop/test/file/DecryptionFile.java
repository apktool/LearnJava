package com.hadoop.test.file;

import com.hadoop.crypto.Decryption;
import com.hadoop.crypto.impl.OpenSSLDecryption;
import org.apache.hadoop.conf.Configuration;

/**
 * @author li.wengang
 * @date 2018-12-25 18:26
 */
public class DecryptionFile {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.set("hadoop.security.crypto.codec.classes.aes.ctr.nopadding", org.apache.hadoop.crypto.CCAesCtrCryptoCodec.class.getName());

        Decryption decryption = new OpenSSLDecryption(conf);

        decryption.init();
        int len = decryption.decrypt(args[0], args[1]);
    }
}
