package com.example.cache.controller.request;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CacheRequest {
    private String key;
    private String value;

}
