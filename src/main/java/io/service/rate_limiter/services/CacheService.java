package io.service.rate_limiter.services;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

    @Value("${ratelimiter.maxRequests:5}")
    int maxRequests;

    @Value("${ratelimiter.timeFrameSeconds:60}")
    int timeFrame;

    @Autowired
    private RedisTemplate<String, Integer> redisTemplate;

    public boolean canAccess(String key) {
        Integer requestCount = redisTemplate.opsForValue().get(key);
        return requestCount == null || requestCount <= maxRequests;
    }

    public Long getTtl(String key) {
        return redisTemplate.getExpire(key);
    }

    public void incrementAccess(String key) {
        Long requestCount = redisTemplate.opsForValue().increment(key);
        if (requestCount == 1) {
            redisTemplate.expire(key, timeFrame, TimeUnit.SECONDS);
        }
    }

}
