package com.claver.bloggingPlatform.exception.error;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.Map;

@Builder
@Getter
public class ErrorResponse {
    private final int status;
    private final String message;
    private final Instant timestamp;
    private final Map<String, String> fieldErrors;
}
