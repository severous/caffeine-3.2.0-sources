package com.github.benmanes.caffeine.demo;


import com.google.errorprone.annotations.concurrent.GuardedBy;

public class GuardedByTest {
    @GuardedBy("lock")
    private int counter;

    private final Object lock = new Object();


    public void increment() {
        synchronized (lock){
            counter++;
        }

    }
}
