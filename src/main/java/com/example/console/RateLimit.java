package com.example.console;

public class RateLimit {
    private class LimitInfo {
        int limits;
        String key;
        long lastCheckTime;
        int count;
    }
}
