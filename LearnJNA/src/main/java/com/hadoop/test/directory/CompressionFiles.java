package com.hadoop.test.directory;

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
 * @date 2018-12-20 11:29
 */
public class CompressionFiles {
    private static final Logger logger = LoggerFactory.getLogger(CompressionFiles.class);
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("local");

    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();

        FileSystem fs = FileSystem.get(conf);

        final String inPathName = resourceBundle.getString("original.directory");
        final String outPathName = resourceBundle.getString("compress.directory");

        RemoteIterator<LocatedFileStatus> fileStatusListIterator = fs.listFiles(new Path(inPathName), true);

        Path hdfswritepath = new Path("/tmp/jna-compression.log");
        FSDataOutputStream outputStream=fs.create(hdfswritepath);

        while (fileStatusListIterator.hasNext()) {
            LocatedFileStatus fileStatus = fileStatusListIterator.next();

            String inPath = fileStatus.getPath().toUri().getPath();
            String outPath = inPath.replace(inPathName, outPathName);

            logger.debug("--> " + inPath);

            long start = System.currentTimeMillis();
            System.out.println(inPath);
            System.out.println(outPath);

            Compressor compressor = new SnappyCompressor(conf);
            compressor.init();
            compressor.compress(inPath, outPath);

            long end = System.currentTimeMillis();

            long before = fs.getFileStatus(new Path(inPath)).getLen();
            long after = fs.getFileStatus(new Path(outPath)).getLen();

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
                            + "'HARDWARECOMPRESS', '%s', %d, %d, %.4f, %.4f,'%s', %d);\n"
                    , inPath, before, after, compressionRatio, compressionSpeed, startTimestamp, end - start);

            outputStream.writeBytes(sql);
            logger.info(sql);
        }

        outputStream.close();
    }
}
