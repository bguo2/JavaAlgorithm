package com.example.console;

import com.example.console.matrix.ShortestDistToAllBuildings;
import com.example.console.graph.ShortestPathGraph;
import com.example.console.subarray.MinLargestSumSubArrays;
import com.example.console.subarray.MinSubArrayWithGreaterThanValue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

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
        String S = "ADOBECBADEBANC", T = "ABC";
        String result = MinWindowSubstring.getMinWindowSubstring(S, T);
        assertTrue(result.equals("CBA"));
        S = "ADOABECODEBANC";
        result = MinWindowSubstring.getMinWindowSubstring(S, T);
        assertTrue(result.equals("ABEC"));
        S = "ADOABECODEABANC";
        T = "AABC";
        result = MinWindowSubstring.getMinWindowSubstring(S, T);
        assertTrue(result.equals("ABANC"));

        S = "a";
        T = "a";
        result = MinWindowSubstring.getMinWindowSubstring(S, T);
        assertTrue(result.equals("a"));
        S = "aa";
        T = "aa";
        result = MinWindowSubstring.getMinWindowSubstring(S, T);
        assertTrue(result.equals("aa"));

        S = "thisisateststring";
        T = "tist";
        result = MinWindowSubstring.getMinWindowSubstring(S, T);
        assertTrue(result.equals("tstri"));
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

    @Test
    public void minLargeSumSubarrayTest() {
        MinLargestSumSubArrays test = new MinLargestSumSubArrays();
        int result = test.splitArray(new int[] {7, 2, 5, 10, 8}, 2);
        assertTrue(result == 18);
        result = test.splitArray(new int[] {7, 2, 5, 10, 1}, 2);
        assertTrue(result == 14);
    }

    @Test
    public void minSubArrayWithGreaterThanValueTest()
    {
        int len = MinSubArrayWithGreaterThanValue.getMinSubArray(new int[] {1, 4, 45, 6, 0, 19}, 51);
        assertTrue(len == 3);
        len = MinSubArrayWithGreaterThanValue.getMinSubArray(new int[] {1, 10, 5, 2, 7}, 9);
        assertTrue(len == 1);
        len = MinSubArrayWithGreaterThanValue.getMinSubArray(new int[] {1, 11, 100, 1, 0, 200, 3, 2, 1, 250}, 280);
        assertTrue(len == 4);
        len = MinSubArrayWithGreaterThanValue.getMinSubArray(new int[] {1, 2, 4}, 4);
        assertTrue(len == 0);
    }

    @Test
    //unweighed graph
    public void shortestPathInGraph() {
        ShortestPathGraph graph = new ShortestPathGraph(8);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(0,3 );
        graph.addEdge(3,7 );
        graph.addEdge(3,4 );
        graph.addEdge(4,7 );
        graph.addEdge(6,7 );
        graph.addEdge(4, 7);
        graph.addEdge(4, 6);
        graph.addEdge(4, 5);

        List<Integer> path = new ArrayList<>();
        int steps = graph.getShortestPath(0, 7, path);
        assertTrue(steps ==2);
        assertTrue(path.size() == 3 && path.get(0) == 0 && path.get(1) == 3 && path.get(2) == 7);

        List<Integer> path1 = new ArrayList<>();
        steps = graph.getShortestPath(2, 6, path1);
        assertTrue(steps == 5);
        assertTrue(path1.size() == 6 && path1.get(0) == 2 && path1.get(1) == 1 && path1.get(2) == 0 &&
                path1.get(3) == 3 && (path1.get(4) == 4  || path1.get(4) == 7)&& path1.get(5) == 6);
    }
}
