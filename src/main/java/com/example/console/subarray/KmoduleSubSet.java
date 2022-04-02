package com.example.console.subarray;

import java.util.Arrays;

//You are given an array of positive and/or negative integers and a value K . The task is to find count of
// all sub-arrays whose sum is divisible by K?
//Input  : arr[] = {10, 5, 9, 11, 5},
//         K = 5
//Output : 10
//same:
//Given an unsorted array of integers, find the number of subarrays having sum exactly equal to a given number k.
//Input : arr[] = {10, 2, -2, -20, 10},
//        k = -10
//Output : 3
//Subarrays: arr[0...3], arr[1...4], arr[3..4]
//have sum exactly equal to -10.
//
//Input : arr[] = {9, 4, 20, 3, 10, 5},
//            k = 33
//Output : 2
//Subarrays : arr[0...2], arr[2...4] have sum
//exactly equal to 33.
public class KmoduleSubSet {
    public static long countSubset(int k, int[] input) {
        int[] result = new int[1];
        return countSubsetSum(k, input, 0, 0) - 1;
    }

    //O(2^n)
    private static int countSubsetSum(int k, int[] input, int start, int sum) {
        if(start >= input.length) {
            if(sum % k == 0)
                return 1;
            return 0;
        }
        int count0 = countSubsetSum(k, input, start+1, sum+input[start]);
        int count = countSubsetSum(k, input, start+1, sum);
        return count0 + count;
    }

    //module of K is 0-k-1
    public static long countSubsetWithMem(int k, int[] input) {
        int[][] mem = new int[input.length+1][k];
        return countSubsetSumWithMem(k, input, 0, 0, mem) - 1;
    }

    private static int countSubsetSumWithMem(int k, int[] input, int start, int sum, int[][]mem) {
        if(start >= input.length) {
            if(sum % k == 0)
                return 1;
            return 0;
        }
        if(mem[start][sum] > 0)
            return mem[start][sum];
        int count0 = countSubsetSum(k, input, start+1, (sum+input[start])%k);
        int count = countSubsetSum(k, input, start+1, sum%k);
        mem[start][sum] = count0 + count;
        return mem[start][sum];
    }
}
