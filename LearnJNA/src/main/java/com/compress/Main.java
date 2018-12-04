package com.compress;


import com.compress.test.Checksum;
import com.compress.test.Compression;
import com.compress.test.Decompression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author li.wengang
 * @date 2018-12-03 16:08
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        if (args.length == 0 || args.length > 1) {
            logger.error("Error arguments!");
            return;
        }

        if (args[0].equals("compress")) {
            logger.debug("Compress...");
            Compression.main(args);
        } else if (args[0].equals("decompress")) {
            logger.debug("Decompress...");
            Decompression.main(args);
        } else if (args[0].equals("checksum")) {
            logger.debug("Checksum...");
            Checksum.main(args);
        } else {
            logger.error("Error arguments!");
        }
    }
}
