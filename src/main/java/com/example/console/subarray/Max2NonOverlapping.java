package com.example.console.subarray;

//Maximum Sum of Two Non-Overlapping Subarrays
//Given an array A of non-negative integers, return the maximum sum of elements in two non-overlapping (contiguous) subarrays,
// which have lengths L and M.  (For clarification, the L-length subarray could occur before or after the M-length subarray.)
//
//Formally, return the largest V for which V = (A[i] + A[i+1] + ... + A[i+L-1]) + (A[j] + A[j+1] + ... + A[j+M-1]) and either:
//
//0 <= i < i + L - 1 < j < j + M - 1 < A.length, or
//0 <= j < j + M - 1 < i < i + L - 1 < A.length.
//
//
//Example 1:
//
//Input: A = [0,6,5,2,2,5,1,9,4], L = 1, M = 2
//Output: 20
//Explanation: One choice of subarrays is [9] with length 1, and [6,5] with length 2.
//Example 2:
//
//Input: A = [3,8,1,3,2,1,8,9,0], L = 3, M = 2
//Output: 29
//Explanation: One choice of subarrays is [3,8,1] with length 3, and [8,9] with length 2.
//Example 3:
//
//Input: A = [2,1,5,6,0,9,5,0,3,8], L = 4, M = 3
//Output: 31
//Explanation: One choice of subarrays is [5,6,0,9] with length 4, and [0,3,8] with length 3.
//
//
//Note:
//
//L >= 1
//M >= 1
//L + M <= A.length <= 1000
//0 <= A[i] <= 1000
//这道题给了一个非负数组A，还有两个长度L和M，说是要分别找出不重叠且长度分别为L和M的两个子数组，前后顺序无所谓，问两个子数组最大的数字之和是多少。
// 博主最开始想的方法是用动态规划 Dynamic Programming 和滑动窗口 Sliding Window 来做，用两个 dp 数组，其中 front[i] 表示范围 [0, i]
// 之间的长度为M的子数组的最大数字之和，back[i] 表示范围 [i, n-1] 之间的长度为M的子数组的最大数字之和。然后再次遍历数组，维护一个长度为L的滑动数组，
// 当数组长度正好为L的时候，当前窗口的数字之和加上左边的 front[left-1]，或者加上右边的 back[i+1]，取二者中的较大值来更新结果 res，这种解法可以通过 OJ，
// 但是行数比较多，且用了三个 for 循环，这里就不贴了。来看论坛上的高分解法吧，首先建立累加和数组，这里可以直接覆盖A数组，然后定义 Lmax 为在最后M个数字之前的
// 长度为L的子数组的最大数字之和，同理，Mmax 表示在最后L个数字之前的长度为M的子数组的最大数字之和。结果 res 初始化为前 L+M 个数字之和，然后遍历数组，
// 从 L+M 开始遍历，先更新 Lmax 和 Mmax，其中 Lmax 用 A[i - M] - A[i - M - L] 来更新，Mmax 用 A[i - L] - A[i - M - L] 来更新。然后取
// Lmax + A[i] - A[i - M] 和 Mmax + A[i] - A[i - L] 之间的较大值来更新结果 res 即可

//get the max L and max M, the check the index i: max(L+sum(M), M+sum(L))
public class Max2NonOverlapping {
    public static int maxSumTwoNoOverlap(int[] A, int L, int M) {
        if(A == null || A.length == 0)
            return Integer.MIN_VALUE;

        int n = A.length;
        int[] maxLeftL = new int[n];
        int[] maxLeftM = new int[n];
        int[] sum = new int[n];
        sum[0] = A[0];
        for(int i = 1; i < n; i++) {
            sum[i] = sum[i-1] + A[i];
        }

        maxLeftL[L-1] = sum[L-1];
        for(int i = L; i < n; i++) {
            int curSum = sum[i] - sum[i-L];
            maxLeftL[i] = Math.max(maxLeftL[i-1], curSum);
        }

        maxLeftM[M-1] = sum[M-1];
        for (int i = M; i < n; i++) {
            int curSum = sum[i] - sum[i-M];
            maxLeftM[i] = Math.max(maxLeftM[i-1], curSum);
        }

        int res = 0;
        for(int i = (L + M) - 1; i < n ; i ++){
            //i-M = L-1, i-L=M-1 when starting from L+M-1
            int maxLM = maxLeftL[i - M] + sum[i] - sum[i - M];
            int maxML = maxLeftM[i - L] + sum[i] - sum[i - L];
            res = Math.max(res, Math.max(maxLM, maxML));
        }

        return res;
    }
}
