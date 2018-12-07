package com.compress.test.directory;

import com.compress.snappy.Decompressor;
import com.compress.snappy.impl.SnappyDecompressor;
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
public class DecompressionFiles {
    private static final Logger logger = LoggerFactory.getLogger(DecompressionFiles.class);
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("local");

    public static void main(String[] args) throws IOException {

        final File inPathName = new File(resourceBundle.getString("compress.directory"));
        final File outPathName = new File(resourceBundle.getString("decompress.directory"));

        Iterator<File> fileIterator = FileUtils.iterateFiles(inPathName, null, true);
        while (fileIterator.hasNext()) {
            File file = fileIterator.next();
            byte[] input = FileUtils.readFileToByteArray(file);

            int abc = input.length * 2;
            for (int i = 2; i < 50; i++) {
                int abs = Math.abs(input.length * i);
                if (abs < Integer.MAX_VALUE - 1) {
                    abc = abs;
                }
            }

            byte[] decompressOutput = new byte[abc];

            long start = System.currentTimeMillis();

            Decompressor decompressor = new SnappyDecompressor();
            decompressor.init();
            int len = decompressor.decompress(input, input.length, decompressOutput);
            decompressor.exit();

            long end = System.currentTimeMillis();

            String path = file.getAbsolutePath().replace(inPathName.getAbsolutePath(), outPathName.getAbsolutePath());
            File tmp = new File(path);

            FileUtils.writeByteArrayToFile(tmp, Arrays.copyOf(decompressOutput, len));
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
