package org.kizhaku.springaop.advice;

import io.swagger.v3.oas.annotations.Hidden;
import org.kizhaku.springaop.model.ErrorResponse;
import org.kizhaku.springaop.model.UserErrorResponse;
import org.kizhaku.springaop.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Hidden
public class ExceptionResponseHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new UserErrorResponse("Missing user in system", ex.getMessage(), ex.getUserId()));
    }

    @ExceptionHandler(Exception.class) //Default global exception
    public ResponseEntity<ErrorResponse> handleDefaultError(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("An error has occured in server", "This is an uncaught exception"));
    }
}
