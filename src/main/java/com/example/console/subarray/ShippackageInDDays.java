package com.example.console.subarray;

//[LeetCode] 1011. Capacity To Ship Packages Within D Days 在D天内送达包裹的能力
//
//A conveyor belt has packages that must be shipped from one port to another within D days.
//
//The ith package on the conveyor belt has a weight of weights[i]. Each day, we load the ship with packages
// on the conveyor belt (in the order given by weights). We may not load more weight than the maximum weight capacity of the ship.
//
//Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within D days.
//
//Example 1:
//
//Input: weights = [1,2,3,4,5,6,7,8,9,10], D = 5
//Output: 15
//Explanation: A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
//1st day: 1, 2, 3, 4, 5
//2nd day: 6, 7
//3rd day: 8
//4th day: 9
//5th day: 10
//
//Note that the cargo must be shipped in the order given, so using a ship of capacity 14 and splitting the packages into parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed.
//Example 2:
//
//Input: weights = [3,2,2,4,1,4], D = 3
//Output: 6
//Explanation: A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
//1st day: 3, 2
//2nd day: 2, 4
//3rd day: 1, 4
//Example 3:
//
//Input: weights = [1,2,3,1,1], D = 4
//Output: 3
//Explanation:
//1st day: 1
//2nd day: 2
//3rd day: 3
//4th day: 1, 1
//Constraints:
//
//1 <= D <= weights.length <= 5 * 104
//1 <= weights[i] <= 500

//来分析一下，船的载重量的范围，先来分析一下最小值，由于所有的包裹都要上船，所以最小的船载重量至少应该是最重的那个包裹，不然上不了船了，
// 而最大的载重量就是包裹的总重量，一条船就能拉走了。所以正确的答案就在这两个边界范围之内，挨个遍历的话实在有些太不高效了，这里就要祭出二分搜索法了，
// 当算出了中间值 mid 后，利用这个载重量去算需要多少天能运完，然后去和D做比较，如果大于D，说明需要增加载重量，否则减少载重量，最终会终止到正确的结果。
// 具体来看代码，left 初始化为最大的包裹重量，right 初始化为所有的包裹重量总和。然后进行 while 循环，求出 mid，同时使用两个变量 cnt 和 cur，
// 分别用来计算需要的天数，和当前货物的重量，其中 cnt 初始化为1，至少需要一天来运货物。然后遍历所有的包裹重量，每次加到 cur，若此时 cur 大于 mid 了，
// 说明当前包裹不能加了，将 cur 重置为当前包裹重量，为下条船做准备，然后 cnt 自增1。遍历完了之后，判断若 cnt 大于D，则 left 赋值为 mid+1，
// 否则 right 赋值为 mid，这是博主经常用的一种二分搜索的写法，可以参见博主之前的总结帖 LeetCode Binary Search Summary 二分搜索法小结，
// 最终返回 left 即可
public class ShippackageInDDays {
    public static int shipWithinDays(int[] weights, int D) {
        int left = Integer.MIN_VALUE;
        int right = 0;
        for(int weight: weights) {
            left = Math.max(left, weight);
            right +=  weight;
        }

        while(left < right) {
            int mid = left + (right - left)/2;
            int days = 1, res = 0;
            for(int weight: weights) {
                res += weight;
                if(res > mid) {
                    res = weight;
                    days++;
                }
            }

            if(days > D)
                left = mid + 1;
            else
                right = mid;
        }

        return left;
    }
}
