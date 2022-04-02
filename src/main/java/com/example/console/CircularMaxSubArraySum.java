package com.example.console;

//Given a circular array C of integers represented by A, find the maximum possible sum of a non-empty subarray of C.
//
//Here, a circular array means the end of the array connects to the beginning of the array.  (Formally, C[i] = A[i]
// when 0 <= i < A.length, and C[i+A.length] = C[i] when i >= 0.)
//
//Also, a subarray may only include each element of the fixed buffer A at most once.  (Formally, for a subarray C[i], C[i+1], ...,
// C[j], there does not exist i <= k1, k2 <= j with k1 % A.length = k2 % A.length.)
//
//
//
//Example 1:
//
//Input: [1,-2,3,-2]
//Output: 3
//Explanation: Subarray [3] has maximum sum 3
//Example 2:
//
//Input: [5,-3,5]
//Output: 10
//Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10
//Example 3:
//
//Input: [3,-1,2,-1]
//Output: 4
//Explanation: Subarray [2,-1,3] has maximum sum 2 + (-1) + 3 = 4
//Example 4:
//
//Input: [3,-2,2,-3]
//Output: 3
//Explanation: Subarray [3] and [3,-2,2] both have maximum sum 3
//Example 5:
//
//Input: [-2,-3,-1]
//Output: -1
//Explanation: Subarray [-1] has maximum sum -1

import java.util.Arrays;

//只要用之前的方法求出子数组的最小和，用数组总数字和一减，同样可以得到最大和。两种情况的最大和都要计算出来，取二者之间的较大值才是真正的和最大的子数组。
// 但是这里有个 corner case 需要注意一下，假如数组中全是负数，那么和最小的子数组就是原数组本身，则求出的差值是0，而第一种情况求出的和最大的子数组也应该是负数，
// 那么二者一比较，返回0就不对了，所以这种特殊情况需要单独处理一下
public class CircularMaxSubArraySum {
    public int maxSubarraySumCircular(int[] A) {
        int min = Integer.MAX_VALUE, minSum = 0, max = Integer.MIN_VALUE, maxSum = 0;
        int sum = 0;

        for(int i = 0; i < A.length; i++) {
            minSum = Math.min(A[i], minSum + A[i]);
            maxSum = Math.max(A[i], maxSum + A[i]);
            min = Math.min(min, minSum);
            max = Math.max(max, maxSum);
            sum += A[i];
        }

        return (sum - min == 0) ? max : Math.max(max, sum - min);
    }
}
