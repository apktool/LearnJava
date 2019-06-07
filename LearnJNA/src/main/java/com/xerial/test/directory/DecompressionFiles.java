package com.xerial.test.directory;

import com.xerial.snappy.Decompressor;
import com.xerial.snappy.impl.SnappyDecompressor;
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
public class DecompressionFiles {
    private static final Logger logger = LoggerFactory.getLogger(DecompressionFiles.class);
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("local");

    public static void main(String[] args) {

        final File inPathName = new File(resourceBundle.getString("compress.directory"));
        final File outPathName = new File(resourceBundle.getString("decompress.directory"));

        Iterator<File> fileIterator = FileUtils.iterateFiles(inPathName, null, true);
        while (fileIterator.hasNext()) {
            File file = fileIterator.next();

            long start = System.currentTimeMillis();

            Decompressor decompressor = new SnappyDecompressor();

            String path = file.getAbsolutePath().replace(inPathName.getAbsolutePath(), outPathName.getAbsolutePath());
            File tmp = new File(path);
            decompressor.decompress(file, tmp);

            long end = System.currentTimeMillis();

            long before = FileUtils.sizeOf(file);
            long after = FileUtils.sizeOf(tmp);

            double decompressionRatio = 0.0;
            if (before == 0) {
                decompressionRatio = 0.0000;
            } else {
                decompressionRatio = after * 1.0 / before;
            }

            double decompressionSpeed = 0.0;
            if (end - start == 0) {
                decompressionSpeed = 0.0;
            } else {
                decompressionSpeed = before * 1.0 / (end - start);
            }

            String startTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(start);

            String sql = String.format("INSERT INTO decompress_test (decompress_type, file_name, file_size_before, file_size_after, "
                            + "decompression_ratio, decompression_speed, start_time, duration) VALUES ("
                            + "'HARDWAREDECOMPRESS', '%s', %d, %d, %.4f, %.4f,'%s', %d);"
                    , file.getAbsolutePath(), before, after, decompressionRatio, decompressionSpeed, startTimestamp, end - start);

            logger.info(sql);
        }
    }
}
