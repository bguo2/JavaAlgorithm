package com.example.console;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

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

    @Test
    public void stringConcatenationTest() {
        String[] words = {"foo", "bar"};
        List<Integer> positions = StringConcatenation.getStartingIndex("barfoothefoobarman", words);
        assertTrue(positions.size() == 2 && positions.get(0) == 0 && positions.get(1) == 9);
        positions = StringConcatenation.getStartingIndex("foobarthebarfooman", words);
        assertTrue(positions.size() == 2 && positions.get(0) == 0 && positions.get(1) == 9);

        //duplicates in words
        String[] words1 = {"foo", "bar", "foo"};
        positions = StringConcatenation.getStartingIndex("foobarfoothefoobarfoobar", words1);
        assertTrue(positions.size() == 2 && positions.get(0) == 0 && positions.get(1) == 12);
        positions = StringConcatenation.getStartingIndex("foobarfoothefoobarfoobarfoo", words1);
        assertTrue(positions.size() == 3 && positions.get(0) == 0 && positions.get(1) == 12 && positions.get(2) == 18);

        positions = StringConcatenation.getStartingIndex("catbatatecatatebat", new String[] {"cat", "ate", "bat"});
        assertTrue(positions.size() == 3 && positions.get(0) == 0 && positions.get(1) == 3 && positions.get(2) == 9);

        positions = StringConcatenation.getStartingIndex("abcdababcd", new String[] {"ab", "ab", "cd"});
        assertTrue(positions.size() == 3 && positions.get(0) == 0 && positions.get(1) == 2 && positions.get(2) == 4);

        positions = StringConcatenation.getStartingIndex("abcdababcd", new String[] {"ab", "ab"});
        assertTrue(positions.size() == 1 && positions.get(0) == 4);
    }

    @Test
    public void binaryTreePathSumTest(){
        BinaryTreePathSum pathSum = new BinaryTreePathSum();
        BinaryTreePathSum.TreeNode root = pathSum.new TreeNode(null, null, 5);
        List<List<Integer>> result = pathSum.getAllPathsSum(null, 5);
        assertTrue(result == null);

        result = pathSum.getAllPathsSum(root, 5);
        assertTrue(result.size() == 1 && result.get(0).get(0) == 5);

        BinaryTreePathSum.TreeNode curNode = root.addNode(4, true);
        curNode = curNode.addNode(11, true);
        curNode.addNode(7, true);
        curNode.addNode(2, false);

        curNode = root.addNode(8, false);
        curNode.addNode(13, true);
        curNode = curNode.addNode(4, false);
        curNode.addNode(5, true);
        curNode.addNode(1, false);

        result = pathSum.getAllPathsSum(root, 22);
    }

    @Test
    public void wellFormedParenthesisTest() {
        List<String> result = WellFormedParenthesis.generateWellFormedParenthesis(0);
        assertTrue(result == null);

        result = WellFormedParenthesis.generateWellFormedParenthesis(1);
        assertTrue(result != null && result.get(0).equals("()"));

        result = WellFormedParenthesis.generateWellFormedParenthesis(2);
        assertTrue(result != null && result.size() == 2 && result.contains("(())") && result.contains("()()"));
    }
}
