package com.example.console;

import org.junit.Test;

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
}
