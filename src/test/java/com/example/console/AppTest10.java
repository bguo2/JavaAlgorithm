package com.example.console;

import com.example.console.graph.Denpendency;
import com.example.console.math.NthRoot;
import com.example.console.matrix.ImageRotation;
import com.example.console.recursion.TargetNumber;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.*;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest10 {

    @Test
    public void DenpendencyTest() {

        Denpendency denpendency = new Denpendency();
        String[] depends = { "B", "C" };
        denpendency.depend("A", depends);
        depends = new String[] { "D", "C" };
        denpendency.depend("B", depends);
        depends = new String[] { "D" };
        denpendency.depend("C", depends);
        depends = new String[] { "F" , "A"};
        denpendency.depend("E", depends);
        boolean hasCycle = denpendency.cycleExists();
        assertTrue(!hasCycle);
        denpendency.install("E");
        denpendency.list();
        denpendency.remove("D");
        denpendency.remove("E");
        denpendency.list();

        /*
        depends = new String[] {"A"};
        denpendency.depend("D", depends);
        hasCycle = denpendency.cycleExists();
        assertTrue(hasCycle);
         */

        denpendency.install("B");
        denpendency.list();
        denpendency.install("F");
        denpendency.list();
        denpendency.remove("C");

    }

    @Test
    public void KSmallestPairsTest() {
        List<Pair<Integer, Integer>> result = KSmallestPairs.getSmallestPairs(new int[]{1, 7, 11},
                new int[] {2, 4, 6}, 3);
        assertTrue(result.size() == 3 && result.get(0).getKey() == 1 && result.get(0).getValue() == 2
        && result.get(1).getKey() == 1 && result.get(1).getValue() == 4 && result.get(2).getKey() == 1 && result.get(2).getValue() == 6);

        result = KSmallestPairs.getSmallestPairs1(new int[]{1, 7, 11},
                new int[] {2, 4, 6}, 3);
        assertTrue(result.size() == 3 && result.get(0).getKey() == 1 && result.get(0).getValue() == 2
                && result.get(1).getKey() == 1 && result.get(1).getValue() == 4 && result.get(2).getKey() == 1 && result.get(2).getValue() == 6);
    }

    @Test
    public void TargetNumberTest() {
        List<Integer> list = Arrays.asList(1,2,3,4);
        boolean isReachable = TargetNumber.isReachable(list, 21);
        assertTrue(isReachable);
    }

    @Test
    public void ValidStringTest() {
        String isValid = ValidString.isValid("aabb");
        assertTrue("", isValid.equals("YES"));
        isValid = ValidString.isValid("ab");
        assertTrue("", isValid.equals("YES"));
        isValid = ValidString.isValid("abcc");
        assertTrue("", isValid.equals("YES"));
        isValid = ValidString.isValid("aabbcd");
        assertTrue("", isValid.equals("NO"));
        isValid = ValidString.isValid("aabbccddeefghi");
        assertTrue("", isValid.equals("NO"));
        isValid = ValidString.isValid("aaabbbcc");
        assertTrue("", isValid.equals("NO"));
    }

    @Test
    public void nthRootTest() {
        double n = NthRoot.findNthRoot(8, 3);
        assertTrue(Math.abs(n - 2.0) < 0.1);
    }

    @Test
    public void citationTest() {
        int ret = Hindex.hIndexII(new int[] {0, 1, 3, 5, 6});
        assertTrue(ret == 3);
        ret = Hindex.hIndexII(new int[] {0, 1, 3, 5, 6, 7, 9});
        assertTrue(ret == 4);
    }

    @Test
    public void largestHistogramTest() {
        int ret = LargestRectangleHistogram.largestRectangle(new int[] { 2,1,5,6,2,3 });
        assertTrue(ret == 10);
    }

    @Test
    public void imageRotateTest() {
        int[][] matrix = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };

        //ImageRotation.rotateClockwise(matrix);
        ImageRotation.rotateAntiClockwise(matrix);
        int[][] matrix1 = {
                {5, 1, 9,11},
                {2, 4, 8,10},
                {13, 3, 6, 7},
                {15,14,12,16}
        };
        //ImageRotation.rotateClockwise(matrix1);
        ImageRotation.rotateAntiClockwise(matrix1);
    }

    @Test
    public void missingPositiveTest() {
        int[] nums = {3,4,-1,1};
        int res = MissingPositive.firstMissingPositive(nums);
        assertTrue("", res == 2);
        res = MissingPositive.firstMissingPositive(new int[]{3,0,-1,2});
        assertTrue("", res == 1);
    }

    public static class TestClass {
        String value;
        int index;
        File file;
        public TestClass(String value, int i) {
            this.value = value;
            this.index = i;
            this.file = new File(value);
        }
    }
    @Test
    public void stackWithArrayImpTest() {
        StackImpWithArray<TestClass> s = new StackImpWithArray<>(3);
        try {
            s.push(new TestClass("1111",0));
            TestClass t = s.pop();
            t = s.pop();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void rangeAddionTest() {
        long res = ArrayOperations.arrayManipulation(5, new int[][] {
                {0, 1, 100},
                {1,4,100},
                {2,3, 100}
        });
        assertTrue("", res == 200);
        res = ArrayOperations.arrayManipulation(5, new int[][] {
                {1,3,2},
                {2,4,3},
                {0,2,-2}
        });
        assertTrue("", res == 5);
    }
}
