package com.chapter3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ExecuteAround {
    private static final String path = ExecuteAround.class.getClassLoader().getResource("data.txt").getPath();

    public static void main(String[] args) throws IOException {
        // method we want to refactor to make more flexible
        String result = processFileLimited();
        System.out.println(result);

        System.out.println("---");

        String oneLine = processFile((BufferedReader b) -> b.readLine());
        System.out.println(oneLine);

        String twoLines = processFile((BufferedReader b) -> b.readLine() + b.readLine());
        System.out.println(twoLines);
    }

    private static String processFileLimited() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        }
    }

    private static String processFile(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return p.process(br);
        }
    }

    private interface BufferedReaderProcessor {
        public String process(BufferedReader b) throws IOException;
    }
}
