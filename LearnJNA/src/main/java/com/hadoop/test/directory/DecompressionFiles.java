package com.hadoop.test.directory;

import com.hadoop.snappy.Decompressor;
import com.hadoop.snappy.impl.SnappyDecompressor;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

/**
 * @author li.wengang
 * @date 2018-12-20 11:29
 */
public class DecompressionFiles {
    private static final Logger logger = LoggerFactory.getLogger(DecompressionFiles.class);
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("local");

    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();

        FileSystem fs = FileSystem.get(conf);

        final String inPathName = resourceBundle.getString("compress.directory");
        final String outPathName = resourceBundle.getString("decompress.directory");

        RemoteIterator<LocatedFileStatus> fileStatusListIterator = fs.listFiles(new Path(inPathName), true);

        while (fileStatusListIterator.hasNext()) {
            LocatedFileStatus fileStatus = fileStatusListIterator.next();

            String inPath = fileStatus.getPath().toUri().getPath();
            String outPath = inPath.replace(inPathName, outPathName);

            logger.debug("--> " + inPath);

            long start = System.currentTimeMillis();

            Decompressor decompressor = new SnappyDecompressor(conf);
            decompressor.init();
            decompressor.decompress(inPath, outPath);

            long end = System.currentTimeMillis();

            long before = fs.getFileStatus(new Path(inPath)).getLen();
            long after = fs.getFileStatus(new Path(outPath)).getLen();

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
                    , inPath, before, after, decompressionRatio, decompressionSpeed, startTimestamp, end - start);


            logger.info(sql);
        }
    }
}
