package com.example.console;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.test.util.AssertionErrors.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest5 {

    @Test
    public void sumOfDistanceInTreeTest() {
        boolean win = canWin("++++".toCharArray());

        SumOfDistanceInTree tree = new SumOfDistanceInTree();
        int[][] edges = new int[][] {{0,1},{0,2},{2,3},{2,4},{2,5}};
        int[] result = tree.getSumOfDistancesInTree(6, edges);
    }

    private boolean canWin(char[] array) {
        for(int i = 0; i < array.length - 1; i++) {
            if(array[i] == '+' && array[i+1] == '+') {
                array[i] = '-';
                array[i+1] = '-';
                boolean win = canWin(array);
                array[i] = '+';
                array[i+1] = '+';
                if(!win) {
                    return true;
                }
            }
        }

        return false;
    }

    @Test

    public void findIndexTest() {
        int[] test = new int[] { 1, 2, 2, 2,5};
        int result = findIndex(test, 2);
        assertTrue("", result == 0);
    }

    public int findIndex(int[] nums, int target) {
        if(nums == null || nums.length == 0)
            return -1;
        int i = 0;
        int j = nums.length;

        while (i < j) {
            int mid = (i+j) / 2;
            if(nums[mid] < target)
                i = mid + 1;
            else
                j = mid;
        }

        return i;
    }
}
