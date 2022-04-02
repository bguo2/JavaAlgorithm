package com.example.console;

import java.util.Stack;

public class Calculator {
    //Basic Calculator
    // Given a string s representing an expression, implement a basic calculator to evaluate it.
    //
    //
    //
    //Example 1:
    //
    //Input: s = "1 + 1"
    //Output: 2
    //Example 2:
    //
    //Input: s = " 2-1 + 2 "
    //Output: 3
    //Example 3:
    //
    //Input: s = "(1+(4+5+2)-3)+(6+8)"
    //Output: 23
    public int calculate_basic(String s) {
        int sign = 1;
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(Character.isDigit(ch)) {
                int num = 0;
                while(i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num*10 + s.charAt(i) - '0';
                    i++;
                }
                i--;
                res += sign*num;
            }
            else if(ch == '(') {
                stack.push(res);
                stack.push(sign);
                res = 0;
                sign = 1;
            }
            else if(ch == '+') {
                sign = 1;
            }
            else if(ch == '-') {
                sign = -1;
            }
            else if(ch == ')') {
                res *= stack.pop() ;
                res += stack.pop();
            }
        }

        return res;
    }

    //Given a string s which represents an expression, evaluate this expression and return its value.
    //
    //The integer division should truncate toward zero.
    //
    //
    //
    //Example 1:
    //
    //Input: s = "3+2*2"
    //Output: 7
    //Example 2:
    //
    //Input: s = " 3/2 "
    //Output: 1
    //Example 3:
    //
    //Input: s = " 3+5 / 2 "
    //Output: 5
    //without stack
    public int calculate_II(String s) {
        int sign = 1;
        int res = 0;
        int md = -1;
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(Character.isDigit(ch)) {
                int num = 0;
                while(i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                i--;
                num = sign * num;
                if(md < 0) {
                    stack.push(num);
                }
                else {
                    int temp;
                    if(md == 0) {
                        temp = stack.pop() * num;
                    }
                    else {
                        temp = stack.pop() / num;
                    }
                    stack.push(temp);
                }
            }
            else if(ch == '+') {
                sign = 1;
                md = -1;
            }
            else if(ch == '-') {
                sign = -1;
                md = -1;
            }
            else if(ch == '*') {
                sign = 1;
                md = 0;
            }
            else if(ch == '/') {
                sign = 1;
                md = 1;
            }
        }

        while(!stack.isEmpty()) {
            res += stack.pop();
        }

        return res;
    }

    //Implement a basic calculator to evaluate a simple expression string.
    //
    //The expression string contains only non-negative integers, '+', '-', '*', '/' operators, and open '(' and closing parentheses ')'. The integer division should truncate toward zero.
    //
    //You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].
    //
    //
    //
    //Example 1:
    //
    //Input: s = "1+1"
    //Output: 2
    //Example 2:
    //
    //Input: s = "6-4/2"
    //Output: 4
    //Example 3:
    //
    //Input: s = "2*(5+5*2)/3+(6/2+8)"
    //Output: 21
    //Example 4:
    //
    //Input: s = "(2+6*3+5-(3*14/7+2)*5)+3"
    //Output: -12
    //Example 5:
    //
    //Input: s = "0"
    //Output: 0
    public int calculate(String s) {
        int sign = 1;
        int md = -1, res = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            //search matched () callback itself
            if(ch == '(') {
                int j = i;
                int count = 0;
                for(j = i; j < s.length(); j++) {
                    if(s.charAt(j) == '(')
                        count++;
                    else if(s.charAt(j) == ')')
                        count--;
                    if(count == 0)
                        break;
                }
                int num = calculate(s.substring(i+1, j));
                i = j;
                if(md < 0) {
                    num = num*sign;
                    stack.push(num);
                }
                else {
                    int temp;
                    if(md == 0) {
                        temp = stack.pop() * num;
                    }
                    else
                        temp = stack.pop() / num;
                    stack.push(temp);
                }
            }
            else if(Character.isDigit(ch)) {
                int num = 0;
                while(i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num*10 + s.charAt(i) - '0';
                    i++;
                }
                i--;
                if(md < 0) {
                    num = num*sign;
                    stack.push(num);
                }
                else {
                    int temp;
                    if(md == 0) {
                        temp = stack.pop() * num;
                    }
                    else
                        temp = stack.pop() / num;
                    stack.push(temp);
                }
            }
            else if(ch == '+') {
                sign = 1;
                md = -1;
            }
            else if(ch == '-') {
                sign = -1;
                md = -1;
            }
            else if(ch == '*')
                md = 0;
            else if(ch == '/')
                md = 1;
        }

        while(!stack.isEmpty()) {
            res += stack.pop();
        }

        return res;
    }
}
