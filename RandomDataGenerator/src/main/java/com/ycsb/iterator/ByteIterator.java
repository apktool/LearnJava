package com.ycsb.iterator;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Iterator;

public abstract class ByteIterator implements Iterator<Byte> {

    @Override
    public abstract boolean hasNext();

    @Override
    public Byte next() {
        throw new UnsupportedOperationException();
    }

    public abstract byte nextByte();

    /** @return byte offset immediately after the last valid byte */
    public int nextBuf(byte[] buf, int bufOff) {
        int sz = bufOff;
        while (sz < buf.length && hasNext()) {
            buf[sz] = nextByte();
            sz++;
        }
        return sz;
    }

    public abstract long bytesLeft();

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    /** Resets the iterator so that it can be consumed again. Not all
     * implementations support this call.
     * @throws UnsupportedOperationException if the implementation hasn't implemented
     * the method.
     */
    public void reset() {
        throw new UnsupportedOperationException();
    }

    /** Consumes remaining contents of this object, and returns them as a string. */
    public String toString() {
        Charset cset = Charset.forName("UTF-8");
        CharBuffer cb = cset.decode(ByteBuffer.wrap(this.toArray()));
        return cb.toString();
    }

    /** Consumes remaining contents of this object, and returns them as a byte array. */
    public byte[] toArray() {
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

}
