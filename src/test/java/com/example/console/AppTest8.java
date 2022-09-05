package com.example.console;

import com.example.console.DynamicPg.PalidromSubStrings;
import com.example.console.DynamicPg.Triplets;
import com.example.console.binarytree.BinaryTreePathSum;
import com.example.console.math.NthRoot;
import com.example.console.matrix.Matrix;
import com.example.console.subarray.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest8 {
    @Test
    public void matrixTest() {
        Matrix matrix = new Matrix();
        int[][] m = new int[][] {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10,11,12},
                {13,14,15,16}
        };
        List<Integer> result = matrix.printDiagonally(m);
        result = matrix.printDiagonallyFromTop(m);
        m = new int[][] {
                {1, 2, 3, 4, 5},
                {5, 6, 7, 8, 9}
        };
        result = matrix.printDiagonally(m);
        result = matrix.printDiagonallyFromTop(m);
        m = new int[][] {
                {1, 2},
                {3, 4},
                {5, 6},
                {7, 8}
        };
        result = matrix.printDiagonally(m);
        result = matrix.printDiagonallyFromTop(m);
    }

    @Test
    public void nthroottest() {
        double res = NthRoot.findNthRoot(8, 3);
    }

    @Test
    public void subArrayTest() {
        int[] nums = new int[] { 1,2,1,2,6,7,5,1 };
        //expect [0,3,5]
        int[] result = MaxSumof3Subarrays.maxSumOfThreeSubarrays(nums, 2);

        //descending
        nums = new int[] {9,8,7,6,2,2,2,2};
        //expect [0,2,4]
        result = MaxSumof3Subarrays.maxSumOfThreeSubarrays(nums, 2);

        //ascending
        nums = new int[] {2,2,9,10,0,8,9};
        //expect [0,2,5]
        result = MaxSumof3Subarrays.maxSumOfThreeSubarrays(nums, 2);
    }

    @Test
    public void minDistanceTest() {
        char[] input = new char[] {'a','d','c','b','e','a'};
        int result = MinDistanceBetween2Chars.getMinDistance(input, 'a', 'b');
        Assert.assertTrue(result == 2);
        input = new char[] {'a','d','c','b','e','b', 'a'};
        result = MinDistanceBetween2Chars.getMinDistance(input, 'a', 'b');
        Assert.assertTrue(result == 1);
    }

    @Test
    public void minDiffSubArrayTest() {
        int[] input = new int[] {7, 10, 1, 8, 10};
        int result = MinDiffBetween2Subarrays.minDiffSubArray(input);
        Assert.assertTrue(result == 0);
        input = new int[] {7, 10, 3, 5, 10};
        result = MinDiffBetween2Subarrays.minDiffSubArray(input);
        Assert.assertTrue(result == 1);
        input = new int[] {17, 1, 1, 3, 10};
        result = MinDiffBetween2Subarrays.minDiffSubArray(input);
        Assert.assertTrue(result == 2);
    }

    @Test
    public void minDiffAnySubSetTest() {
        int[] input = new int[] {1, 6, -11, 5};
        int result = MinDiffSubsetSum.getMinDiffSubset(input);
        Assert.assertTrue(result == 1);

        input = new int[] { 3, 1, 4, 2, 2, 8 };
        result = MinDiffSubsetSum.getMinDiffSubset(input);
        Assert.assertTrue(result == 0);

        input = new int[] { 31,26,33,21,40 };
        result = MinDiffSubsetSum.getMinDiffSubset(input);
        Assert.assertTrue(result == 5);
    }

    @Test
    public void canPartitionKSubsetsTest() {
        int[] nums = new int[] { 4, 3, 2, 3, 5, 2, 1 };
        boolean result = KEqualSubSets.canPartitionKSubsets(nums, 2);
        Assert.assertTrue(result);
    }

    @Test
    public void flowerPlanningTest() {
        FlowerPlantingWithNoAdjacent test = new FlowerPlantingWithNoAdjacent();
        int[][] input = new int[][] {
                {1,2},
                {2,3},
                {3,4},
                {4,1},
                {1,3},
                {2,4}
        };
        int[] result = test.gardenNoAdj(4, input);
    }

    @Test
    public void max2NonOverlappingArrayTest() {
        int[] input = new int[] { 0,6,5,2,2,5,1,9,4 };
        int res = Max2NonOverlapping.maxSumTwoNoOverlap(input, 1, 2);
        Assert.assertTrue(res == 20);

        input = new int[] { 3,8,1,3,2,1,8,9,0 };
        res = Max2NonOverlapping.maxSumTwoNoOverlap(input, 3, 2);
        Assert.assertTrue(res == 29);
    }

    @Test
    public void anagramTest() {
        int count = AnagramCountInString.sherlockAndAnagrams("xyyx");
        Assert.assertTrue(count == 4);

        List<String> result = AnagramCountInString.getAllAnagrams("abc");
        Assert.assertTrue(result.size() == 6);
    }

    @Test
    public void countTriplets() {
        List<Long> input = new ArrayList() {{
            add(1L);
            add(4L);
            add(16L);
            add(64L);
        }};
        long count = Triplets.countTriplets(input, 4);
        Assert.assertTrue(count == 2);

        input = new ArrayList() {{
            add(1L);
            add(3L);
            add(9L);
            add(9L);
            add(27L);
            add(81L);
        }};
        count = Triplets.countTriplets(input, 3);
        Assert.assertTrue(count == 6);
    }

    @Test
    public void palidromeTest() {
        int count = PalidromSubStrings.countSubstrings("aaa");
        Assert.assertTrue(count == 6);
        count = PalidromSubStrings.countSubstringsDp("aaa");
        Assert.assertTrue(count == 6);

        //2 char+
        count = PalidromSubStrings.countSubstringsDp("abba");
        Assert.assertTrue(count == 3);
        count = PalidromSubStrings.countSubstringsDp("abbaeae");
        Assert.assertTrue(count == 4);
    }

    @Test
    public void binaryTreePathSumTest() {
        BinaryTreePathSum tree = new BinaryTreePathSum();
        BinaryTreePathSum.TreeNode n1 = tree.new TreeNode(null, null, 3);
        BinaryTreePathSum.TreeNode n2 = tree.new TreeNode(null, null, -2);
        BinaryTreePathSum.TreeNode rn1n2 = tree.new TreeNode(n1, n2, 3);
        BinaryTreePathSum.TreeNode n4 = tree.new TreeNode(null, null, 1);
        BinaryTreePathSum.TreeNode n5 = tree.new TreeNode(null, n4, 2);
        BinaryTreePathSum.TreeNode n6 = tree.new TreeNode(rn1n2, n5, 5);
        BinaryTreePathSum.TreeNode r1 = tree.new TreeNode(null, null, 11);
        BinaryTreePathSum.TreeNode r2 = tree.new TreeNode(null, r1, -3);
        BinaryTreePathSum.TreeNode root = tree.new TreeNode(n6, r2, 10);

        int maxSum = tree.pathSum(root, 8);
        Assert.assertTrue(maxSum == 3);
    }

    @Test
    public void specialStringTest() {
        int count = SpecialString.countSpecialSubString("mnonopoo");
    }
}
