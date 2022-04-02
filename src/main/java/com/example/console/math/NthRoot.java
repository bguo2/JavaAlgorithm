package com.example.console.math;

//find nth root of an integer
//8 ^^ 1/3 = 2
public class NthRoot {

    public static double findNthRoot(int number, int n) {
        if(n == 1)
            return number;
        if(number == 0)
            return 0;
        if(n < 0)
            return 1 / findNthRoot(number, Math.abs(n));
        double low = 1.0, high = number;
        while(low < high) {
            double mid = (low + high) / 2.0;
            double result = 1.0;
            for(int i = 0; i < n; i++)
                result *= mid;
            if(Math.abs(result - number) < 0.0001)
                return mid;
            if(result > number)
                high = mid;
            else
                low = mid;
        }

        return low;
    }
}
