package com.hadoop.test.directory;

import com.hadoop.crypto.Encryption;
import com.hadoop.crypto.impl.CCEncryption;
import com.hadoop.snappy.Compressor;
import com.hadoop.snappy.impl.SnappyCompressor;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

/**
 * @author li.wengang
 * @date 2019-02-20 10:19
 */
public class EncryptionFiles {
    private static final Logger logger = LoggerFactory.getLogger(EncryptionFiles.class);
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("local");

    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        conf.set("hadoop.security.crypto.codec.classes.aes.ctr.nopadding", org.apache.hadoop.crypto.CCAesCtrCryptoCodec.class.getName());

        FileSystem fs = FileSystem.get(conf);

        final String inPathName = resourceBundle.getString("original.directory");
        final String outPathName = resourceBundle.getString("compress.directory");

        RemoteIterator<LocatedFileStatus> fileStatusListIterator = fs.listFiles(new Path(inPathName), true);

        Path hdfswritepath = new Path("/tmp/jna-encryption.log");
        FSDataOutputStream outputStream=fs.create(hdfswritepath);

        while (fileStatusListIterator.hasNext()) {
            LocatedFileStatus fileStatus = fileStatusListIterator.next();

            String inPath = fileStatus.getPath().toUri().getPath();
            String outPath = inPath.replace(inPathName, outPathName);

            long start = System.currentTimeMillis();

            System.out.println("++++++++++++++hahaha++++++++++++");
            System.out.println(inPath);
            System.out.println(outPath);

            Encryption encryption = new CCEncryption(conf);

            encryption.init();
            int len = encryption.encrypt(inPath, outPath);

            long end = System.currentTimeMillis();

            long before = fs.getFileStatus(new Path(inPath)).getLen();
            long after = fs.getFileStatus(new Path(outPath)).getLen();

            double resizeRatio = 0.0;
            if (before == 0) {
                resizeRatio = 0.0000;
            } else {
                resizeRatio = after * 1.0 / before;
            }

            double encryptionSpeed = 0.0;
            if (end - start == 0) {
                encryptionSpeed = 0.0;
            } else {
                encryptionSpeed = before * 1.0 / (end - start);
            }


            String startTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(start);

            String sql = String.format("INSERT INTO encryption_test (encryption_type, file_name, file_size_before, file_size_after, "
                            + "resize_ratio, encryption_speed, start_time, duration) VALUES ("
                            + "'HARDWAREENCRYPTION', '%s', %d, %d, %.4f, %.4f,'%s', %d);\n"
                    , inPath, before, after, resizeRatio, encryptionSpeed, startTimestamp, end - start);

            outputStream.writeBytes(sql);
            logger.info(sql);
        }

        outputStream.close();
    }
}
