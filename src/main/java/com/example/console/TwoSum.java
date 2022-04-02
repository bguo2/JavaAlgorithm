package com.example.console;

import java.util.*;

public class TwoSum {
    //find the index so that nums[i] + nums[j] = target;
    public static List<int[]> getIndexOfPairs(int[] nums, int target) {
        List<int[]> result = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            if(map.containsKey(target - nums[i])) {
                result.add(new int[] { map.get(target - nums[i]), i });
            }
            map.put(nums[i], i);
        }
        return result;
    }

    //give nums and k, return count different pairs such that a+k=b
    //e.g. [1,1,2,2,3,3] k = 1, different pairs are (1,1), (1,2), (2,2), (2,3), (3,3), only (1,2) and (2,3) can meet (a+k, b)
    //so count=2. if k=0, then it is (1,1), (2,2), (3,3)
    public static int countOfDistinctPairs(int[] nums, int k) {
        int count = 0;
        Set<Integer> set = new HashSet<>();
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
            if(k == 0) {
                if(map.containsKey(nums[i])) {
                    if(map.get(nums[i]) == 0)
                        count++;
                    //count alreay, set to 1
                    map.put(nums[i], 1);
                }
                else
                    map.put(nums[i], 0);
            }
        }

        if(k == 0)
            return count;

        for(int i = 0; i < nums.length; i++) {
            int a = nums[i] - k;
            if(set.contains(a)) {
                count++;
                //remove duplicates
                if(map.containsKey(a) && map.get(a) == nums[i]) {
                    count--;
                }
                //add pair to map
                map.put(a, nums[i]);
            }
        }
        return count;
    }
}
