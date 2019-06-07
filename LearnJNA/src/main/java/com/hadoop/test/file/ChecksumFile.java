package com.hadoop.test.file;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileChecksum;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author li.wengang
 * @date 2018-12-04 16:39
 */
public class ChecksumFile {
    private static final Logger logger = LoggerFactory.getLogger(ChecksumFile.class);

    public static void main(String[] args) {

        Configuration conf = new Configuration();
        FileChecksum inputCheckSum1 = null;
        FileChecksum inputCheckSum2 = null;

        try {
            FileSystem fs = FileSystem.get(conf);
            inputCheckSum1 = fs.getFileChecksum(new Path(args[0]));
            inputCheckSum2 = fs.getFileChecksum(new Path(args[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (inputCheckSum1.equals(inputCheckSum2)) {
            logger.info("SAME CONTENTS");
        } else {
            logger.info("DIFFERENT CONTENTS");
        }

    }
}
