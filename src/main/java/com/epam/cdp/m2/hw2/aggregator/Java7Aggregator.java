package com.epam.cdp.m2.hw2.aggregator;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Java7Aggregator implements Aggregator {

    @Override
    public int sum(List<Integer> numbers) {
        int sum = 0;
        for (Integer number : numbers) {
            sum += number;
        }
        return sum;
    }

    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        HashMap<String, Long> result = new HashMap<>();
        Long counter = 0L;
        String currentWord;
        for (int i = 0; i < words.size(); i++) {
            currentWord = words.get(i);
            if (!result.containsKey(currentWord)) {
                for (String word : words) {
                    if (word.equals(currentWord)) {
                        counter++;
                    }
                }
                result.put(currentWord, counter);
                counter = 0L;
            }
        }

        //convert HashMap into List
        List<Map.Entry<String, Long>> list = new LinkedList<Map.Entry<String, Long>>(result.entrySet());
        //sorting the list elements
        list.sort(new Comparator<>() {
            @Override
            public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                if (o2.getValue().compareTo(o1.getValue()) == 0) {
                    return o1.getKey().compareTo(o2.getKey());
                }
                return o2.getValue().compareTo(o1.getValue());
            }
        });


        // cut map to limit
        List<Pair<String, Long>> sortedLimitedResult = new ArrayList<>();
        Set<Map.Entry<String, Long>> set = new LinkedHashSet<>(list);
        Map.Entry<String, Long>[] entryArray
                = set.toArray(
                new Map.Entry[set.size()]);
        for (int i = 0; i < limit; i++) {
            if (i == entryArray.length) {
                break;
            }
            sortedLimitedResult.add(new Pair<>(entryArray[i].getKey(), entryArray[i].getValue()));
        }

        return sortedLimitedResult;
    }


    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        Set<String> duplicates = new TreeSet<>();
        String tempString;
        for (int i = 0; i < words.size(); i++) {
            tempString = words.get(i);
            for (int j = i + 1; j < words.size(); j++) {
                if (words.get(j).equalsIgnoreCase(tempString)) {
                    duplicates.add(tempString.toUpperCase());
                }
            }
        }

        if (limit > duplicates.size()) {
            return new ArrayList<>(duplicates);
        }
        return new ArrayList<>(duplicates).subList(0, (int) limit);

    }
}
