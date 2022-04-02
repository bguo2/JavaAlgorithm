package com.example.console.math;

import java.util.Arrays;

public class Primes {
    //count primes numbers which is less than n
    public static int countPrimes(int n) {
        if(n < 2)
            return 0;
        boolean[] primes = new boolean[n];
        Arrays.fill(primes, true);
        int res = 0;
        for(int i = 0; i < n; i++) {
            if(!primes[i])
                continue;
            res++;
            for(int j = 2; j*i < n; j++) {
                primes[j*i] = false;
            }
        }
        return res;
    }
}
