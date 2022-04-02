package com.example.console.recursion;

import java.util.ArrayList;
import java.util.List;

//Given a string containing only digits, restore it by returning all possible valid IP address combinations.
//
//For example: given "25525511135",return ["255.255.11.135", "255.255.111.35"].
//一个有效的IP地址由4个数字组成，每个数字在0-255之间。对于其中的2位数或3位数，不能以0开头。所以对于以s[i]开头的数字有3种可能：
//
//1. s[i]
//2. s[i : i+1]，s[i] !=0时
//3. s[i : i+2]，s[i] != 0，且s[i : i+2] <= 255
public class RestoreIPAddress {

    public static List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<String>();
        helper(s, 0, "", result);
        return result;
    }

    public static void helper(String s, int n, String out, List<String> result) {
        //possible result
        if (n == 4) {
            //consumed all characters?
            if (s.isEmpty())
                result.add(out);
            return;
        }

        for (int k = 1; k < 4; ++k) {
            if (s.length() < k)
                break;
            String first = s.substring(0, k);
            String second = s.substring(k);
            if(first.isEmpty())
                continue;
            int value = Integer.valueOf(first);
            if(value > 255)
                continue;
            //has prefix 0
            if(String.valueOf(value).length() != first.length())
                continue;
            helper(second, n + 1, out + first + (n == 3 ? "" : "."), result);
        }
    }
}
