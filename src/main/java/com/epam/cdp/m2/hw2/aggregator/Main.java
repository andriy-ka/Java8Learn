package com.epam.cdp.m2.hw2.aggregator;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Java7Aggregator java7Aggregator = new Java7Aggregator();
        Java8Aggregator java8Aggregator = new Java8Aggregator();
        System.out.println(java7Aggregator.sum(List.of(1, 3, 4, 2)));
        System.out.println(java7Aggregator.getMostFrequentWords(List.of("b", "a", "a", "b"), 2));
        System.out.println(java7Aggregator.getDuplicates(List.of("go", "lol", "stop", "GO", "AA", "aa"), 1));
        System.out.println(java8Aggregator.sum(List.of(1, 3, 4, 2)));
        System.out.println(java8Aggregator.getMostFrequentWords(List.of("bla", "ko", "bla", "lomo", "lomo", "bla"), 2));
        System.out.println(java8Aggregator.getDuplicates(List.of("go", "lol", "stop", "GO", "aa", "aa"), 3));
    }
}
