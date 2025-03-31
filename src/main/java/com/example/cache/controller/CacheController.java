package com.example.cache.controller;

import com.example.cache.controller.request.CacheRequest;
import com.example.cache.service.CacheService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cache")
public class CacheController {
    private final CacheService cacheService;

    public CacheController(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @PostMapping("/put")
    public void put(@RequestBody CacheRequest request) {
        cacheService.put(request.getKey(), request.getValue());
    }

    @GetMapping("/get")
    public String get(@RequestParam String key) {
        return cacheService.get(key);
    }
}
