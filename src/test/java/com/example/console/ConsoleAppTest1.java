package com.example.console;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsoleAppTest1 {

    @Test
    public void subsetTest() {
        int[] arr = {4,3,6};
        ArrayList<ArrayList<Integer>>  result = Subsets.subsets(arr);
        Iterator<ArrayList<Integer>> iterator = result.iterator();

        while(iterator.hasNext()){
            ArrayList<Integer> list = iterator.next();
        }
    }

    @Test
    public void balancedParenthesisTest() {
        String result = BalancedParenthesis.getBalancedString(null);
        assertTrue(result == null);

        result = BalancedParenthesis.getBalancedString("");
        assertTrue(result.length() == 0);

        result = BalancedParenthesis.getBalancedString("(");
        assertTrue(result.length() == 0);

        result = BalancedParenthesis.getBalancedString(")");
        assertTrue(result.length() == 0);

        result = BalancedParenthesis.getBalancedString("()");
        assertTrue(result.equals("()"));

        result = BalancedParenthesis.getBalancedString("a(bc(ds)");
        assertTrue(result.equals("abc(ds)"));

        result = BalancedParenthesis.getBalancedString("a(bc(ds)))");
        assertTrue(result.equals("a(bc(ds))"));

        result = BalancedParenthesis.getBalancedString("((())");
        assertTrue(result.equals("(())"));
        result = BalancedParenthesis.getBalancedString("((()fd)fd))))");
        assertTrue(result.equals("((()fd)fd)"));
        result = BalancedParenthesis.getBalancedString("((()fd)fd))((((");
        assertTrue(result.equals("((()fd)fd)"));
        result = BalancedParenthesis.getBalancedString("((()fd)fd))))(())");
        assertTrue(result.equals("((()fd)fd)(())"));
    }

    @Test
    public void wordLadderTest() {
        WordLadder ladder = new WordLadder();
        Set<String> wordDict = new HashSet<String>();
        wordDict.add("hot");
        wordDict.add("dot");
        wordDict.add("dog");
        wordDict.add("lot");
        wordDict.add("log");
        int count = ladder.getShortest("hit", "cog", wordDict);
        assertTrue(count == 5);
    }
}
