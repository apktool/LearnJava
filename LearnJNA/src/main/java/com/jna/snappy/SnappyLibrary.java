package com.jna.snappy;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.IntByReference;

/**
 * @author li.wengang
 * @date 2018-12-03 15:25
 */
public interface SnappyLibrary extends Library {
    SnappyLibrary INSTANCE = Native.load("/tmp/libsnappy.so", SnappyLibrary.class);

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
