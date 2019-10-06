package com.glispa.csvad.utils;

import java.util.*;

public class ListUtils {
    public static <T> List<T> getRandomWithoutDuplicates(int count, List<T> list) {
        List<T> resultList = new ArrayList<>();
        for(Integer idx: generateRandomIndexesWithoutDuplicates(count, list.size())) {
            resultList.add(list.get(idx));
        }
        return resultList;
    }

    private static Set<Integer> generateRandomIndexesWithoutDuplicates(int count, int max) {
        if (max < count){
            throw new IllegalArgumentException("Count can't be more than max value");
        }
        Random random = new Random();
        Set<Integer> resultSet = new LinkedHashSet<>();
        while (resultSet.size() < count) {
            Integer next = random.nextInt(max);
            resultSet.add(next);
        }
        return resultSet;
    }
}