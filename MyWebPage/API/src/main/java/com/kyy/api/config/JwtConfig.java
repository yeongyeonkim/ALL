package com.kyy.api.config;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JwtConfig {
    @Value("spring.jwt.secret")
    private String secretKey;

    private long expiredToken = 1000L * 60 * 60;
}
