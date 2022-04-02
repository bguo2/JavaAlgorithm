package com.example.console;

import java.util.*;

//print out the List with frequency order
//Input:  arr[] = {2, 5, 2, 8, 5, 6, 8, 8, 9, 1}
//Output: arr[] = {8, 8, 8, 2, 2, 5, 5, 1, 6, 9}
public class SortListWithFrequency {
    //mean T extends any class which is a super class of T and implements Comparable
    //private static class SortedList<T extends Comparable<? super T>> implements Comparator<T> {
    private static class SortedList implements Comparator<Integer> {
        final Map<Integer, Integer> map;
        SortedList(Map<Integer, Integer> map) {
            this.map = map;
        }

        @Override
        public int compare(Integer t1, Integer t2) {
            //value equal: compare key
            if(map.get(t1).equals(map.get(t2))) {
                return t1.compareTo(t2);
            }
            return map.get(t2).compareTo(map.get(t1));
        }
    }

    public static List<Integer> sortListWithFrequency(List<Integer> input) {
        Map<Integer, Integer> map = new HashMap<>();
        for(Integer i : input) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        Collections.sort(input, new SortedList(map));
        return input;
    }
}
