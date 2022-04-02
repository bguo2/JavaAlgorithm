package com.example.console;
//H-index: sort the citations with descending order, if c <= index, then the index is the H-index
//Given an array of citations sorted in ascending order (each citation is a non-negative integer) of a researcher,
// write a function to compute the researcher's h-index.
//
//According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each,
// and the other N − h papers have no more than h citations each."
//
//Example:
//
//Input: citations = [0,1,3,5,6]
//Output: 3
//Explanation: [0,1,3,5,6] means the researcher has 5 papers in total and each of them had
//             received 0, 1, 3, 5, 6 citations respectively.
//             Since the researcher has 3 papers with at least 3 citations each and the remaining
//             two with no more than 3 citations each, her h-index is 3.
//Note:
//
//If there are several possible values for h, the maximum one is taken as the h-index.
public class Hindex {
    //linear algorithm: left hand citations < n-idx while right hand citations >= n-idx
    public static int hIndex(int[] citations) {
        int idx = 0;
        for(int c: citations) {
            if(c >= citations.length - idx)
                return citations.length - idx;
            else
                idx++;
        }
        return 0;
    }

    //binary search
    public static int hIndexII(int[] citations) {
        int i = 0, j = citations.length - 1;
        int n = citations.length;
        while(i <= j) {
            int pivot = i + (j - i) / 2;
            if(citations[pivot] == n - pivot)
                return n - pivot;
            else if(citations[pivot] < n - pivot)
                i = pivot + 1;
            else
                j = pivot - 1;
        }

        return n-i;
    }
}
