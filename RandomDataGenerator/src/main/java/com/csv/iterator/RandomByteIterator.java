package com.csv.iterator;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author apktool
 * @package com.csv.iterator
 * @class RandomByteIterator
 * @description TODO
 * @date 2020-07-28 21:13
 */

public class RandomByteIterator extends ByteIterator {
    private long len;
    private long off;
    private int bufOff;
    private byte[] buf;

    public RandomByteIterator(long len) {
        this.len = len;
        this.buf = new byte[6];
        this.bufOff = buf.length;
        fillBytes();
        this.off = 0;
    }

    @Override
    public boolean hasNext() {
        return (off + bufOff) < len;
    }

    private void fillBytesImpl(byte[] buffer, int base) {
        int bytes = ThreadLocalRandom.current().nextInt();

        switch (buffer.length - base) {
            default:
                buffer[base + 5] = (byte) (((bytes >> 25) & 95) + ' ');
            case 5:
                buffer[base + 4] = (byte) (((bytes >> 20) & 63) + ' ');
            case 4:
                buffer[base + 3] = (byte) (((bytes >> 15) & 31) + ' ');
            case 3:
                buffer[base + 2] = (byte) (((bytes >> 10) & 95) + ' ');
            case 2:
                buffer[base + 1] = (byte) (((bytes >> 5) & 63) + ' ');
            case 1:
                buffer[base + 0] = (byte) (((bytes) & 31) + ' ');
            case 0:
                break;
        }
    }

    private void fillBytes() {
        if (bufOff == buf.length) {
            fillBytesImpl(buf, 0);
            bufOff = 0;
            off += buf.length;
        }
    }

    public byte nextByte() {
        fillBytes();
        bufOff++;
        return buf[bufOff - 1];
    }

    @Override
    public int nextBuf(byte[] buffer, int bufOffset) {
        int ret;
        if (len - off < buffer.length - bufOffset) {
            ret = (int) (len - off);
        } else {
            ret = buffer.length - bufOffset;
        }
        int i;
        for (i = 0; i < ret; i += 6) {
            fillBytesImpl(buffer, i + bufOffset);
        }
        off += ret;
        return ret + bufOffset;
    }

    @Override
    public long bytesLeft() {
        return len - off - bufOff;
    }

    @Override
    public void reset() {
        off = 0;
    }

}
