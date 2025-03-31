package com.example.cache.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SimpleCacheTest {

    @Test
    public void testCacheNormalCase() throws InterruptedException {
        SimpleCache<String, String> cache = new SimpleCache<>(10); // TTL = 1O sec
        cache.put("key1", "value1");

        assertEquals("value1", cache.get("key1"));
        Thread.sleep(1000);
        assertEquals("value1", cache.get("key1"));
    }

    @Test
    public void testCacheExpiration() throws InterruptedException {
        SimpleCache<String, String> cache = new SimpleCache<>(1); // TTL = 1 sec
        cache.put("key1", "value1");

        assertEquals("value1", cache.get("key1"));
        Thread.sleep(1500);
        assertNull(cache.get("key1"));
    }
}
