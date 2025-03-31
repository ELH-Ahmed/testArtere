package com.example.cache.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CacheServiceTest {

    @Mock
    private com.example.cache.utils.SimpleCache<String, String> simpleCache;

    @InjectMocks
    private CacheService cacheService;

    public CacheServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPutAndGet() {
        cacheService.put("testKey", "testValue");
        when(simpleCache.get("testKey")).thenReturn("testValue");
        assertEquals("testValue", cacheService.get("testKey"));
    }
}
