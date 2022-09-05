package com.example.console;

import java.util.*;

//print out the List with frequency order
//Input:  arr[] = {2, 5, 2, 8, 5, 6, 8, 8, 9, 1}
//Output: arr[] = {8, 8, 8, 2, 2, 5, 5, 1, 6, 9}
public class SortListWithFrequency {
    public static List<Integer> sortListWithFrequency(List<Integer> input) {
        Map<Integer, Integer> map = new HashMap<>();
        for(Integer i : input) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        Collections.sort(input, (t1, t2) -> {
            if(map.get(t1).equals(map.get(t2))) {
                return t1 - t2;
            }
            return map.get(t2) - map.get(t1);
        });
        return input;
    }
}
