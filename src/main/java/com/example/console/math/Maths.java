package com.example.console.math;

public class Maths {

    //floor of square root, 4=>2; 8=>2; 9=>3
    public static int squrt(int x) throws Exception{
        if(x < 0)
            throw new Exception("Not valid operation");
        int start = 0, end = x, result = 0;
        while(start <= end) {
            int mid = (start + end) / 2;
            if(mid * mid == x)
                return mid;
            if(mid * mid < x) {
                start = mid + 1;
                result = mid;
            }
            else {
                end = mid - 1;
            }
        }

        return result;
    }
}
