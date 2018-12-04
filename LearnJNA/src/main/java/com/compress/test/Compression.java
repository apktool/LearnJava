package com.compress.test;

import com.compress.snappy.Compressor;
import com.compress.snappy.impl.SnappyCompressor;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ResourceBundle;

/**
 * @author li.wengang
 * @date 2018-12-04 10:55
 */
public class Compression {
    private static final Logger logger = LoggerFactory.getLogger(Compression.class);
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("local");

    public static void main(String[] args) throws IOException {

        final File inPathName = new File(resourceBundle.getString("original.directory"));
        final File outPathName = new File(resourceBundle.getString("compress.directory"));

        FileUtils.forceMkdir(inPathName);
        FileUtils.forceMkdir(outPathName);

        Iterator<File> fileIterator = FileUtils.iterateFiles(inPathName, null, true);
        while (fileIterator.hasNext()) {
            File file = fileIterator.next();
            byte[] input = FileUtils.readFileToByteArray(file);
            byte[] compressOutput = new byte[input.length * 2];

            long start = System.currentTimeMillis();

            Compressor compressor = new SnappyCompressor();
            compressor.init();
            int len = compressor.compress(input, input.length, compressOutput);
            compressor.exit();

            long end = System.currentTimeMillis();

            String path = file.getAbsolutePath().replace(inPathName.getAbsolutePath(), outPathName.getAbsolutePath());
            File tmp = new File(path);

            FileUtils.writeByteArrayToFile(tmp, Arrays.copyOf(compressOutput, len));
            long before = FileUtils.sizeOf(file);
            long after = FileUtils.sizeOf(tmp);

            double compressionRatio = after * 1.0 / before;
            double compressionSpeed = before * 1.0 / (end - start);

            String startTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(start);

            String sql = String.format("INSERT INTO decompress_test (decompress_type, file_name, file_size_before, file_size_after, "
                            + "decompression_ratio, decompression_speed, start_time, duration) VALUES ("
                            + "'HARDWAREDECOMPRESS', '%s', %d, %d, %.4f, %.4f,'%s', %d);"
                    , file.getAbsolutePath(), before, after, compressionRatio, compressionSpeed, startTimestamp, end - start);

            logger.info(sql);
        }
    }
}
