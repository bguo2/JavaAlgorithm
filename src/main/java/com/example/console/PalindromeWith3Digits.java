package com.example.console;

//find the largest palindrome of a number that can be obtained by multiplying two 3 digit numbers
//since 999*999=998001
//so the largest Palindrome number won't be greater than 998001
public class PalindromeWith3Digits {

    public int getLargestNumber() {
        int n = 998;
        boolean found = false;
        int first, second, paliNum = -1;
        while (!found) {
            n--;
            paliNum = makePalindrome(n);
            for(int i = 999; i > 99; i--) {
                if(paliNum / i > 999) {
                    break;
                }

                if(paliNum % i == 0) {
                    first = paliNum / i;
                    second = i;
                    found = true;
                    break;
                }
            }
        }

        return paliNum;
    }

    private int makePalindrome(int n) {
        StringBuilder builder = new StringBuilder();
        builder.append(n);
        String newString = String.valueOf(n) + builder.reverse().toString();
        return Integer.valueOf(newString);
    }

}
