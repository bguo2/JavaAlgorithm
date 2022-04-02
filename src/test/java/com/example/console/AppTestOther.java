package com.example.console;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

public class AppTestOther {
    @Test
    public void asynchTest() throws Exception {
        List<CompletableFuture<Integer>> futures = new ArrayList<>();
        int total = 0;
        long start = System.currentTimeMillis();
        for(int i = 1; i < 10; i++) {
            final int index = i;
            CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return makeCall(index);
                }catch (Exception e) {
                }
                return 0;
            });
            futures.add(future);
        }

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
        CompletableFuture<List<Integer>> retFuture = allFutures.thenApply(item -> futures.stream()
                .map(f -> f.join())
                .collect(Collectors.toList()));
        total += retFuture.get().stream().mapToInt(c -> {
            System.out.println(c);
            return  c;
        }).sum();

        System.out.println((System.currentTimeMillis() - start) + " milli-seconds");
        assertTrue(total == 9*(1+9)/2);
    }

    private int makeCall(int i) throws Exception{
        Thread.sleep(100);
        return i;
    }

    @Test
    public void asynchTest1() throws Exception {
        long start = System.currentTimeMillis();
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> {
            try {
                return makeCall(0);
            }catch (Exception e) {
            }
            return 0;
        });
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> {
            try {
                return makeCall(100);
            }catch (Exception e) {
            }
            return 0;
        });
        f1 = f1.whenComplete((aVoid, throwable) -> System.out.println("Completed f1"));
        f2 = f2.whenComplete((aVoid, throwable) -> System.out.println("Completed f2"));
        CompletableFuture[] all = {f1, f2};
        CompletableFuture<Void> allOf = CompletableFuture.allOf(all);
        allOf.thenApply(item -> Arrays.stream(all).map(f -> f.join()))
            .whenComplete((aVoid, throwable) -> {
            System.out.println("Completed allOf");
        }).get();
        System.out.println("Joined");
        System.out.println((System.currentTimeMillis() - start) + " milli-seconds");
    }
}
