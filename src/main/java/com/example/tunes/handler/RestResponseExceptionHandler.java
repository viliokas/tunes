package com.example.tunes.handler;

import com.example.tunes.exception.NotRespondingException;
import com.example.tunes.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class RestResponseExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundExceptionException(
            UserNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(prepareResponseBody(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotRespondingException.class)
    public ResponseEntity<Object> handleNotRespondingExceptionException(
            NotRespondingException ex, WebRequest request) {
        return new ResponseEntity<>(prepareResponseBody(ex.getMessage()), HttpStatus.SERVICE_UNAVAILABLE);
    }

    private Map<String, Object> prepareResponseBody(String message) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", message);
        return body;
    }

}
