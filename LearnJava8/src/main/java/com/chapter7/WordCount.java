package com.chapter7;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class WordCount {
    public static void main(String[] args) {
        final String SENTENCE = "Nel mezzo del cammin di nostra vita mi ritrovai in una selva oscura ché la dritta via era smarrita ";

        int result1 = WordCounter.countWordsIteratively(SENTENCE);
        System.out.println(result1);

        Stream<Character> stream1 = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);
        int result2 = WordCounter.countWords(stream1);
        System.out.println(result2);


        Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
        Stream<Character> stream2 = StreamSupport.stream(spliterator, true);
        int result3 = WordCounter.countWords(stream2);
        System.out.println(result3);
    }

}
