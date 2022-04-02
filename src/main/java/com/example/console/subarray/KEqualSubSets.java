package com.example.console.subarray;

import java.util.Arrays;

// Partition to K Equal Sum Subsets
//Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into
// k non-empty subsets whose sums are all equal.
//
//
//
//Example 1:
//
//Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
//Output: True
//Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
//
//
//Note:
//
//1 <= k <= len(nums) <= 16.
//0 < nums[i] < 10000.
public class KEqualSubSets {
    public static boolean canPartitionKSubsets(int[] nums, int k) {
        if(nums == null || nums.length == 0)
            return false;

        int sum = Arrays.stream(nums).sum();
        if(sum % k != 0)
            return false;
        boolean[] visited = new boolean[nums.length];
        return canPartition(nums, 0, sum/k, 0, k, visited);
    }
    //O(k*2^n)
    private static boolean canPartition(int[] nums, int start, int target, int curSum, int k, boolean[] visited) {
        if(k == 1) {
            return true;
        }
        if(curSum > target)
            return false;
        if(curSum == target)
            return canPartition(nums, 0, target, 0, k-1, visited);
        for(int i = start; i < nums.length; i++) {
            if(visited[i])
                continue;
            visited[i] = true;
            if(canPartition(nums, i + 1, target, curSum+nums[i], k, visited))
                return true;
            visited[i] = false;
        }
        return false;
    }
}
