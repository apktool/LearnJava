package com.xerial.test.directory;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.ResourceBundle;

/**
 * @author li.wengang
 * @date 2018-12-04 16:39
 */
public class ChecksumFiles {
    private static final Logger logger = LoggerFactory.getLogger(ChecksumFiles.class);
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("local");

    public static void main(String[] args) {
        final File originalDirectory = new File(resourceBundle.getString("original.directory"));
        final File decompressDirectory = new File(resourceBundle.getString("decompress.directory"));

        Iterator<File> fileIterator = FileUtils.iterateFiles(originalDirectory, null, true);

        while (fileIterator.hasNext()) {
            File originalFile = fileIterator.next();
            File decompressFile = null;

            long inputCRC32 = 0;
            long outputCRC32 = 0;

            long start = System.currentTimeMillis();

            try {
                String path = originalFile.getAbsolutePath().replace(originalDirectory.getAbsolutePath(), decompressDirectory.getAbsolutePath());
                decompressFile = new File(path);

                inputCRC32 = FileUtils.checksumCRC32(originalFile);
                outputCRC32 = FileUtils.checksumCRC32(decompressFile);
            } catch (IOException e) {
                logger.error(e.getMessage());
            }

            long end = System.currentTimeMillis();

            String startTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(start);

            if (inputCRC32 == outputCRC32) {
                String sql = String.format("INSERT INTO checksum_test(original_file_name, decompress_file_name, is_same," +
                                " start_time, duration) VALUES ('%s', '%s', %b, '%s', %d);"
                        , originalFile.getAbsolutePath(), decompressFile.getAbsolutePath(), true, startTimestamp, end - start);
                logger.info(sql);
            } else {
                String sql = String.format("INSERT INTO checksum_test(original_file_name, decompress_file_name, is_same," +
                                " start_time, duration) VALUES ('%s', '%s', %b, '%s', %d);"
                        , originalFile.getAbsolutePath(), decompressFile.getAbsolutePath(), false, startTimestamp, end - start);
                logger.info(sql);
            }
        }
    }
}
