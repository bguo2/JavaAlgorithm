package com.example.console;

import com.example.console.binarytree.BinaryTreeLinked;
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
}
