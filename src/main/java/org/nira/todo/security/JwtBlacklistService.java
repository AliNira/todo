package org.nira.todo.security;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class JwtBlacklistService {

    private final StringRedisTemplate redisTemplate;

    public void blacklistToken(String token, long expirationMs) {
        redisTemplate.opsForValue().set(token, "blacklisted", expirationMs, TimeUnit.MILLISECONDS);
    }

    public boolean isTokenBlacklisted(String token) {
        return redisTemplate.hasKey(token);
    }
}
