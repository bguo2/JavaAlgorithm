package com.example.console;

public class LexicSmallestArray {
    /*
    Given an array arr[], find the lexicographically smallest array that can be obtained after performing at maximum
    of k consecutive swaps.
Examples :


Input: arr[] = {7, 6, 9, 2, 1}
        k = 3
Output: arr[] = {2, 7, 6, 9, 1}
Explanation: Array is: 7, 6, 9, 2, 1
Swap 1:   7, 6, 2, 9, 1
Swap 2:   7, 2, 6, 9, 1
Swap 3:   2, 7, 6, 9, 1
So Our final array after k = 3 swaps :
2, 7, 6, 9, 1

Input: arr[] = {7, 6, 9, 2, 1}
        k = 1
Output: arr[] = {6, 7, 9, 2, 1}

We first pick the smallest element from array a1, a2, a3…(ak or an) [We consider ak when k is smaller, else n].
We place the smallest element to the a0 position after shifting all these elements by 1 position right.
We subtract number of swaps (number of swaps is number of shifts minus 1) from k. If still we are left with k > 0
then we apply the same procedure from the very next starting position i.e., a2, a3,…(ak or an) and then place it to the
a1 position. So we keep applying the same process until k becomes 0.
     */

    public static void minimizeWithKSwaps(int arr[], int k) {
        for(int i = 0; i < arr.length - 1; i++) {
            int pos = i;
            for(int j = i+1; j < arr.length; j++) {
                if(j-i > k) {
                    break;
                }
                // Find the minimum value from i+1 to max k or n
                if(arr[j] < arr[pos])
                    pos = j;
            }

            int tmp;
            for(int j = pos; j > i; j--) {
                tmp = arr[j];
                arr[j] = arr[j-1];
                arr[j-1] = tmp;
            }

            k -= pos - i;
            if(k < 1)
                break;
        }
    }
}
