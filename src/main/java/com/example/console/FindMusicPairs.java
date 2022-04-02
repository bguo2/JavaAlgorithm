package com.example.console;


import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
//You are given a list of songs where the ith song has a duration of time[i] seconds.
//
//Return  the pairs of songs for which their total duration in seconds is divisible by  60. Formally,
// we want the number of indices i, j such that i < j with (time[i] + time[j]) % 60 == 0.
//Input: time = [30,20,150,100,40]
//Output: [30,150] [20, 100] [20, 40]
//Explanation: Three pairs have a total duration divisible by 60:
//(time[0] = 30, time[2] = 150): total duration 180
//(time[1] = 20, time[3] = 100): total duration 120
//(time[1] = 20, time[4] = 40): total duration 60

//Input: time = [60,60,60]
//Output: [60, 60] [60, 60] [60, 60]
//Explanation: All three pairs have a total duration of 120, which is divisible by 60.
public class FindMusicPairs {
    public static List<int[]> getPairs(int[] times) {
        //List<Integer>: index of key
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<int[]> result = new ArrayList<>();
        List<Integer> list;
        for(int i = 0; i < times.length; i++) {
            Integer time = times[i] % 60;
            int timeToFind = time == 0 ? 0 : 60-time;
            if(map.containsKey(timeToFind)) {
                list = map.get(timeToFind);
                //output result
                for(Integer index: list) {
                    result.add(new int[] { times[index], times[i] });
                }
            }

            //get time's list
            if(map.containsKey(time)) {
                list = map.get(time);
            }
            else {
                list = new ArrayList<>();
            }
            list.add(i);
            map.put(time, list);
        }

        return result;
    }

    //just need the count
    public static int getPairsCount(int[] times) {
        //List<Integer>: index of key
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for(int i = 0; i < times.length; i++) {
            Integer time = times[i] % 60;
            int timeToFind = time == 0 ? 0 : 60-time;
            if(map.containsKey(timeToFind)) {
                count += map.get(timeToFind);
            }
            map.put(time, map.getOrDefault(time, 0)+1);
        }

        return count;
    }
}
