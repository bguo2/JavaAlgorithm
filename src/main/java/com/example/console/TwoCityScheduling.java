package com.example.console;

//029. Two City Scheduling
//A company is planning to interview 2n people. Given the array costs where costs[i] = [aCosti, bCosti], the cost of flying
// the ith person to city a is aCosti, and the cost of flying the ith person to city b is bCosti.
//
//Return the minimum cost to fly every person to a city such that exactly n people arrive in each city.
//
//
//
//Example 1:
//
//Input: costs = [[10,20],[30,200],[400,50],[30,20]]
//Output: 110
//Explanation:
//The first person goes to city A for a cost of 10.
//The second person goes to city A for a cost of 30.
//The third person goes to city B for a cost of 50.
//The fourth person goes to city B for a cost of 20.
//
//The total minimum cost is 10 + 30 + 50 + 20 = 110 to have half the people interviewing in each city.
//Example 2:
//
//Input: costs = [[259,770],[448,54],[926,667],[184,139],[840,118],[577,469]]
//Output: 1859
//Example 3:
//
//Input: costs = [[515,563],[451,713],[537,709],[343,819],[855,779],[457,60],[650,359],[631,42]]
//Output: 3086
//
//
//Constraints:
//
//2 * n == costs.length
//2 <= costs.length <= 100
//costs.length is even.
//1 <= aCosti, bCosti <= 1000

import java.util.Arrays;

//这道题说是一个公司要面试 2n 个人，每个人飞到城市A和城市B的花费不同，现在分别让n个人去城市A和城市B面试，问最小的花费是多少。博主二话不说，
// 上来就写个递归的暴力搜索，遍历所有的情况，但是很不幸的超时了，这道题需要更优化的解法。其实这道题可以用贪婪算法来做，首先假设我们让所有的人都去城市A，
// 那么总花费就是把所有人去城市A的花费加起来，但现在需要让其中的一半人去城市B，由于花费不同了，怎么算呢？若去城市B的花费小于城市A的，则应该 refund
// 二者的差值，若去城市A的花费小于城市B的，则应该加上二者的差值。所以用去城市B的花费减去城市A的，若为负数，则是 refund，若为正数，则是追加的花费。
// 当然是希望是负数，而且越小越好，这样就可以 refund，使得整个花费变小。所以开始时遍历一遍 costs 数组，将去城市A的花费先累加到结果 res 中，
// 然后将去城市B的花费减去城市A的花费的差值存入 refund 数组，之后给 refund 数组排序，取出前n个值加到结果 res 中即可
public class TwoCityScheduling {
    public int twoCitySchedCost(int[][] costs) {
        int res = 0;
        int[] diff = new int[costs.length];
        int index = 0;
        for(int[] cost: costs) {
            res += cost[0];
            diff[index++] = cost[1] - cost[0];
        }

        Arrays.sort(diff);
        for(int i = 0; i < costs.length/2; i++) {
            res += diff[i];
        }

        return res;
    }
}
