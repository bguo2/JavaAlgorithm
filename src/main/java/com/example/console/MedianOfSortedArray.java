package com.example.console;

//There are two sorted arrays nums1 and nums2 of size m and n respectively.
//
//Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
//
//You may assume nums1 and nums2 cannot be both empty.
//
//Example 1:
//
//nums1 = [1, 3]
//nums2 = [2]
//
//The median is 2.0
//Example 2:
//
//nums1 = [1, 2]
//nums2 = [3, 4]
//
//The median is (2 + 3)/2 = 2.5
public class MedianOfSortedArray {

    public double medianOfSortedArray(int[] num1, int[] num2) {
        int m = num1.length;
        int n = num2.length;
        int left = (m+n+1)/2, right = (m+n+2)/2;
        return (findKth(num1, 0, num2, 0,  left ) + findKth(num1, 0, num2, 0, right)) / 2.0;
    }

    private int findKth(int[] num1, int i, int[] num2, int j, int k) {
        int m = num1.length;
        int n = num2.length;
        if(i >= m)
            return num2[j + k - 1];
        if(j >= n)
            return num1[i + k - 1];
        if(k == 1)
            return Math.min(num1[i], num2[j]);
        int midVal1 = (i + k/2 - 1 < m) ? num1[i + k/2 -1] : Integer.MAX_VALUE;
        int midVal2 = (j + k/2 - 1 < n) ? num2[j + k/2 -1] : Integer.MAX_VALUE;
        if(midVal1 < midVal2)
            return findKth(num1, i+k/2, num2, j, k-k/2);
        return findKth(num1, i, num2, j+k/2, k-k/2);
    }
}
