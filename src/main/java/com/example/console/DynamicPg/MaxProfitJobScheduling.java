package com.example.console.DynamicPg;
//1235. Maximum Profit in Job Scheduling
//Hard
//
//1080
//
//11
//
//Add to List
//
//Share
//We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].
//
//You're given the startTime, endTime and profit arrays, return the maximum profit you can take such that there are no
// two jobs in the subset with overlapping time range.
//
//If you choose a job that ends at time X you will be able to start another job that starts at time X.
//
//
//
//Example 1:
//
//
//
//Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
//Output: 120
//Explanation: The subset chosen is the first and fourth job.
//Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
//Example 2:
//
//
//
//Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
//Output: 150
//Explanation: The subset chosen is the first, fourth and fifth job.
//Profit obtained 150 = 20 + 70 + 60.
//Example 3:
//
//
//
//Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
//Output: 6
//
//
//Constraints:
//
//1 <= startTime.length == endTime.length == profit.length <= 5 * 104
//1 <= startTime[i] < endTime[i] <= 109
//1 <= profit[i] <= 104

import java.util.Arrays;
import java.util.Comparator;

//
public class MaxProfitJobScheduling {
    class Point {
        int s;
        int e;
        int p;
        Point(int s, int e, int p)
        {
            this.s=s;
            this.p=p;
            this.e=e;
        }
    }

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n=startTime.length;
        Point[] arr=new Point[startTime.length];

        for(int i=0; i<n;i++)
        {
            arr[i] = new Point(startTime[i],endTime[i],profit[i]);
        }

        //sort by end time asc; greedy with more jobs (possible to get more profit)
        Arrays.sort(arr, (a, b) -> a.e == b.e ? a.s - b.s : a.e - b.e);
        int[] dp = new int[n];
        dp[0] = arr[0].p;

        for(int i = 1; i < n; i++) {
            int index = binarySearch(arr, i);
            int profit1 = index == -1 ? 0 : dp[index];
            dp[i] = Math.max(dp[i-1], arr[i].p + profit1);
        }

        return dp[n-1];
    }

    //find the last one whose end time <= cur start time
    private int binarySearch(Point[] arr, int curIndex) {
        int end = curIndex - 1;
        int start = 0;
        while(start <= end) {
            int mid = (start+end)/2;
            if(arr[mid].e <= arr[curIndex].s) {
                if(arr[mid+1].e <= arr[curIndex].s)
                    start = mid + 1;
                else
                    return mid;
            }
            else {
                end = mid - 1;
            }
        }
        return -1;
    }
}
