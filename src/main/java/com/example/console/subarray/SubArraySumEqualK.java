package com.example.console.subarray;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//Given an array of integers nums and an integer k, return the total number of continuous subarrays whose sum equals to k.
//
//
//
//Example 1:
//
//Input: nums = [1,1,1], k = 2
//Output: 2
//Example 2:
//
//Input: nums = [1,2,3], k = 3
//Output: 2
//
//
//Constraints:
//
//1 <= nums.length <= 2 * 104
//-1000 <= nums[i] <= 1000
//-107 <= k <= 107
//can be use left sum array: if leftSum[j]-leftSum[i-1] = K, then the sum between i and j = k
public class SubArraySumEqualK {
    public static int subarraySum(int[] nums, int k) {
        int count = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            //current left sum - k exists in the left sum map: leftSum[current index] - leftSum[pre index] = K
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            //equivalent to store the left sum array so far in the map
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    //brute force: try all possible sum
    public static int subarraySum1(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; start++) {
            int sum=0;
            for (int end = start; end < nums.length; end++) {
                sum += nums[end];
                if (sum == k)
                    count++;
            }
        }
        return count;
    }

    //Given an array arr[] of length N and an integer X, the task is to find the number of subsets with a sum equal to X.
    //
    //Examples:
    //
    //Input: arr[] = {1, 2, 3, 3}, X = 6
    //Output: 3
    //All the possible subsets are {1, 2, 3},
    //{1, 2, 3} and {3, 3}
    //
    //
    //Input: arr[] = {1, 1, 1, 1}, X = 1
    //Output: 4
    //O(2^n)
    public static int countSubset(int[] nums, int k) {
        return countSubsetRec(nums, k, 0, 0);
    }

    private static int countSubsetRec(int[] nums, int k, int curSum, int index) {
        if(index >= nums.length) {
            if(curSum == k)
                return 1;
            return 0;
        }
        return countSubsetRec(nums, k, curSum+nums[index], index+1) +
                countSubsetRec(nums, k, curSum, index+1);
    }

    //with memory: O(n*sum)
    public static int countSubsetWithMem(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        int[][] mem = new int[nums.length+1][sum+1];
        return countSubsetRecWithMem(nums, k, 0, 0, mem);
    }

    private static int countSubsetRecWithMem(int[] nums, int k, int curSum, int index, int[][] mem) {
        if(index >= nums.length) {
            if(curSum == k)
                return 1;
            return 0;
        }
        if(mem[index][curSum] > 0)
            return mem[index][curSum];
        mem[index][curSum] = countSubsetRec(nums, k, curSum+nums[index], index+1) +
                countSubsetRec(nums, k, curSum, index+1);
        return mem[index][curSum];
    }
}
