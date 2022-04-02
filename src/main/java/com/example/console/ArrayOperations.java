package com.example.console;

import java.util.HashMap;
import java.util.Map;

//Consider an array of size n with all initial values as 0, we need to perform following m range increment operations.
//
//increment(a, b, k) : Increment values from 'a'
//                     to 'b' by 'k'.
//After m operations, we need to calculate the maximum of the values in the array.
//Input : n = 5 m = 3
//        a = 0, b = 1, k = 100
//        a = 1, b = 4, k = 100
//        a = 2, b = 3, k = 100
//Output : 200
//Explanation:
//Initially array = {0, 0, 0, 0, 0}
//After first operation:
//array = {100, 100, 0, 0, 0}
//After second operation:
//array = {100, 200, 100, 100, 100}
//After third operation:
//array = {100, 200, 200, 200, 100}
//Maximum element after m operations
//is 200.

//add k to [a, b] is similar to [0, a) - k and [b+1, n] - k
//the max number is the max sum of all
//假设我们的数组范围是 [0, n)，需要更新的区间是 [start, end]，更新值是 inc，那么将区间 [start, end] 中每个数字加上 inc，等同于将区间 [start, n)
// 内的数字都加上 inc，然后将 [end+1, n) 区间内数字都减去 inc，明白了可以这样转换之后，我们还是不能每次都更新区间内所有的值，所以需要换一种标记方式，
// 做法就是在开头坐标 start 位置加上 inc，而在结束位置 end 加1的地方加上 -inc。就比如说需要将新区间 [1, 3] 内数字都加2，那么我们在1的位置加2，
// 在4的位置减2，于是数组就变成了 [0, 2, 0, 0, -2]。假如就只有这一个操作，如何得到最终的结果呢，答案是建立累加和数组，变成 [0, 2, 2, 2, 0]，
// 我们发现正好等同于直接将区间 [1, 3] 内的数字都加2。进一步分析，建立累加和数组的操作实际上是表示当前的数字对之后的所有位置上的数字都有影响，
// 那么我们在 start 位置上加了2，表示在 [start, n) 区间范围内每个数字都加了2，而实际上只有 [start, end] 区间内的数字才需要加2，为了消除这种影响，
// 我们需要将 [end+1, n) 区间内的数字都减去2，所以才在 end+1 位置上减去了2，那么建立累加和数组的时候就相当于后面所有的数字都减去了2。需要注意的是这里 end
// 可能等于 n-1，则 end+1 可能会越界，所以我们初始化数组的长度为 n+1，就可以避免越界了。那么根据题目中的例子，我们可以得到一个数组，
// nums = {-2, 2, 3, 2, -2, -3}，然后对其做累加和就是我们要求的结果 result = {-2, 0, 3, 5, 3}
public class ArrayOperations {
    public static long arrayManipulation(int n, int[][] queries) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int[] ops: queries) {
            int a = ops[0];
            int b = ops[1];
            int k = ops[2];
            map.put(a, map.getOrDefault(a, 0) + k);
            map.put(b+1, map.getOrDefault(b+1, 0) - k);
        }

        long max = Long.MIN_VALUE;
        long sum = 0;
        for(int i = 0; i < n; i++) {
            long num = map.getOrDefault(i, 0);
            sum += num;
            max = Math.max(max, sum);
        }

        return max;
    }
}
