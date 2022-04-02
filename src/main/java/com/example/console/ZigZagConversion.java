package com.example.console;
//The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
//
//P   A   H   N
//A P L S I I G
//Y   I   R
//And then read line by line: "PAHNAPLSIIGYIR"
//
//Write the code that will take a string and make this conversion given a number of rows:
//
//string convert(string s, int numRows);
//
//
//Example 1:
//
//Input: s = "PAYPALISHIRING", numRows = 3
//Output: "PAHNAPLSIIGYIR"
//Example 2:
//
//Input: s = "PAYPALISHIRING", numRows = 4
//Output: "PINALSIGYAHRPI"
//Explanation:
//P     I    N
//A   L S  I G
//Y A   H R
//P     I
//Example 3:
//
//Input: s = "A", numRows = 1
//Output: "A"
//
//
//Constraints:
//
//1 <= s.length <= 1000
//s consists of English letters (lower-case and upper-case), ',' and '.'.
//1 <= numRows <= 1000

//建立一个大小为 numRows 的字符串数组，为的就是把之字形的数组整个存进去，然后再把每一行的字符拼接起来，就是想要的结果了。
// 顺序就是按列进行遍历，首先前 numRows 个字符就是按顺序存在每行的第一个位置，然后就是 ‘之’ 字形的连接位置了，
// 可以发现其实都是在行数区间 [1, numRows-2] 内，只要按顺序去取字符就可以了，最后把每行都拼接起来即为所求
public class ZigZagConversion {
    public String convert(String s, int numRows) {
        StringBuilder[] temp = new StringBuilder[numRows];
        for(int i = 0; i < numRows; i++)
            temp[i] = new StringBuilder();

        int n = s.length();
        for(int i = 0; i < n;) {
            for(int j = 0; j < numRows && i < n; j++) {
                temp[j].append(s.charAt(i++));
            }

            for(int k = numRows - 2; k > 0 && i < n; k--) {
                temp[k].append(s.charAt(i++));
            }
        }

        StringBuilder result = new StringBuilder();
        for(StringBuilder s1 : temp) {
            result.append(s1.toString());
        }

        return result.toString();
    }
}
