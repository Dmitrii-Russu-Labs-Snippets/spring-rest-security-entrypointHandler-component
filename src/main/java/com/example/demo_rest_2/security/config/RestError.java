package com.example.demo_rest_2.security.config;

import java.time.Instant;

public record RestError(
        String status,
        Instant timestamp
) {}
