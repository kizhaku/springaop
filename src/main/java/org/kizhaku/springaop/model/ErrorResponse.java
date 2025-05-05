package org.kizhaku.springaop.model;

import java.time.LocalDateTime;

public class ErrorResponse {
    String error;
    String message;
    LocalDateTime localDateTime;

    public ErrorResponse(String error, String message) {
        this.error = error;
        this.message = message;
        this.localDateTime = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
}
