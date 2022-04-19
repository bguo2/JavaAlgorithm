package com.example.console;

import com.example.console.graph.ComponentCycle;
import com.example.console.subarray.MaxSubArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.test.util.AssertionErrors.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest7 {
    @Test
    public void maxSubArrayTest() {
        int[] input = new int[] {1, 2, 3};
        int[] result = MaxSubArray.maxSubArray(input);
        assertTrue("", result.length == 3 && result[0] == 1 && result[2] == 3);

        input = new int[] {-2,1,-3,4,-1,2,1,-5,4};
        result = MaxSubArray.maxSubArray(input);
        assertTrue("", result.length==4 && result[0]==4 && result[1]==-1);
    }

    @Test
    public void cycleTest() {
        ComponentCycle test = new ComponentCycle();

        test.addEdge("A", "B");
        test.addEdge("A", "C");
        test.addEdge("D", "C");

        boolean hasCycle = test.hasCycle();
        assertTrue("", hasCycle == false);

        test.addEdge("D", "A");
        test.addEdge("C", "D");
        hasCycle = test.hasCycle();
        assertTrue("", hasCycle == false);
    }

    @Test
    public void largestPalidromeTest() {
        PalindromeWith3Digits test = new PalindromeWith3Digits();
        int max = test.getLargestNumber();
        assertTrue("", max == 906609);
    }

    @Test
    public void test7() {
        int num = 269;
//if use bit operation >>> other than >>, you don't have to worry about the sign
        char[] map = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        String res = num == 0 ? "0" : "";
        while (num != 0){
            res = map[num & 15] + res;  //. pls use & instead of %
            num >>>= 4;
        }

        System.out.println(res);
    }

    @Test
    public void palindromePairtest() {
        PalindromePair pair = new PalindromePair();
        String[] test = {"bat","tab","cat"};
        pair.palindromePairs(test);
    }
}
