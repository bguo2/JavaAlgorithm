package com.example.console;

import com.example.console.binarytree.BinaryTreeLinked;
import com.example.console.binarytree.BstRangeSum;
import com.example.console.binarytree.DuplicatedSubTree;
import com.example.console.binarytree.TreeNode;
import com.example.console.recursion.OddEvenJump;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class AppTest12 {
    @Test
    public void cipherTest() {
        String input = "All-convoYs-9-be:Alert1.";
        int rotationFactor_1 = 4;
        char[] arr = input.toCharArray();
        for(int i = 0; i < arr.length; i++) {
            if(Character.isDigit(arr[i])) {
                int r = (((arr[i] - '0') + rotationFactor_1) % 10) + '0';
                arr[i] = (char)(r);
            }
            else if(Character.isLetter(arr[i])) {
                int r;
                if(arr[i] >= 'A' && arr[i] <= 'Z') {
                    r = ((arr[i] - 'A' + rotationFactor_1) % 26) + 'A';
                }
                else {
                    r = ((arr[i] - 'a' + rotationFactor_1) % 26) + 'a';
                }
                arr[i] = (char)(r);
            }
        }
        String result = new String(arr);
    }

    @Test
    public void threeSmallestLargestTest() {
        int[] arr = new int[] { 1, 2, 3, 4, 5 };
        int[] result = ThreeLargestNums.getLargest3Products(arr);
        assertTrue(result[0] == -1 && result[1] == -1 && result[2] == 6 && result[3] == 24 && result[4] == 60);
        arr = new int[] { 2, 1, 2, 1, 2 };
        result = ThreeLargestNums.getLargest3Products(arr);
        assertTrue(result[0] == -1 && result[1] == -1 && result[2] == 4 && result[3] == 4 && result[4] == 8);
        arr = new int[] { 2, 4, 7, 1, 5, 3 };
        result = ThreeLargestNums.getLargest3Products(arr);
        assertTrue(result[0] == -1 && result[1] == -1 && result[2] == 56 && result[3] == 56 && result[4] == 140 &&
                result[5] == 140);
    }

    @Test
    public void threeSmallestLargestTest1() {
        int[] arr = new int[] { 1, 2, 3, 4, 5 };
        int[] result = ThreeLargestNums.getSmallest3Products(arr);
        assertTrue(result[0] == -1 && result[1] == -1 && result[2] == 6 && result[3] == 6 && result[4] == 6);
        arr = new int[] { 2, 1, 2, 1, 2 };
        result = ThreeLargestNums.getSmallest3Products(arr);
        assertTrue(result[0] == -1 && result[1] == -1 && result[2] == 4 && result[3] == 2 && result[4] == 2);
        arr = new int[] { 2, 4, 7, 1, 5, 3 };
        result = ThreeLargestNums.getSmallest3Products(arr);
        assertTrue(result[0] == -1 && result[1] == -1 && result[2] == 56 && result[3] == 8 && result[4] == 8 &&
                result[5] == 6);
    }

    @Test
    public void binaryTreeTest() {
        BinaryTreeLinked.Node n7 = new BinaryTreeLinked.Node(7);
        BinaryTreeLinked.Node n8 = new BinaryTreeLinked.Node(8);
        BinaryTreeLinked.Node n6 = new BinaryTreeLinked.Node(6);
        n6.left = n7;
        n6.right = n8;
        BinaryTreeLinked.Node n4 = new BinaryTreeLinked.Node(4);
        BinaryTreeLinked.Node n5 = new BinaryTreeLinked.Node(5);
        BinaryTreeLinked.Node n3 = new BinaryTreeLinked.Node(3);
        n3.left = n6;
        BinaryTreeLinked.Node n2 = new BinaryTreeLinked.Node(2);
        n2.left = n4;
        n2.right = n5;
        BinaryTreeLinked.Node n1 = new BinaryTreeLinked.Node(1);
        n1.left = n2;
        n1.right = n3;

        List<Integer> result = BinaryTreeLinked.getTreeResult(n1);
        assertTrue(result.size() == 8 && result.get(0) == 1 && result.get(1) == 2 && result.get(2) == 4 &&
                result.get(3) == 5 && result.get(4) == 7 && result.get(5) == 8 && result.get(6) == 6 &&
                result.get(7) == 3);
    }

    @Test
    public void socialDistanceTest() {
        long[] S = {2, 6};
        long count = CafeteriaSocialDistance.getMaxAdditionalDinersCount(10, 1, 2, S);
        assertTrue(count == 3);

        S = new long[] {11, 6, 14};
        count = CafeteriaSocialDistance.getMaxAdditionalDinersCount(15, 2, 3, S);
        assertTrue(count == 1);
    }

    @Test
    public void BstRangeSumTest() {
        TreeNode node3 = new TreeNode(3, null, null);
        TreeNode node7 = new TreeNode(7, null, null);
        TreeNode node5 = new TreeNode(5, node3, node7);
        TreeNode node18 = new TreeNode(18, null, null);
        TreeNode node15 = new TreeNode(15, null, node18);
        TreeNode root = new TreeNode(10, node5, node15);

        int sum = BstRangeSum.rangeSumBST(root, 7, 15);
        assertTrue(sum == 32);
        int sum1 = BstRangeSum.getRangeSum(root, 7, 15);
        assertTrue(sum1 == 32);

        sum = BstRangeSum.rangeSumBST(root, -1, 8);
        assertTrue(sum == 15);
        sum1 = BstRangeSum.getRangeSum(root, -1, 8);
        assertTrue(sum1 == 15);

        sum = BstRangeSum.rangeSumBST(root, 4, 16);
        assertTrue(sum == 37);
        sum1 = BstRangeSum.getRangeSum(root, 4, 16);
        assertTrue(sum1 == 37);
    }

    @Test
    public void findDuplicatedSubTree() {
        DuplicatedSubTree tree = new DuplicatedSubTree();
        TreeNode node4 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2, node4, null);
        TreeNode root4 = new TreeNode(4);
        TreeNode node3 = new TreeNode(3, node2, node4);
        TreeNode root = new TreeNode(1, node2, node3);
        List<TreeNode> result = tree.findDuplicateSubtrees(root);
    }

    @Test
    public void oddEvenJumpTest() {
        OddEvenJump jump = new OddEvenJump();
        int[] arr = { 10,13,12,14,15};
        int count = jump.oddEvenJumps(arr);
        assertTrue(count == 2);

        arr = new int[] {2,3,1,1,4};
        count = jump.oddEvenJumps(arr);
        assertTrue(count == 3);

        arr = new int[] {5,1,3,4,2};
        count = jump.oddEvenJumps(arr);
        assertTrue(count == 3);
    }

    @Test
    public void subsetTest() {
        Subset test = new Subset();
        int[] nums = {1,2,2};
        test.subsetsWithDup(nums);

    }

    @Test
    public void sortOnTest() {
        int[] input = new int[] {2, 1, 0};
        int[] result = CountSorting.sort(input);
        assertTrue(result[0] == 0 && result[1] == 1 && result[2] == 2);

        input = new int[] {2, 0, 1, 1, 1};
        result = CountSorting.sort(input);
        assertTrue(result[0] == 0 && result[1] == 1 && result[2] == 1 && result[3] == 1 && result[4] == 2);

        input = new int[] {3, 2, 2, 1, 1, 1};
        result = CountSorting.sort(input);
        assertTrue(result[0] == 1 && result[1] == 1 && result[2] == 1 && result[3] == 2 && result[4] == 2 && result[5] == 3);
    }

    @Test
    public void notifTest() {
        int[] arr = {2, 3, 4, 2, 3, 6, 8, 4, 5};
        int result = CountSorting.activityNotifications(arr, 5);
        assertTrue(result == 2);
        result = CountSorting.activityNotifications(arr, 3);
        assertTrue(result == 2);

        arr = new int[] {1, 4, 1, 2, 7, 1, 2, 5, 3, 6};
        double median = CountSorting.getMedian(arr, 10);
        assertTrue(median == 2.5);
        median = CountSorting.getMedian(arr, 5);
        assertTrue(median == 2.0);
        median = CountSorting.getMedian(arr, 6);
        assertTrue(median == 1.5);
    }
}
