package com.github.benmanes.caffeine.demo;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Expiry;
import com.github.benmanes.caffeine.cache.Weigher;
import org.jspecify.annotations.Nullable;

import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class Demo {
    public static void main(String[] args) throws InterruptedException {
        Cache<String, @Nullable Object> cache = Caffeine.newBuilder()
                .maximumWeight(100)
                .weigher(new Weigher<String, Object>() {
                    @Override
                    public int weigh(String key, Object value) {
                        return 2;
                    }
                })
                .expireAfter(new Expiry<String, Object>() {
                    @Override
                    public long expireAfterCreate(String key, Object value, long currentTime) {
                        return 0;
                    }

                    @Override
                    public long expireAfterUpdate(String key, Object value, long currentTime, long currentDuration) {
                        return 0;
                    }

                    @Override
                    public long expireAfterRead(String key, Object value, long currentTime, long currentDuration) {
                        return 0;
                    }
                }).build();


        cache.put("key1", 1000);
        cache.put("key1", 2000);
        Object value = cache.getIfPresent("key");
        System.out.println(value);
        cache.policy()
                .expireVariably()
                .ifPresent(policy -> policy.put("key", "30000", 13, TimeUnit.SECONDS));
        Thread.sleep(300000);

    }
}

