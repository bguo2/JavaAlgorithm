package com.example.console;

public class Reverse {

    public static int reverseNumber(int number, int result) {
        result = result*10 + number%10;
        if(Math.abs(number) < 10)
            return result;
        int tmp = reverseNumber(number/10, result);
        return tmp;
    }

    public static int reverseNumber(int number) {
        if(Math.abs(number) < 10)
            return number;
        int tmp = reverseNumber(number/10);
        tmp = (int)((number%10)*Math.pow(10, Integer.toString(Math.abs(number)).length() - 1) + tmp);
        return tmp;
    }

    public static String reverseString(String value) {
        if(value == null || value.length() == 1)
            return value;
        Character ch = value.charAt(0);
        String result = reverseString(value.substring(1));
        result = result + ch;
        return result;
    }
}