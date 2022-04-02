package com.example.console.DynamicPg;

import java.util.*;

//Increasing Triplet Subsequence
//Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
//
//Formally the function should:
//
//Return true if there exists  i, j, k
//such that  arr[i]  <  arr[j]  <  arr[k]  given 0 ≤  i  <  j  <  k  ≤  n -1 else return false.
//
//
//
//Your algorithm should run in O( n ) time complexity and O( 1 ) space complexity.
//
//Examples:
//Given [1, 2, 3, 4, 5],
//return true.
//
//Given [5, 4, 3, 2, 1],
//return false.
public class Triplets {

    //dp[i]表示在i位置之前小于等于nums[i]的数字的个数(包括其本身)，我们初始化dp数组都为1，然后我们开始遍历原数组，对当前数字nums[i]，
    // 我们遍历其之前的所有数字，如果之前某个数字nums[j]小于nums[i]，那么我们更新dp[i] = max(dp[i], dp[j] + 1)，如果此时dp[i]到3了，则返回true，
    // 若遍历完成，则返回false
    public static boolean increasingTriplet(int[] input) {
        int[] dp = new int[input.length];
        Arrays.fill(dp, 1);
        for(int i = 0; i < input.length; i++) {
            for(int j = 0; j < i; j++) {
                if(input[j] < input[i]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                    //there are 3 numbers meet the condition
                    if(dp[i] > 3) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //You are given an array and you need to find number of tripets of indices  such that the elements at those indices are in geometric progression for a given common ratio  and .
    //
    //Example
    //[1,4,16,64] r=4
    //
    //There are [1,4,16] and [4,16,64]  at indices 0,1  Return 2.
    //
    //Function Description
    //
    //Complete the countTriplets function in the editor below.
    //
    //countTriplets has the following parameter(s):
    //
    //int arr[n]: an array of integers
    //int r: the common ratio
    //Returns
    //
    //int: the number of triplets
    //e.g. [1 2 2 4]
    //potential: each number's occurance number
    //counter: counter number of the previous one that meets the ratios
    public static long countTriplets(List<Long> arr, long r) {
        Map<Long, Long> potential = new HashMap<>();
        Map<Long, Long> counter = new HashMap<>();
        long count = 0;
        for(int i = 0; i < arr.size(); i++) {
            long a = arr.get(i);
            long key = a / r;
            if (counter.containsKey(key) && a % r == 0) {
                count += counter.get(key);
            }

            if (potential.containsKey(key) && a % r == 0) {
                long c = potential.get(key);
                counter.put(a, counter.getOrDefault(a, 0L) + c);
            }

            // Every number can be the start of a triplet.
            potential.put(a, potential.getOrDefault(a, 0L) + 1);
        }

        return count;
    }
}
