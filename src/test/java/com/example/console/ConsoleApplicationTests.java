package com.example.console;

import com.example.console.graph.DirectedGraph;
import com.example.console.graph.UndirectedGraph;
import com.example.console.math.Maths;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsoleApplicationTests {

    @Test
    public void undirectedGraphTest() {
        UndirectedGraph g = new UndirectedGraph(3);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);

        boolean isCyclic = g.isCyclic();
        System.out.println("Is Cyclic: " + isCyclic);
        assertTrue(isCyclic);

        UndirectedGraph g1 = new UndirectedGraph(5);
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

        UndirectedGraph g2 = new UndirectedGraph(5);
        g2.addEdge(0, 1);
        g2.addEdge(1, 2);
        g2.addEdge(3, 4);
        int count = g2.getConnectedComponents();
        assertTrue(count == 2);
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
        assertTrue(result == 9);
        result = test.findKthNumber(3, new int[] {9, 3, 8, 7, 1});
        assertTrue(result == 7);
        result = test.findKthNumber(3, new int[] {9, -3, 8, 7, -1, 10, 5});
        assertTrue(result == 8);

        result = test.findKthNumber(4, new int[] {3,2,3,1,2,4,5,5,6});
        assertTrue(result == 4);
    }

    @Test
    public void quickSortTest() {
        QuickSort test = new QuickSort();
        int[] result = test.quickSort(new int[] {5});
        assertTrue(result != null && result.length == 1);

        result = test.quickSort(new int[] {9, 3, 5, 1, 7, 5, 8, 7, 3});
        assertTrue(result.length == 9);

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

    @Test
    public void MathTest() {
        try
        {
            assertTrue(Maths.squrt(1) == 1);
            assertTrue(Maths.squrt(2) == 1);
            assertTrue(Maths.squrt(4) == 2);
            assertTrue(Maths.squrt(9) == 3);
            assertTrue(Maths.squrt(8) == 2);
        }
        catch (Exception e){

        }
    }

    @Test
    public void MaxSlidingWindowTest(){
        int[] nums = { 1,3,-1,-3,5,3,6,7 };
        int[] result = MaxSlidingWindow.maxSlindingWindow(nums, 3);

        result = MaxSlidingWindow.maxSlidingNumber(nums, 3);
        nums = new int[] { 8,10,9,-7,-4,-8,2,-6};
        result = MaxSlidingWindow.maxSlidingNumber(nums, 5);
    }

    @Test
    public void MergeKSortedListTest() {
        class MyList<E>  implements SortedListIterator<E> {
            private List<E> list = new ArrayList<>();
            private Iterator<E> iterator;

            @Override
            public boolean hasNext() {
                boolean hasNext = iterator.hasNext();
                return hasNext;
            }

            @Override
            public E next() {
                E e =  this.iterator.next();
                return e;
            }

            public void add(E value) {
                this.list.add(value);
                iterator = list.iterator();
            }
        }

        MergeKSortedList<Integer> merge = new MergeKSortedList<Integer>();
        MyList<Integer> in1 = new MyList<>();
        in1.add(3);
        in1.add(7);
        in1.add(7);
        in1.add(11);

        MyList<Integer> in2 = new MyList<>();
        in2.add(4);
        in2.add(12);

        List<SortedListIterator<Integer>> input = new ArrayList<>();
        input.add(in1);
        input.add(in2);

        System.out.println();
        List<Integer> result = merge.mergeSortedList(input);
        Iterator<Integer> iterator = result.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Test
    public void SmallestPairTest() {
        int[] arr1 = {1, 7, 11};
        int[] arr2 = {2, 4, 6};

        List<Pair<Integer, Integer>> result = KSmallestPairs.getSmallestPairs(arr1, arr2, 4);
        Iterator<Pair<Integer, Integer>> iterator = result.iterator();
        while (iterator.hasNext())
        {
            Pair<Integer, Integer> pair = iterator.next();
            System.out.println("(" + pair.getKey() + "," + pair.getValue() + ")");
        }
    }
}
