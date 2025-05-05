package org.kizhaku.springaop.exception;
import java.util.function.Function;

public class UserNotFoundException extends RuntimeException{

    private String userId;
    private static String message = "User %s not found in system";

    public UserNotFoundException(String userId) {
        super(formatMessage.apply(userId));
        this.userId = userId;
    }

    public UserNotFoundException(String message, String userId) {
        super(message);
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public static Function<String, String> formatMessage = (format) -> message.formatted(format);
}
