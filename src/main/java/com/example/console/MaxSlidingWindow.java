package com.example.console;

import java.util.ArrayList;
import java.util.List;

//Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
//You can only see the k numbers in the window. Each time the sliding window moves right by one position.
public class MaxSlidingWindow {

    public static Integer[] maxSlindingWindow(int[] nums, int k){
        if(nums == null || nums.length == 0 || k < 0)
            return null;
        List<Integer> result = new ArrayList<>();

        int j, max;

        for (int i = 0; i <= nums.length - k; i++) {

            max = nums[i];

            for (j = 1; j < k; j++)
            {
                if (nums[i + j] > max)
                    max = nums[i + j];
            }

            result.add(max);
        }

        return result.toArray(new Integer[0]);
    }
}
