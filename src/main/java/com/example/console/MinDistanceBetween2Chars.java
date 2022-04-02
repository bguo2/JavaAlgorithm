package com.example.console;

//get min distance between 2 chars
//e.g. ['a','d','c','b','e','a'], 'a' and 'b' => 4, 'b','a'=>2
//output is 2
public class MinDistanceBetween2Chars {
    public static int getMinDistance(char[] input, char a, char b) {
        if(input == null || input.length < 2)
            return -1;
        if(a == b)
            return 0;
        int startPos = -1;
        int result = Integer.MAX_VALUE;
        char lastFind = (char)255;
        for(int i = 0; i < input.length; i++) {
            if(a == input[i] || b == input[i]) {
                if(startPos == -1) {
                    startPos = i;
                    lastFind = input[i];
                    continue;
                }
                if(input[i] == lastFind) {
                    startPos = i;
                    continue;
                }
                lastFind = input[i];
                result = Math.min(result, i - startPos);
                startPos = i;
            }
        }

        if(result == Integer.MAX_VALUE)
            return -1;
        return result;
    }
}
