package com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.exception;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptions {

    //-- Handle custom "Not Found" exceptions
    @ExceptionHandler(NotFoundException.class)
    ProblemDetail handleNotFoundException(NotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setType(URI.create("about:blank"));
        problemDetail.setTitle("not found");
        problemDetail.setDetail(ex.getMessage());
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return problemDetail;
    }

    //-- Handle validation errors for request body (@RequestBody)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ProblemDetail handleMethodArgumentRequestBody(MethodArgumentNotValidException ex) {
        Map<String, String> errorHandler = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errorHandler.put(fieldError.getField(), Objects.requireNonNullElse(fieldError.getDefaultMessage(), "Invalid value"));
        }
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Validation failed");
        problemDetail.setProperty("errors", errorHandler);
        return problemDetail;
    }

    //-- Handle validation errors for method parameters (@PathVariable, @RequestParam)
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ProblemDetail handleHandlerMethodValidationPathException(HandlerMethodValidationException ex) {
        Map<String, String> errorHandler = new HashMap<>();

        for (MessageSourceResolvable pathError : ex.getAllErrors()) {
            for (String errorCode : pathError.getCodes()) {
                if (errorCode.contains("NotBlank") || errorCode.contains("NotNull")) {
                    errorHandler.put("request", Objects.requireNonNullElse(pathError.getDefaultMessage(), "Field cannot be blank or null"));
                } else if (errorCode.contains("Min.id")) {
                    errorHandler.put("venueId", Objects.requireNonNullElse(pathError.getDefaultMessage(), "Invalid venueId"));
                }
            }
        }

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Validation failed");
        problemDetail.setProperty("errors", errorHandler);
        return problemDetail;
    }
}