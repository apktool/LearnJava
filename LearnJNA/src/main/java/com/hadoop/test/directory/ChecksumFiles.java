package com.hadoop.test.directory;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

/**
 * @author li.wengang
 * @date 2018-12-04 16:39
 */
public class ChecksumFiles {
    private static final Logger logger = LoggerFactory.getLogger(ChecksumFiles.class);
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("local");

    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();

        FileSystem fs = FileSystem.get(conf);

        final String inPathName = resourceBundle.getString("original.directory");
        final String outPathName = resourceBundle.getString("decompress.directory");

        RemoteIterator<LocatedFileStatus> fileStatusListIterator = fs.listFiles(new Path(inPathName), true);

        Path hdfswritepath = new Path("/tmp/jna-checksum-encryption.log");
        FSDataOutputStream outputStream=fs.create(hdfswritepath);

        while (fileStatusListIterator.hasNext()) {
            LocatedFileStatus fileStatus = fileStatusListIterator.next();

            String inPath = fileStatus.getPath().toUri().getPath();
            String outPath = inPath.replace(inPathName, outPathName);

            FileChecksum inputCheckSum1 = null;
            FileChecksum inputCheckSum2 = null;

            long start = System.currentTimeMillis();

            try {
                inputCheckSum1 = fs.getFileChecksum(new Path(inPath));
                inputCheckSum2 = fs.getFileChecksum(new Path(outPath));
            } catch (IOException e) {
                e.printStackTrace();
            }

            long end = System.currentTimeMillis();

            String startTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(start);

            if (inputCheckSum1.equals(inputCheckSum2)) {
                String sql = String.format("INSERT INTO checksum_test_encrption(original_file_name, decryption_file_name, is_same," +
                                " start_time, duration) VALUES ('%s', '%s', %b, '%s', %d);\n"
                        , inPath, outPath, true, startTimestamp, end - start);
                outputStream.writeBytes(sql);
                logger.info(sql);
            } else {
                String sql = String.format("INSERT INTO checksum_test_encrption(original_file_name, decryption_file_name, is_same," +
                                " start_time, duration) VALUES ('%s', '%s', %b, '%s', %d);\n"
                        , inPath, outPath, false, startTimestamp, end - start);
                outputStream.writeBytes(sql);
                logger.info(sql);
            }

        }
        outputStream.close();

    }
}
