package com.example.cache.utils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SimpleCache<K, V> {
    private final ConcurrentHashMap<K, V> cache = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<K, Long> timestamps = new ConcurrentHashMap<>();
    private final long ttl; // in milliseconds

    public SimpleCache(long ttlSeconds) {
        this.ttl = ttlSeconds * 1000;
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(this::evictExpiredEntries, ttl, ttl, TimeUnit.MILLISECONDS);
    }

    public void put(K key, V value) {
        cache.put(key, value);
        timestamps.put(key, System.currentTimeMillis());
    }

    public V get(K key) {
        if (timestamps.containsKey(key) && (System.currentTimeMillis() - timestamps.get(key)) > ttl) {
            cache.remove(key);
            timestamps.remove(key);
            return null;
        }
        return cache.get(key);
    }

    private void evictExpiredEntries() {
        long now = System.currentTimeMillis();
        timestamps.forEach((key, time) -> {
            if (now - time > ttl) {
                cache.remove(key);
                timestamps.remove(key);
            }
        });
    }
}

