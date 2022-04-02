package com.example.console;

import com.example.console.DynamicPg.CoinChange;
import com.example.console.DynamicPg.MaxProfitJobScheduling;
import com.example.console.cache.LeastFrenquentUsed;
import com.example.console.subarray.KmoduleSubSet;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

public class AppTest11 {

    @Test
    public void kModuleSubArrayTest() {
        long count = KmoduleSubSet.countSubset(5, new int[] { 10, 5});
        assertTrue(count == 3);
        count = KmoduleSubSet.countSubset(5, new int[] { 10 });
        assertTrue(count == 1);
        //{10}, {5}, {5}, {10, 5}, {10, 5}, {5,5,}, {10,5,5}
        count = KmoduleSubSet.countSubset(5, new int[] { 10, 5, 5 });
        assertTrue(count == 7);
        //{10}, {5}, {5}, {10, 5}, {10, 5}, {5,5,}, {10,5,5}, | {11, 9}
        count = KmoduleSubSet.countSubset(5, new int[] { 10, 5, 11, 9, 5 });
        assertTrue(count == 15);
    }

    @Test
    public void kModuleSubArrayWithMemTest() {
        long count = KmoduleSubSet.countSubsetWithMem(5, new int[] { 10000000, 5000});
        assertTrue(count == 3);
        count = KmoduleSubSet.countSubsetWithMem(5, new int[] { 10 });
        assertTrue(count == 1);
        //{10}, {5}, {5}, {10, 5}, {10, 5}, {5,5,}, {10,5,5}
        count = KmoduleSubSet.countSubsetWithMem(5, new int[] { 10, 5, 5 });
        assertTrue(count == 7);
        //{10}, {5}, {5}, {10, 5}, {10, 5}, {5,5,}, {10,5,5}, | {11, 9}
        count = KmoduleSubSet.countSubsetWithMem(5, new int[] { 10, 5, 11, 9, 5 });
        assertTrue(count == 15);
    }

    @Test
    public void sortListWithFrequencyTest() {
        List<Integer> result = SortListWithFrequency.sortListWithFrequency(
                new ArrayList<>(Arrays.asList(2, 5, 2, 8, 5, 6, 8, 8, 9, 1)));
    }

    @Test
    public void leastFrenquentUsedTest() {
        LeastFrenquentUsed<String, String> cache = new LeastFrenquentUsed<>(2);
        cache.set("1", "111");
        cache.set("2", "222");
        String value = cache.get("2");
        cache.set("3", "333");
    }

    @Test
    public void coinChangeTest() {
        long count = CoinChange.countWaysOfCoinChange(new Long[] {1L,2L,3L}, 4);
        assertTrue(count == 4);
    }

    @Test
    public void maxProfitTest() {
        MaxProfitJobScheduling test = new MaxProfitJobScheduling();
        int max = test.jobScheduling(new int[] {1,2,3,4,6}, new int[] {3,5,10,6,9}, new int[] {20,20,100,70,60});
    }

    @Test
    public void twoSumTest() {
        List<int[]> result = TwoSum.getIndexOfPairs(new int[] { 2, 7, 6, 3 }, 9);
        assertTrue(result.size() == 2);

        int count = TwoSum.countOfDistinctPairs(new int[] {1,1,2, 2, 3, 3,4,5, 5,6 }, 2);
        assertTrue(count == 4);
       count = TwoSum.countOfDistinctPairs(new int[] {1,1, 2,2, 3,4,4,5,6 }, 0);
        assertTrue(count == 3);
        count = TwoSum.countOfDistinctPairs(new int[] {1,2, 3,4,5,6 }, 0);
        assertTrue(count == 0);
        count = TwoSum.countOfDistinctPairs(new int[] {1, 2, 3,4,5,6 }, 4);
        assertTrue(count == 2);
        count = TwoSum.countOfDistinctPairs(new int[] {1,1, 2,2, 3,3,4,5,5,6,6 }, -2);
        assertTrue(count == 4);
        count = TwoSum.countOfDistinctPairs(new int[] {1, 1,2,2,3,3 }, 0);
        assertTrue(count == 3);
        count = TwoSum.countOfDistinctPairs(new int[] {1, 1,2,2,3,3 }, 1);
        assertTrue(count == 2);
    }
}
