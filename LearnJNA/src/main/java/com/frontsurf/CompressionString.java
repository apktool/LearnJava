package com.frontsurf;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.IntByReference;

/**
 * @author li.wengang
 * @date 2018-11-28 16:18
 */
public class CompressionString {

    public static void main(String[] args) {
        int res;
        Snappy.INSTANCE.CC_apiSysInit(true);

        /*
        final char[] ip = {
                0x8f, 0xca, 0x94, 0xb5, 0x09, 0x55, 0x43, 0xf4,
                0xa6, 0xb2, 0xd3, 0x93, 0x20, 0x82, 0xee, 0xde,
                0xa0, 0x15, 0x34, 0x4c, 0xd7, 0x30, 0xe9, 0x60,
                0x4a, 0x0e, 0xea, 0xcd, 0xed, 0xa4, 0xe6, 0xdd,
                0xf4, 0x2a, 0xca, 0x13, 0xe6, 0xd6, 0x7d, 0x10,
                0xf3, 0xeb, 0xce, 0xb3, 0x87, 0xf2, 0xab, 0x84,
                0x1f, 0x47, 0x93, 0x53, 0x5c, 0xd8, 0x4d, 0x1c,
                0x55, 0x2a, 0xdb, 0x2e, 0x32, 0x9d, 0xaa, 0x8c,
                0xf7, 0xf0, 0x0b, 0x94, 0x9d, 0x07, 0xe7, 0xde,
                0x67, 0xec, 0xad, 0x67, 0x73, 0x15, 0x90, 0xef,
                0x90, 0xe5, 0xf0, 0x9f, 0x4f, 0x74, 0x15, 0xef,
                0x5d, 0x93, 0x23, 0xc7, 0x0f, 0x0b, 0x4a, 0xe4,
                0x3f, 0x26, 0x3e, 0x7e, 0x57, 0x71, 0xe7, 0x28,
                0xab, 0xbf, 0x59, 0xf7, 0x0c, 0x71, 0x06, 0xe6,
                0x99, 0xf5, 0x32, 0x79, 0xd9, 0x8d, 0xa8, 0xa1,
                0x07, 0x52, 0xac, 0xe0, 0xad, 0x78, 0x30, 0xad
        };
        */

        final byte[] ip = {
                0x61, 0x62, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68,
                0x61, 0x62, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68,
                0x68, 0x67, 0x66, 0x65, 0x64, 0x63, 0x62, 0x61,
                0x68, 0x67, 0x66, 0x65, 0x64, 0x63, 0x62, 0x61
        };

        int ipLength = ip.length;

        byte[] op = new byte[ip.length * 2];
        IntByReference opLength = new IntByReference(op.length);

        Snappy.INSTANCE.snappy_compress(ip, ipLength, op, opLength);

        for (int i = 0; i < opLength.getValue(); i++) {
            if (i % 8 == 0) {
                System.out.println();
            }
            System.out.printf("0x%08x, ", (int) op[i]);
        }

        System.out.println();
        System.out.println("---------------------------");

        byte[] ipS = new byte[ipLength];
        IntByReference ipSlength = new IntByReference(ipS.length);

        Snappy.INSTANCE.snappy_uncompress(op, opLength.getValue(), ipS, ipSlength);

        for (int i = 0; i < ipSlength.getValue(); i++) {
            if (i % 8 == 0) {
                System.out.println();
            }
            System.out.printf("0x%08x, ", (int) ipS[i]);
        }

        System.out.println();

        Snappy.INSTANCE.CC_apiSysExit();
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
