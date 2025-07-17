package com.cognizant.jwtauth.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private static final Key SIGNING_KEY = Keys.hmacShaKeyFor(
            "my-super-secret-key-which-is-very-long-256bit!".getBytes());

    public static String generateToken(String subject) {
        long now = System.currentTimeMillis();
        long expiry = now + 10 * 60 * 1000; // 10 minutes

        return Jwts.builder()
                .subject(subject)
                .issuedAt(new Date(now))
                .expiration(new Date(expiry))
                .signWith(SIGNING_KEY)  // ✅ REPLACED HERE
                .compact();
    }
}
