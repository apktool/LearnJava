package com.frontsurf;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.IntByReference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

/**
 * @author li.wengang
 * @date 2018-11-28 16:18
 */
public class CompressionFile {
    private static final Logger logger = LogManager.getLogger(CompressionFile.class);

    public static void main(String[] args) throws IOException {
        Snappy.INSTANCE.CC_apiSysInit(true);

        if (args.length != 2) {
            logger.info("Please input true parameters");
            return;
        }

        String inputFile = args[0];
        DataInputStream dataInputStream = new DataInputStream(new FileInputStream(inputFile));

        int len = dataInputStream.available();

        byte[] input = new byte[len];
        dataInputStream.read(input);
        dataInputStream.close();

        byte[] output = new byte[len * 2];

        Snappy.INSTANCE.snappy_compress(input, input.length, output, new IntByReference(output.length));

        byte[] ipS = new byte[len];
        CompressionFile.Snappy.INSTANCE.snappy_uncompress(output, output.length, ipS, new IntByReference(ipS.length));

        String outputFile = args[1];
        DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(outputFile));
        dataOutputStream.write(ipS);

        CompressionFile.Snappy.INSTANCE.CC_apiSysExit();
    }

    private interface Snappy extends Library {
        Snappy INSTANCE = Native.load("/tmp/libsnappy.so", Snappy.class);

        /**
         * CC_status CC_apiSysInit(CC_BOOL asyncSupport);
         */
        int CC_apiSysInit(boolean asyncSupport);

        /**
         * snappy_status snappy_compress(const char* input, size_t input_length, char* compressed, size_t* compressed_length);
         */
        int snappy_compress(byte[] input, int input_length, byte[] compressed, IntByReference compressed_length);

        /**
         * snappy_status snappy_uncompress(const char* compressed, size_t compressed_length, char* uncompressed, size_t* uncompressed_length);
         */
        int snappy_uncompress(byte[] compressed, int compressed_length, byte[] uncompressed, IntByReference uncompressed_length);

        /**
         * CC_status CC_apiSysExit(void);
         */
        int CC_apiSysExit();
    }
}
