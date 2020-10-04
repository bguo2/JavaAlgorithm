package com.example.console;





import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

//Find k pairs with smallest sums in two sorted arrays
//Given two integer arrays arr1[] and arr2[] sorted in ascending order and an integer k. Find k pairs with smallest sums
// such that one element of a pair belongs to arr1[] and other element belongs to arr2[]
//Examples:
//Input :  arr1[] = {1, 7, 11}
//         arr2[] = {2, 4, 6}
//         k = 3
//Output : [1, 2], [1, 4], [1, 6]
//Explanation: The first 3 pairs are returned
//from the sequence [1, 2], [1, 4], [1, 6], [7, 2], [7, 4], [11, 2], [7, 6], [11, 4],[11, 6]
public class KSmallestPairs {
    public static List<Pair<Integer, Integer>> getSmallestPairs(int[] arr1, int[] arr2, int k){
        List<Pair<Integer, Integer>> result = new ArrayList<Pair<Integer, Integer>>();
        if(arr1 == null || arr2 == null)
            return result;
        int n1 = arr1.length;
        int n2 = arr2.length;
        if(k > n1*n2)
            return result;
        int[] index2 = new int[n1];
        while (k > 0){
            int min_sum = Integer.MAX_VALUE;
            int min_index = 0;
            // To pick next pair, traverse for all elements of arr1[], for every element, find
            // corresponding current element in arr2[] and pick minimum of all formed pairs.
            for (int i1 = 0; i1 < n1; i1++)
            {
                // Check if current element of arr1[] plus element of array2 to be used gives minimum sum
                if (index2[i1] < n2 && arr1[i1] + arr2[index2[i1]] < min_sum)
                {
                    // Update index that gives minimum
                    min_index = i1;

                    // update minimum sum
                    min_sum = arr1[i1] + arr2[index2[i1]];
                }
            }

            result.add(Pair.of(arr1[min_index], arr2[index2[min_index]]));
            index2[min_index]++;
            k--;
        }

        return result;
    }
}
