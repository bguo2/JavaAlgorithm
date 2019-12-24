package com.example.console;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void pacificAtlanticWaterFlowTest() {
        int[][] matrix = {
                {1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}
        };
        PacificAtlanticWaterFlow pawf = new PacificAtlanticWaterFlow();
        List<int[]> res = pawf.getResult(matrix);
        assertTrue(res != null && res.size() == 7);
    }

    @Test
    public void shortestGetAllKeysTest() {
        int[][] matrix = {
                {'@','.', 'a', '.', '#'},
                {'#', '#', '#', '.', '#'},
                {'b', '.', 'A', '.', 'B'}
        };

        ShortestPathGetAllKeys test = new ShortestPathGetAllKeys();
        int step = test.shortestPaht(matrix);
        assertTrue(step == 8);

        int[][] matrix1 = {
                {'@', '.', '.', 'a', 'A'},
                {'.', '.', 'B', '#', '.'},
                {'.', '.', '.', '.', 'b'}
        };
        step = test.shortestPaht(matrix1);
        assertTrue(step == 6);
    }

    @Test
    public void medianOfSortedArrayTest() {
        int[] num1 = {1, 3};
        int[] num2 = {2};

        MedianOfSortedArray test = new MedianOfSortedArray();
        double median = test.medianOfSortedArray(num1, num2);
        assertTrue(median == 2.0);

        int[] num3 = {1, 2};
        int[] num4 = {3, 4};
        median = test.medianOfSortedArray(num3, num4);
        assertTrue(median == 2.5);
    }

    @Test
    public void iterator1Test() {
        List<Integer> list = new ArrayList<>();
        Iterator1<Integer> iterator1 = new Iterator1<>(list.iterator());

        boolean hasNext = iterator1.hasNext();
        assertTrue("should has no ", hasNext == false);
        Integer e = iterator1.getNextObject();
        assertTrue(e == null);

        list.add(1);
        list.add(20);
        list.add(30);

        iterator1 = new Iterator1<>(list.iterator());
        hasNext = iterator1.hasNext();
        assertTrue(hasNext);
        e = iterator1.getNextObject();
        assertTrue(e == 1);

        hasNext = iterator1.hasNext();
        assertTrue(hasNext);
        e = iterator1.getNextObject();
        assertTrue(e == 20);

        e = iterator1.getNextObject();
        assertTrue(e == 30);
    }

    @Test
    public void wordLadder1Test() {
        List<String> dict = new ArrayList<String>() {{
            add("hot");
            add("dot");
            add("dog");
            add("lot");
            add("log");
        }};

        List<List<String>> result = new WordLadder1().getResult("hit", "cog", dict);
        assertTrue(result != null && result.size() == 2);
    }
}
