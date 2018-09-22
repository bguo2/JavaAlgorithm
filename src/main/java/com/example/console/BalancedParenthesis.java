package com.example.console;

import java.util.LinkedList;
import java.util.Stack;

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
            else if(input.charAt(i) == ')'){
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
}
