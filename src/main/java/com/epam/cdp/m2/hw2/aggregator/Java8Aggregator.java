package com.epam.cdp.m2.hw2.aggregator;

import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class Java8Aggregator implements Aggregator {

    @Override
    public int sum(List<Integer> numbers) {
        return numbers.stream().mapToInt(Integer::valueOf).sum();
    }

    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        Map<String, Long> map = words.stream()
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()));

        return map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(el->new Pair<>(el.getKey(),el.getValue()))
                .limit(limit)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        Set<String> result = new HashSet<>();
        return words.stream().map(String::toUpperCase).filter(n -> !result.add(n)).sorted().limit((int) limit).collect(Collectors.toList());
    }
}