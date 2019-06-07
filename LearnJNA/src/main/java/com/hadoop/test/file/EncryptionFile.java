package com.hadoop.test.file;

import com.hadoop.crypto.Encryption;
import com.hadoop.crypto.impl.CCEncryption;
import org.apache.hadoop.conf.Configuration;

/**
 * @author li.wengang
 * @date 2018-12-25 18:26
 */
public class EncryptionFile {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.set("hadoop.security.crypto.codec.classes.aes.ctr.nopadding", org.apache.hadoop.crypto.CCAesCtrCryptoCodec.class.getName());

        Encryption encryption = new CCEncryption(conf);

        encryption.init();
        int len = encryption.encrypt(args[0], args[1]);
    }
}
