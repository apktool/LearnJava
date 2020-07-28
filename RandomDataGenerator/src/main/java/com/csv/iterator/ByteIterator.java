package com.csv.iterator;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * @author apktool
 * @package com.csv
 * @class ByteIterator
 * @description TODO
 * @date 2020-07-28 20:18
 */
public abstract class ByteIterator implements Iterator<Byte> {

    public abstract byte nextByte();

    public abstract long bytesLeft();

    @Override
    public abstract boolean hasNext();

    @Override
    public Byte next() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    public void reset() {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        Charset cset = Charset.forName("UTF-8");
        CharBuffer cb = cset.decode(ByteBuffer.wrap(toArray()));
        return cb.toString();
    }

    private byte[] toArray() {
        long left = bytesLeft();
        if (left != (int) left) {
            throw new ArrayIndexOutOfBoundsException("Too much data to fit in one array!");
        }
        byte[] ret = new byte[(int) left];
        int off = 0;
        while (off < ret.length) {
            off = nextBuf(ret, off);
        }
        return ret;
    }

    protected int nextBuf(byte[] buf, int bufOff) {
        int sz = bufOff;
        while (sz < buf.length && hasNext()) {
            buf[sz] = nextByte();
            sz++;
        }
        return sz;
    }
}
