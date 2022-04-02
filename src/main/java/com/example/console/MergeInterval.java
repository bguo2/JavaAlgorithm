package com.example.console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return
// an array of the non-overlapping intervals that cover all the intervals in the input.
//
//
//
//Example 1:
//
//Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
//Output: [[1,6],[8,10],[15,18]]
//Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
//Example 2:
//
//Input: intervals = [[1,4],[4,5]]
//Output: [[1,5]]
//Explanation: Intervals [1,4] and [4,5] are considered overlapping.
//
//
//Constraints:
//
//1 <= intervals.length <= 104
//intervals[i].length == 2
//0 <= starti <= endi <= 104
public class MergeInterval {
    public int[][] merge(int[][] intervals) {
        //Arrays.sort(intervals, (e1, e2) -> e1[0] - e2[0]);
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] e1, int[] e2) {
                return e1[0] - e2[0];
            }
        });

        List<int[]> result = new ArrayList<>();
        int[] t = intervals[0];
        for(int i = 1; i < intervals.length; i++) {
            if(intervals[i][0] <= t[1]) {
                t[1] = Math.max(t[1], intervals[i][1]);
            }
            else {
                result.add(t);
                t = intervals[i];
            }
        }
        result.add(t);

        return result.toArray(new int[result.size()][]);
    }
}
