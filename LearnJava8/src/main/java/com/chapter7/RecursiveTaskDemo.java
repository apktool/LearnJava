package com.chapter7;

import lombok.AllArgsConstructor;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

@AllArgsConstructor
public class RecursiveTaskDemo extends RecursiveTask<Long> {
    private final long[] numbers;
    private final int start;
    private final int end;

    public static void main(String[] args) {
        long n = 100;
        long[] numbers = LongStream.range(0, n).toArray();
        RecursiveTaskDemo rd = new RecursiveTaskDemo(numbers, 0, numbers.length);
        long result = rd.forJoinSum();
        System.out.println(result);
    }

    @Override
    protected Long compute() {
        int length = end - start;
        long threshold = 10_000;

        if (length <= threshold) {
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += numbers[i];
            }

            return sum;
        }

        RecursiveTaskDemo leftTask = new RecursiveTaskDemo(numbers, start, start + length / 2);
        leftTask.fork();

        RecursiveTaskDemo rightTask = new RecursiveTaskDemo(numbers, start + length / 2, end);
        rightTask.fork();

        Long rightResult = rightTask.compute();
        Long leftResult = leftTask.compute();

        return leftResult + rightResult;
    }

    public long forJoinSum() {
        ForkJoinTask<Long> task = new RecursiveTaskDemo(numbers, 0, numbers.length);
        return new ForkJoinPool().invoke(task);
    }
}
