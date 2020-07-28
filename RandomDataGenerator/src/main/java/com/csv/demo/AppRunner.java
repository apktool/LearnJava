package com.csv.demo;

import com.csv.generator.CounterGenerator;
import com.csv.generator.Generator;
import com.csv.generator.StringGenerator;
import com.csv.generator.UnixEpochTimestampGenerator;
import com.csv.iterator.RandomByteIterator;
import com.csv.iterator.StringByteIterator;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

/**
 * @author apktool
 * @package com.csv.demo
 * @class AppRunner
 * @description TODO
 * @date 2020-07-28 22:03
 */
@Slf4j
public class AppRunner {
    private OutputStream outputStream;
    private Generator keySequence;

    public AppRunner(String fileName, int start) {
        keySequence = new CounterGenerator(start);
        try {
            outputStream = new FileOutputStream(fileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public static void main(String[] args) throws IOException {
        AppRunner runner = new AppRunner("/tmp/abc/" + Thread.currentThread().getName(), 0);

        for (int i = 0; i < 10; i++) {
            try {
                runner
                        .appendKeyToStream()
                        .appendDelimiterToStream(",")
                        .appendTimestampToStream()
                        .appendDelimiterToStream(",")
                        .appendLipsumToStream(10)
                        .appendDelimiterToStream("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        runner.build();

    }

    public AppRunner appendKeyToStream() throws IOException {
        StringGenerator generator = new StringGenerator(Long.parseLong(keySequence.nextString()));
        StringByteIterator iterator = new StringByteIterator(generator.nextString());

        outputStream.write("user".getBytes());

        while (iterator.hasNext()) {
            outputStream.write(iterator.nextByte());
        }

        return this;
    }

    public AppRunner appendTimestampToStream() throws IOException {
        UnixEpochTimestampGenerator generator = new UnixEpochTimestampGenerator(1, TimeUnit.MILLISECONDS);
        StringByteIterator iterator = new StringByteIterator(generator.nextString());

        while (iterator.hasNext()) {
            outputStream.write(iterator.nextByte());
        }

        return this;
    }

    public AppRunner appendLipsumToStream(int length) throws IOException {
        RandomByteIterator iterator = new RandomByteIterator(length);
        while (iterator.hasNext()) {
            byte bytes = iterator.nextByte();

            if (bytes == ",".getBytes()[0]) {
                bytes = iterator.nextByte();
            }

            outputStream.write(bytes);
        }

        return this;
    }

    public AppRunner appendDelimiterToStream(String delimiter) throws IOException {
        outputStream.write(delimiter.getBytes());
        return this;
    }

    public void build() {
        try {
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
