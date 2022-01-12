package com.epam.cdp.m2.hw2.aggregator;

import javafx.util.Pair;

import java.util.List;
import java.util.concurrent.RecursiveTask;

public class Java7ParallelAggregator extends RecursiveTask<Integer> implements Aggregator  {

    private List<Integer> numbers;

    Java7ParallelAggregator(List<Integer> numbers){
        this.numbers = numbers;
    }

    @Override
    protected Integer compute() {
        if(numbers.size() <= 1){
            return numbers.get(0);
        }
        else if(numbers.size() <= 2){
            return numbers.get(0) + numbers.get(1);
        }
        Java7ParallelAggregator firstHalfList = new Java7ParallelAggregator(numbers.subList(0, numbers.size()/2));
        Java7ParallelAggregator secondHalfList = new Java7ParallelAggregator(numbers.subList(numbers.size()/2, numbers.size()));
        firstHalfList.fork();
        secondHalfList.fork();
        return firstHalfList.join() + secondHalfList.join();
    }

    @Override
    public int sum(List<Integer> list) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        throw new UnsupportedOperationException();
    }
}
