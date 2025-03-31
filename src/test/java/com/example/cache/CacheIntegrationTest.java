package com.example.cache;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CacheIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCachePutAndGet() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestJson = "{\"key\": \"testKey\", \"value\": \"testValue\"}";
        HttpEntity<String> request = new HttpEntity<>(requestJson, headers);

        ResponseEntity<Void> putResponse = restTemplate.exchange("/cache/put", HttpMethod.POST, request, Void.class);
        assertEquals(HttpStatus.OK, putResponse.getStatusCode());

        ResponseEntity<String> response = restTemplate.getForEntity("/cache/get?key=testKey", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("testValue", response.getBody());
    }
}
