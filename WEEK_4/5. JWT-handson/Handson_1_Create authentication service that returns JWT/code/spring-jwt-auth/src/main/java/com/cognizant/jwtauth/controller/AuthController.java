package com.cognizant.jwtauth.controller;

import com.cognizant.jwtauth.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/authenticate")
    public ResponseEntity<Map<String, String>> authenticate(Authentication authentication) {
        LOGGER.info("START - authenticate()");
        // spring injects 'authentication' populated by Basic Auth filter
        String username = authentication.getName();

        String token = JwtUtil.generateToken(username);

        LOGGER.info("END - authenticate()");
        return ResponseEntity.ok(Map.of("token", token));
    }
}
