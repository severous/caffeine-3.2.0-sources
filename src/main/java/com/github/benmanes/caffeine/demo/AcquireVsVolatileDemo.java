package com.github.benmanes.caffeine.demo;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public class AcquireVsVolatileDemo {
    static class Shared {
        volatile int data = 0;
        volatile boolean ready = false;

        static final VarHandle READY_HANDLE;
        static {
            try {
                READY_HANDLE = MethodHandles.lookup().findVarHandle(Shared.class, "ready", boolean.class);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Running with getAcquire() ===");
        for (int i = 0; i < 10_000; i++) {
            if (testWithAcquire()) {
                System.out.println("⚠️ Inconsistency detected with getAcquire() in run #" + i);
                break;
            }
        }
    }

    static boolean testWithAcquire() throws InterruptedException {
        Shared shared = new Shared();

        final boolean[] seenInconsistency = {false};

        Thread reader = new Thread(() -> {
            while (!(boolean) Shared.READY_HANDLE.get(shared)) {
                Thread.onSpinWait(); // spin
            }
            System.out.println(shared.data);
            if (shared.data != 42) {
                seenInconsistency[0] = true; // saw ready == true, but data != 42!
            }
        });

        Thread writer = new Thread(() -> {
            shared.data = 42;
            Shared.READY_HANDLE.set(shared, true);
        });

        writer.start();
        reader.start();
        writer.join();
        reader.join();
        return seenInconsistency[0];
    }
}

