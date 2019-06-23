package com.example.console;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest

public class AppTest2 {

    @Test
    public void minCostTest() {
        MinHiringCost minHiringCost = new MinHiringCost();
        int[] q = {3,1,10,10,1};
        int[] w = {4,8,2,2,7};

        double cost = minHiringCost.getMinCost(w, q, 3);
        assertTrue(Math.abs(cost - 30.66666) < 0.001);

        int[] q1 = {10,20,5};
        int[] w1 = {70,50,30};
        cost = minHiringCost.getMinCost(w1, q1, 2);
        assertTrue(Math.abs(cost - 105.00000) < 0.001);
    }

    @Test
    public void shortestToAllBuildingsTest()
    {
        int[][] matrix = {
            {1,0,2,0,1},
            {0,0,0,0,0},
            {0,0,1,0,0}
        };

        ShortestDistToAllBuildings obj = new ShortestDistToAllBuildings();
        int shortest = obj.getShortestDistance(matrix);
        assertTrue(shortest == 7);
    }

    @Test
    public void minWindowSubstringTest()
    {
        String S = "ADOBECODEBANC", T = "ABC";
        String result = MinWindowSubstring.getMinWindowSubstring(S, T);
        assertTrue(result.equals("BANC"));
        S = "ADOABECODEBANC";
        result = MinWindowSubstring.getMinWindowSubstring(S, T);
        assertTrue(result.equals("ABEC"));
        S = "ADOABECODEBANC";
        T = "AABC";
        result = MinWindowSubstring.getMinWindowSubstring(S, T);
        assertTrue(result.equals("ADOABEC"));
    }

    @Test
    public void minCostToMerge()
    {
        int cost = MinCostToMerge.getMinCost(new int[] {4, 7});
        assertTrue(cost == 11);

        cost = MinCostToMerge.getMinCost(new int[] {8, 4, 7});
        assertTrue(cost == 30);

        cost = MinCostToMerge.getMinCost(new int[] {4,3,2,6});
        assertTrue(cost == 29);
    }
}
