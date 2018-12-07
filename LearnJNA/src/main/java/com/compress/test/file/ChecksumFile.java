package com.compress.test.file;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * @author li.wengang
 * @date 2018-12-04 16:39
 */
public class ChecksumFile {
    private static final Logger logger = LoggerFactory.getLogger(ChecksumFile.class);

    public static void main(String[] args) {
        final File inPathName = new File(args[0]);
        final File outPathName = new File(args[1]);

        long inputCRC32 = 0;
        long outputCRC32 = -1;

        try {
            inputCRC32 = FileUtils.checksumCRC32(inPathName);
            outputCRC32 = FileUtils.checksumCRC32(outPathName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (inputCRC32 == outputCRC32) {
            logger.info("SAME CONTENTS");
        } else {
            logger.info("DIFFERENT CONTENTS");
        }

    }
}
