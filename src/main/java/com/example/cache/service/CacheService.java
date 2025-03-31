package com.example.cache.service;

import com.example.cache.utils.SimpleCache;
import org.springframework.stereotype.Service;

@Service
public class CacheService {
    private final SimpleCache<String, String> cache = new SimpleCache<>(30);

    public void put(String key, String value) {
        cache.put(key, value);
    }

    public String get(String key) {
        return cache.get(key);
    }
}
