package com.example.console;

import java.util.ArrayList;
import java.util.List;

//Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
//
//For example, given n = 3, a solution set is:
//
//[
//  "((()))",
//  "(()())",
//  "(())()",
//  "()(())",
//  "()()()"
//]
public class WellFormedParenthesis {

    public static List<String> generateWellFormedParenthesis(int n){
        if(n <= 0)
            return null;
        List<String> result = new ArrayList<>();
        backTrack(result, "", 0, 0, n);
        return result;
    }

    private static void backTrack(List<String> result, String cur, int open, int close, int max) {
        if(cur.length() == max*2) {
            result.add(cur);
            return;
        }

        if(open < max)
            backTrack(result, cur + "(", open+1, close, max);
        if(close < open)
            backTrack(result, cur + ")", open, close+1, max);
    }
}
