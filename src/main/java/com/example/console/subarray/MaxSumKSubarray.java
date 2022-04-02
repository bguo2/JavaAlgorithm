package com.example.console.subarray;
//Partition Array for Maximum Sum
//Given an integer array arr, you should partition the array into (contiguous) subarrays of length at most k. After partitioning,
// each subarray has their values changed to become the maximum value of that subarray.
//
//Return the largest sum of the given array after partitioning.
//
//
//
//Example 1:
//
//Input: arr = [1,15,7,9,2,5,10], k = 3
//Output: 84
//Explanation: arr becomes [15,15,15,9,10,10,10]
//Example 2:
//
//Input: arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4
//Output: 83
//Example 3:
//
//Input: arr = [1], k = 1
//Output: 1
//
//
//Constraints:
//
//1 <= arr.length <= 500
//0 <= arr[i] <= 109
//1 <= k <= arr.length

import java.util.ArrayList;
import java.util.List;

//先来定义 dp 数组，先从最简单的考虑，使用一个一维的 dp 数组，其中 dp[i] 就表示分割数组中的前i个数字组成的数组可以得到的最大的数字之和。
// 下面来考虑状态转移方程怎么求，对于 dp[i] 来说，若把最后k个数字分割出来，那么前i个数字就被分成了两个部分，前 i-k 个数字，其数字之和可以直接由 dp[i-k]
// 来取得，后面的k个数字，则需要求出其中最大的数字，然后乘以k，用这两部分之和来更新 dp[i] 即可。由于题目中说了分割的长度不超过k，那么就是说小于k的也是可以的，
// 则需要遍历 [1, k] 区间所有的长度，均进行分割。接下来看代码，建立一个大小为 n+1 的 dp 数组，然后i从1遍历到n，此时新建一个变量 curMax 记录当前的最大值，
// 然后用j从1遍历到k，同时要保证 i-j 是大于等于0的，因为需要前半部分存在，实际上这是从第i个数字开始往前找j个数字，然后记录其中最大的数字 curMax，
// 并且不断用 dp[i-j] + curMax * j 来更新 dp[i] 即可
//O(n*k)
public class MaxSumKSubarray {
    public static int maxSumAfterPartitioning(int[] arr, int k) {
        int[] dp = new int[arr.length + 1];
        dp[0] = 0;
        for(int i = 1; i <= arr.length; i++) {
            int curMax = 0;
            //1, 2...i-j, i-j+1..i....
            for(int j = 1; j <= Math.min(k, i); j++) {
                curMax = Math.max(curMax, arr[i-j]);
                dp[i] = Math.max(dp[i], dp[i-j] + curMax*j);
            }
        }
        return dp[arr.length];
    }
}
