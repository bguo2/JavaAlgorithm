package com.example.console;

import java.util.*;

//input (aa(dss)(, output aa(dss): remove the unbalanced parenthesis
public class BalancedParenthesis {

    public static String getBalancedString(String input){
        if(input == null || input.length() == 0)
            return input;
        //stored the position of ( or )
        Stack<int[]> stack = new Stack<>();
        for(int i = 0; i < input.length(); i++){
            if(input.charAt(i) == '(') {
                int[] a = {i, 0};
                stack.push(a);
            }
            else if(input.charAt(i) == ')') {
                //not match ), the top one is )
                if(stack.isEmpty() || stack.peek()[1] == 1) {
                    int[] a = {i, 1};
                    stack.push(a);
                }
                //matched
                else {
                    if(!stack.isEmpty())
                        stack.pop();
                }
            }
        }

        //balanced
        if(stack.isEmpty())
            return input;
        StringBuilder result = new StringBuilder();
        for(int i = input.length() - 1; i > -1 ; i--) {
            if(!stack.isEmpty() && stack.peek()[0] == i) {
                stack.pop();
                continue;
            }
            result.append(input.charAt(i));
        }

        return result.reverse().toString();
    }

    //Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
    public static List<String> getallbalancedParentthesis(String input)
    {
        List<String> result = new ArrayList<>();
        if(input == null || input.isEmpty()) {
            result.add("");
            return result;
        }

        HashSet<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        queue.offer(input);
        while (!queue.isEmpty())
        {
            String s = queue.poll();
            if(isValid(s))
            {
                result.add(s);
                continue;
            }

            //has not found any valid, continue
            if(result.isEmpty())
            {
                //remove one ( or )
                for(int i = 0; i < s.length(); i++)
                {
                    // we only try to remove left or right paren
                    if (s.charAt(i) != '(' && s.charAt(i) != ')')
                        continue;

                    String tmp = s.substring(0, i) + s.substring(i+1);
                    if(!visited.contains(tmp))
                    {
                        visited.add(tmp);
                        queue.offer(tmp);
                    }
                }
            }
        }
        return result;
    }

    //helper function checks if string s contains valid parantheses
    private static boolean isValid(String s)
    {
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') count++;
            else if (c == ')') {
                count--;
                if(count < 0)
                    return false;
            }
        }

        return count == 0;
    }
}
