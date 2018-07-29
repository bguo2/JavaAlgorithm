package com.example.console;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsoleApplicationTests {

    @Test
    public void undirectedGraphTest() {
        Graph g = new Graph(3);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);

        boolean isCyclic = g.isCyclic();
        System.out.println("Is Cyclic: " + isCyclic);
        assertTrue(isCyclic);

        Graph g1 = new Graph(5);
        g1.addEdge(0, 1);
        g1.addEdge(0, 2);
        g1.addEdge(2,3);
        g1.addEdge(2, 4);
        isCyclic = g1.isCyclic();
        System.out.println("Is Cyclic: " + isCyclic);
        assertFalse(isCyclic);

        g1.addEdge(1,4);
        isCyclic = g1.isCyclic();
        System.out.println("Is Cyclic: " + isCyclic);
        assertTrue(isCyclic);
    }

    @Test
    public void directedGraphTest() {
        DirectedGraph g = new DirectedGraph(5);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 3);
        g.addEdge(2,4);
        g.addEdge(3,4);
        boolean isCyclic = g.isCyclic();
        System.out.println("Directed graph is cyclic: "+ isCyclic);
        assertFalse(isCyclic);

        g.addEdge(4,1);
        isCyclic = g.isCyclic();
        System.out.println("Directed graph after add 4=>1 is cyclic: "+ isCyclic);
        assertFalse(isCyclic);

        g.addEdge(4,0);
        isCyclic = g.isCyclic();
        System.out.println("Directed graph after add 4=>0 is cyclic: "+ isCyclic);
        assertTrue(isCyclic);
    }

    @Test
    public void quickSelectTest() {

        QuickSelect test = new QuickSelect();
        int result = test.findKthNumber(2, new int[] {});
        assertTrue(result == -1);
        result = test.findKthNumber(2, new int[] {5});
        assertTrue(result == -1);
        result = test.findKthNumber(1, new int[] {5});
        assertTrue(result == 5);
        result = test.findKthNumber(1, new int[] {9, 3});
        assertTrue(result == 3);
        result = test.findKthNumber(3, new int[] {9, 3, 8, 7, 1});
        assertTrue(result == 7);
        result = test.findKthNumber(3, new int[] {9, -3, 8, 7, -1, 10, 5});
        assertTrue(result == 5);
        result = test.findKthNumber(3, new int[] {9, -3, 8, 7, -1, 10, 5});
        assertTrue(result == 5);
        result = test.findKthNumber(3, new int[] {-1, -3, 5, 7, 8, 9, 10});
        assertTrue(result == 5);
        result = test.findKthNumber(3, new int[] {10, 8, 7, 5, -1, -4, -5});
        assertTrue(result == -1);

        //return array
        int[] result1 = test.findKSmallestNumber(3, new int[] {9, -3, 8, 7, -1, 10, 5});
        assertTrue(result1 != null && result1.length == 3 &&
            result1[0] == -3 && result1[1] == -1 && result1[2] == 5);

        result1 = test.findKSmallestNumber(3, new int[] {-3, -1, 5, 7, 8, 9, 10});
        assertTrue(result1 != null && result1.length == 3 &&
                result1[0] == -3 && result1[1] == -1 && result1[2] == 5);

        result1 = test.findKSmallestNumber(3, new int[] {10, 8, 7, 5, -1, -4, -5});
        assertTrue(result1 != null && result1.length == 3 &&
                result1[0] == -5 && result1[1] == -4 && result1[2] == -1);
    }

    @Test
    public void quickSortTest() {
        QuickSort test = new QuickSort();
        int[] result = test.quickSort(new int[] {5});
        assertTrue(result != null && result.length == 1);

        result = test.quickSort(new int[] {9, 3, 8, 7, 1});
        assertTrue(result.length == 5 && result[0] == 1 && result[1] == 3 && result[2] == 7 &&
        result[3] == 8 && result[4] == 9);

        result = test.quickSort(new int[] {1, 3, 7, 8, 9});
        assertTrue(result.length == 5 && result[0] == 1 && result[1] == 3 && result[2] == 7 &&
                result[3] == 8 && result[4] == 9);

        result = test.quickSort(new int[] {9, 8, 7, 3, 1});
        assertTrue(result.length == 5 && result[0] == 1 && result[1] == 3 && result[2] == 7 &&
                result[3] == 8 && result[4] == 9);
    }

    @Test
    public void SortedKArraysTest() {
        MergeKSortedArray m = new MergeKSortedArray();
        int[] result = m.merge(null);
        assertTrue(result == null);

        result = m.merge(new int[][] {{3, 5, 7, 8}});
        assertTrue(result != null && result.length == 4 && result[0] == 3 && result[3] == 8);

        result = m.merge(new int[][] {{3, 5, 7, 8}, {4, 6, 12, 15}});
        assertTrue(result != null && result.length == 8 && result[0] == 3 && result[1] == 4
                && result[2] == 5 && result[3] == 6 && result[4] == 7 && result[5] == 8 && result[6] == 12 && result[7] == 15);

    }

    @Test
    public void FindKSmallestTest() {
        FindKSmallest obj = new FindKSmallest();
        int[] result = obj.find(null, 8);
        assertTrue(result == null);

        result = obj.find(new int[] {8}, 8);
        assertTrue(result != null && result.length == 1 && result[0] == 8);

        result = obj.find(new int[] { -5, 7, 0, -6, 15, 3}, 2);
        assertTrue(result != null && result.length == 2 && result[0] == -6 && result[1] == -5);

        result = obj.find(new int[] { -5, 7, 0}, 4);
        assertTrue(result != null && result.length == 3 && result[0] == -5 && result[1] == 0 && result[2] == 7);

        result = obj.find(new int[] { -5, 7, 0, -6, 15, 3}, 3);
        assertTrue(result != null && result.length == 3 && result[0] == -6 && result[1] == -5 && result[2] == 0);

    }
}
