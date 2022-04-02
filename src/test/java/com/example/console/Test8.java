package com.example.console;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test8 {

    @Test
    public void sfdcTest() {
        String[] input = new String[] {"3", "DEPEND a b c", "DEPEND d f", "LIST", "END"};
        //validation
        if (input == null || input.length == 0) {
            System.out.println("invalid input");
            return;
        }

        int count = Integer.valueOf(input[0]);
        if(input.length < 2 || !input[input.length-1].equals("END")) {
            System.out.println("invalid input");
            return;
        }
        for(int i = 1; i < count; i++) {
            String commandStr = input[i];
            if(commandStr == null || commandStr.length() < 4) {
                System.out.println("invalid input");
                continue;
            }
            String[] command = commandStr.split(" ");
            System.out.println("invalid input");
        }
    }

    @Test
    public void groupShiftStringTest() {
        String[] test = {"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"};
        List<List<String>> result = GroupShiftedStrings.groupStrings(test);
    }

    @Test
    public void mergeSortTest() {
        int[] input = new int[] { 12, 11, 13, 5, 6, 7 };
        MergeSort.sort(input, 0, input.length-1);
        input = new int[] { 1,1,3,4,8,6,7,6 };
        MergeSort.sort(input, 0, input.length-1);
    }

    @Test
    public void nextPermutationTest() {
        NextLargestPermutation.nextPermutation(new int[] {5,3,4,9,7,4});
    }
}
