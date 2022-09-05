package com.example.console.recursion;
//1335. Minimum Difficulty of a Job Schedule
//Hard
//
//558
//
//61
//
//Add to List
//
//Share
//You want to schedule a list of jobs in d days. Jobs are dependent (i.e To work on the i-th job, you have to finish all the jobs j
// where 0 <= j < i).
//
//You have to finish at least one task every day. The difficulty of a job schedule is the sum of difficulties
// of each day of the d days.
// The difficulty of a day is the maximum difficulty of a job done in that day.
//
//Given an array of integers jobDifficulty and an integer d. The difficulty of the i-th job is jobDifficulty[i].
//
//Return the minimum difficulty of a job schedule. If you cannot find a schedule for the jobs return -1.
//
//
//
//Example 1:
//
//
//Input: jobDifficulty = [6,5,4,3,2,1], d = 2
//Output: 7
//Explanation: First day you can finish the first 5 jobs, total difficulty = 6.
//Second day you can finish the last job, total difficulty = 1.
//The difficulty of the schedule = 6 + 1 = 7
//Example 2:
//
//Input: jobDifficulty = [9,9,9], d = 4
//Output: -1
//Explanation: If you finish a job per day you will still have a free day. you cannot find a schedule for the given jobs.
//Example 3:
//
//Input: jobDifficulty = [1,1,1], d = 3
//Output: 3
//Explanation: The schedule is one job per day. total difficulty will be 3.
//Example 4:
//
//Input: jobDifficulty = [7,1,7,1,7,1], d = 3
//Output: 15
//Example 5:
//
//Input: jobDifficulty = [11,111,22,222,33,333,44,444], d = 6
//Output: 843
//
//
//Constraints:
//
//1 <= jobDifficulty.length <= 300
//0 <= jobDifficulty[i] <= 1000
//1 <= d <= 10
public class MinDifficultJobSchedule {
    public int minDifficulty(int[] jobDifficulty, int d) {
        if(d > jobDifficulty.length)
            return -1;
        Integer[][] cache = new Integer[d][jobDifficulty.length];
        return dfs(jobDifficulty, d-1, 0, cache);
    }

    private int dfs(int[] k, int d, int pos, Integer[][] cache) {
        //base case
        if(d == 0) {
            int max = k[pos];
            for(int i = pos; i < k.length; i++)
                max = Math.max(max, k[i]);
            return max;
        }

        //just making sure we start indexing from 0, sorry about the confusion
        int day = cache.length-d;

        //we already have this in the cache, just return it right here
        if(cache[day][pos] != null)
            return cache[day][pos];

        // same logic as naive recursion
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i = pos; i < k.length-d; i++) {
            max = Math.max(max, k[i]);
            min = Math.min(min, max + dfs(k, d-1, i+1, cache));
        }

        // but now we're also updating the cache the first time we calculate this
        return cache[day][pos] = min;
    }

    //How would we go about writing this recursive function? We'd have to go day by day, trying each combination.
    //
    //So the base case would be: if there's only one day left, just return the max value of the remaining array.
    // Simple enough. If there's more, then sequentially increase the size of the jobs for that day and recursively call the function
    // for the subsequent days. Looking at the code for this:
    public int minDifficulty1(int[] k, int d) {
        if(d > k.length)
            return -1;
        return dfs1(k, d-1, 0);
    }

    private int dfs1(int[] k, int d, int pos) {
        //base case
        if(d == 0) {
            int max1 = k[pos];
            for(int i = pos; i < k.length; i++)
                max1 = Math.max(max1, k[i]);
            return max1;
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        //try out all ranges for that day
        for(int i = pos; i < k.length-d; i++) {
            max = Math.max(max, k[i]);
            min = Math.min(min, max + dfs1(k, d-1, i+1));
        }
        return min;
    }
}
