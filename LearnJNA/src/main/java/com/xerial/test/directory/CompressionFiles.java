package com.xerial.test.directory;

import com.xerial.snappy.Compressor;
import com.xerial.snappy.impl.SnappyCompressor;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.ResourceBundle;

/**
 * @author li.wengang
 * @date 2018-12-04 10:55
 */
public class CompressionFiles {
    private static final Logger logger = LoggerFactory.getLogger(CompressionFiles.class);
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("local");

    public static void main(String[] args) {

        final File inPathName = new File(resourceBundle.getString("original.directory"));
        final File outPathName = new File(resourceBundle.getString("compress.directory"));

        Iterator<File> fileIterator = FileUtils.iterateFiles(inPathName, null, true);
        while (fileIterator.hasNext()) {
            File file = fileIterator.next();
            logger.debug("--> " + file.getAbsolutePath());

            long start = System.currentTimeMillis();

            String path = file.getAbsolutePath().replace(inPathName.getAbsolutePath(), outPathName.getAbsolutePath());
            File tmp = new File(path);
            Compressor compressor = new SnappyCompressor();
            compressor.compress(file, tmp);

            long end = System.currentTimeMillis();

            long before = FileUtils.sizeOf(file);
            long after = FileUtils.sizeOf(tmp);

            double compressionRatio = 0.0;
            if (before == 0) {
                compressionRatio = 0.0000;
            } else {
                compressionRatio = after * 1.0 / before;
            }

            double compressionSpeed = 0.0;
            if (end - start == 0) {
                compressionSpeed = 0.0;
            } else {
                compressionSpeed = before * 1.0 / (end - start);
            }

            String startTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(start);

            String sql = String.format("INSERT INTO compress_test (compress_type, file_name, file_size_before, file_size_after, "
                            + "compression_ratio, compression_speed, start_time, duration) VALUES ("
                            + "'HARDWARECOMPRESS', '%s', %d, %d, %.4f, %.4f,'%s', %d);"
                    , file.getAbsolutePath(), before, after, compressionRatio, compressionSpeed, startTimestamp, end - start);

            logger.info(sql);
        }
    }
}