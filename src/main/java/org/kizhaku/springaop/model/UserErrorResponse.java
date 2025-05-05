package org.kizhaku.springaop.model;

public class UserErrorResponse extends ErrorResponse {

    private String userId;

    public UserErrorResponse(String error, String message, String userId) {
        super(error, message);
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
