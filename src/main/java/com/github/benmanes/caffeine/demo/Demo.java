package com.github.benmanes.caffeine.demo;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Expiry;
import org.jspecify.annotations.Nullable;

import java.util.concurrent.TimeUnit;

public class Demo {
    public static void main(String[] args) {
        Cache<String, @Nullable Object> cache = Caffeine.newBuilder()
                .maximumSize(2)
                .expireAfter(new Expiry<String, Object>() {
                    @Override
                    public long expireAfterCreate(String key, Object value, long currentTime) {
                        return TimeUnit.SECONDS.toNanos(30);
                    }

                    @Override
                    public long expireAfterUpdate(String key, Object value, long currentTime, long currentDuration) {
                        return currentDuration;
                    }

                    @Override
                    public long expireAfterRead(String key, Object value, long currentTime, long currentDuration) {
                        return currentDuration;
                    }
                }).build();

        cache.put("key", 1000);
        Object value = cache.getIfPresent("key");
        System.out.println(value);

    }
}

