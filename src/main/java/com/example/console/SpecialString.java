package com.example.console;

//A string is said to be a special string if either of two conditions is met:
//
//All of the characters are the same, e.g. aaa. All characters except the middle one are the same, e.g. aadaa.
// A special substring is any substring of a string which meets one of those criteria. Given a string, determine how many special
// substrings can be formed from it.
//
//For example, given the string s= mnonopoo, we have the following special substrings:
//
//{m, n, o, n, o, p, o, o, non, ono, opo, oo}
public class SpecialString {
    // no need dp
    public static int countSpecialSubString(String s) {
        char[] str = s.toCharArray();
        int count = 0;
        for(int i = 0; i < str.length; i++) {
            int diffIndex = -1;
            for(int j = i; j < str.length; j++) {
                //single char
                if( j == i) {
                    count++;
                    continue;
                }
                //aba
                if(str[i] == str[j]) {
                    if(diffIndex == -1 || (diffIndex - i == j - diffIndex))
                        count++;
                }
                else {
                    //ab
                    if(diffIndex == -1)
                        diffIndex = j;
                    //abc
                    else
                        break;
                }
            }
        }

        return count;
    }
}
