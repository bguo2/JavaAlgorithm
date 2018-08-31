package com.example.console;

import java.util.LinkedList;

//input (aa(dss)(, output aa(dss): remove the unbalanced parenthesis
public class BalancedParenthesis {

    public static String getBalancedString(String input){
        if(input == null || input.length() == 0)
            return input;
        //stored the position of ( or )
        LinkedList<Integer> positions = new LinkedList<>();
        for(int i = 0; i < input.length(); i++){
            if(input.charAt(i) == '(')
                positions.add(i);
            else if(input.charAt(i) == ')'){
                //matched
                if(!positions.isEmpty() && input.charAt(positions.getLast()) == '(')
                    positions.removeLast();
                else
                    positions.add(i);
            }
        }

        //balanced
        if(positions.isEmpty())
            return input;
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < input.length(); i++) {
            if(!positions.isEmpty() && positions.get(0) == i) {
                positions.remove();
                continue;
            }
            result.append(input.charAt(i));
        }

        return result.toString();
    }
}
