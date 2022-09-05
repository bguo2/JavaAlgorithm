package com.example.console;

import java.util.Arrays;
import java.util.List;

//N integers in an array, value is from 0-M where M <= n, sort it with O(n)
//counting sort
public class CountSorting {
    public static int[] sort(int[] input) {
        if(input == null || input.length < 2)
            return input;
        int[] result = new int[input.length];
        int[] count = new int[input.length];
        //[0,1,1,1,2] 1,3,1,0,0
        for(int i = 0; i < input.length; i++) {
            count[input[i]]++;
        }

        //summary it: leave space for duplicates
        //1,4,5,5,5
        for(int i = 1; i < input.length; i++) {
            count[i] += count[i-1];
        }

        //0,1,1,1,2
        for(int i = input.length - 1; i >=0; i--) {
            result[count[input[i]] - 1] = input[i];
            count[input[i]]--;
        }

        return result;
    }

    //HackerLand National Bank has a simple policy for warning clients about possible fraudulent account activity.
    // If the amount spent by a client on a particular day is greater than or equal to  the client's median spending for a
    // trailing number of days, they send the client a notification about potential fraud. The bank doesn't send the client any
    // notifications until they have at least that trailing number of prior days' transaction data.
    //
    //Given the number of trailing days  and a client's total daily expenditures for a period of  days, determine the number of
    // times the client will receive a notification over all  days.
    //
    //Example
    //
    //
    //On the first three days, they just collect spending data. At day , trailing expenditures are . The median is  and the
    // day's expenditure is . Because , there will be a notice. The next day, trailing expenditures are  and the expenditures are .
    // This is less than  so no notice will be sent. Over the period, there was one notice sent.
    //
    //Note: The median of a list of numbers can be found by first sorting the numbers ascending. If there is an odd number of values,
    // the middle one is picked. If there is an even number of values, the median is then defined to be the average of the two middle
    // values. (Wikipedia)
    //
    //Function Description
    //
    //Complete the function activityNotifications in the editor below.
    //
    //activityNotifications has the following parameter(s):
    //
    //int expenditure[n]: daily expenditures
    //int d: the lookback days for median spending
    //Returns
    //
    //int: the number of notices sent
    //Input Format
    //
    //The first line contains two space-separated integers  and , the number of days of transaction data, and the number of
    // trailing days' data used to calculate median spending respectively.
    //The second line contains  space-separated non-negative integers where each integer  denotes .
    //
    //Constraints
    //
    //Output Format
    //
    //Sample Input 0
    //
    //STDIN               Function
    //-----               --------
    //9 5                 expenditure[] size n =9, d = 5
    //2 3 4 2 3 6 8 4 5   expenditure = [2, 3, 4, 2, 3, 6, 8, 4, 5]
    public static int activityNotifications(int[] arr, int d)
    {
        int max = Arrays.stream(arr).max().getAsInt();
        int size = arr.length;
        if(max > size) {
            size = max + 1;
        }

        int[] count = new int[size]; // holds count of each element

        // fill the count array with first d elements
        for(int i = 0; i < d; i++)
        {
            count[arr[i]] += 1;
        }

        int ans = 0;
        for(int i = d; i < arr.length; i++)
        {
            double median = getMedian0(count, d);
            //Console.WriteLine("median: " + median);
            if(arr[i] >= 2*median)
            {
                ans++;
            }

            // add next remove last
            count[arr[i]] += 1;
            count[arr[i-d]] -= 1;
        }

        return ans;
    }

    public static double getMedian(int[] arr, int d) {
        int max = Arrays.stream(arr).max().getAsInt();
        int size = arr.length;
        if(max > size) {
            size = max + 1;
        }

        int[] count = new int[size]; // holds count of each element

        // fill the count array with first d elements
        for(int i = 0; i < d; i++)
        {
            count[arr[i]] += 1;
        }
        return getMedian0(count, d);
    }

    private static double getMedian0(int[] count, int d)
    {
        // count array has frequencies
        int medianFreq = 0;
        int freqSum = 0;

        //since i == a[i], freqSum is the freqSum th number
        if (d % 2 == 1) { // odd
            medianFreq = d/2 +1;
            for(int i = 0; i< count.length; i++)            {
                freqSum += count[i];
                if (freqSum >= medianFreq)
                    return (double)i;
            }
        }
        else // even
        {
            medianFreq = d/2;
            for (int i = 0; i< count.length; i++)
            {
                freqSum += count[i];

                if (freqSum >= medianFreq)
                {
                    int first = i;
                    int second = freqSum > medianFreq ? i : i+1;
                    return ((double)(first + second))/2.0;
                }
            }
        }

        return 0.0;
    }
}
