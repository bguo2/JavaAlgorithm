package com.example.console;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest

public class AppTest3 {
    @Test
    public void swimInRisingWaterTest() {
        int[][] matrix = {
                {0, 2},
                {1, 3}
        };

        SwimInRisingWater test = new SwimInRisingWater();

        int waitTime = test.getMinWaitTime(matrix);
        assertTrue(waitTime == 3);

        int[][] matrix1 = {
                {0, 1,2, 3,4},
                {24,23,22,21,5},
                {12,13,14,15,16},
                {11,17,18,19,20},
                {10,9,8,7,6}
        };

        waitTime = test.getMinWaitTime(matrix1);
        assertTrue(waitTime == 16);
    }
}
