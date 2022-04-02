package com.example.console;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

//Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
// find the minimum number of conference rooms required.
//
//Example 1:
//
//Input: [[0, 30],[5, 10],[15, 20]]
//Output: 2
//Example 2:
//
//Input: [[7,10],[2,4]]
//Output: 1
public class MeetingRoomII {

    public int minMeetingRooms(int[][] intervals) {
        if(intervals == null || intervals.length == 0)
            return 0;
        int count = 0;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] v1, int[] v2) {
                if(v1[0] == v2[0])
                    return v1[1] - v2[1];
                return v1[0] - v2[0];
            }
        });

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int i = 0; i < intervals.length; i++) {
            if(queue.isEmpty()) {
                count++;
                queue.add(intervals[i][1]);
            }
            else {
                if(intervals[i][0] < queue.peek()) {
                    count++;
                }
                else {
                    queue.poll();
                }
                queue.add(intervals[i][1]);
            }
        }

        return count;
    }
}
