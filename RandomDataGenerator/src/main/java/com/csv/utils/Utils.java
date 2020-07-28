package com.csv.utils;

/**
 * @author apktool
 * @package com.csv.utils
 * @class Utils
 * @description TODO
 * @date 2020-07-28 20:54
 */
public class Utils {

    /**
     * Hash an integer value.
     */
    public static long hash(long val) {
        return fnvhash64(val);
    }

    public static final long FNV_OFFSET_BASIS_64 = 0xCBF29CE484222325L;
    public static final long FNV_PRIME_64 = 1099511628211L;

    /**
     * 64 bit FNV hash. Produces more "random" hashes than (say) String.hashCode().
     *
     * @param val The value to hash.
     * @return The hash value
     */
    public static long fnvhash64(long val) {
        //from http://en.wikipedia.org/wiki/Fowler_Noll_Vo_hash
        long hashval = FNV_OFFSET_BASIS_64;

        for (int i = 0; i < 8; i++) {
            long octet = val & 0x00ff;
            val = val >> 8;

            hashval = hashval ^ octet;
            hashval = hashval * FNV_PRIME_64;
            //hashval = hashval ^ octet;
        }
        return Math.abs(hashval);
    }

    /**
     * Reads a big-endian 8-byte long from an offset in the given array.
     *
     * @param bytes The array to read from.
     * @return A long integer.
     * @throws IndexOutOfBoundsException if the byte array is too small.
     * @throws NullPointerException      if the byte array is null.
     */
    public static long bytesToLong(final byte[] bytes) {
        return (bytes[0] & 0xFFL) << 56
                | (bytes[1] & 0xFFL) << 48
                | (bytes[2] & 0xFFL) << 40
                | (bytes[3] & 0xFFL) << 32
                | (bytes[4] & 0xFFL) << 24
                | (bytes[5] & 0xFFL) << 16
                | (bytes[6] & 0xFFL) << 8
                | (bytes[7] & 0xFFL) << 0;
    }

    /**
     * Writes a big-endian 8-byte long at an offset in the given array.
     *
     * @param val The value to encode.
     * @throws IndexOutOfBoundsException if the byte array is too small.
     */
    public static byte[] longToBytes(final long val) {
        final byte[] bytes = new byte[8];
        bytes[0] = (byte) (val >>> 56);
        bytes[1] = (byte) (val >>> 48);
        bytes[2] = (byte) (val >>> 40);
        bytes[3] = (byte) (val >>> 32);
        bytes[4] = (byte) (val >>> 24);
        bytes[5] = (byte) (val >>> 16);
        bytes[6] = (byte) (val >>> 8);
        bytes[7] = (byte) (val >>> 0);
        return bytes;
    }


    /**
     * @return The currently used memory in bytes
     */
    public static long getUsedMemoryBytes() {
        final Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }
}
