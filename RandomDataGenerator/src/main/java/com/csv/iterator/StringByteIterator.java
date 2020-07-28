package com.csv.iterator;

/**
 * @author apktool
 * @package com.csv
 * @class StringByteIterator
 * @description TODO
 * @date 2020-07-28 20:22
 */
public class StringByteIterator extends ByteIterator {
    private String str;
    private int off;

    public StringByteIterator(String s) {
        this.str = s;
        this.off = 0;
    }

    @Override
    public boolean hasNext() {
        return off < str.length();
    }

    @Override
    public byte nextByte() {
        byte ret = (byte) str.charAt(off);
        off++;
        return ret;
    }

    @Override
    public long bytesLeft() {
        return str.length() - off;
    }

    @Override
    public void reset() {
        off = 0;
    }

    @Override
    public String toString() {
        if (off > 0) {
            return super.toString();
        } else {
            return str;
        }
    }
}
