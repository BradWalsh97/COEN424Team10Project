package com.teamten.sizzle.handler;

import static org.springframework.http.HttpStatus.*;


import com.teamten.sizzle.exceptions.CookbookNotFoundException;
import com.teamten.sizzle.exceptions.ExceptionAmazonS3;
import com.teamten.sizzle.utils.SizzleError;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<SizzleError> buildResponseEntity(SizzleError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(ExceptionAmazonS3.class)
    protected ResponseEntity<SizzleError> handleFailedUploadImageS3(
            ExceptionAmazonS3 ex) {
        SizzleError apiError = SizzleError.builder()
                .status(INTERNAL_SERVER_ERROR)
                .debugMessage(ex.getMessage())
                .message("Failed to upload image to S3")
                .build();
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(CookbookNotFoundException.class)
    protected ResponseEntity<SizzleError> handleCookbookNotFoundException(
            CookbookNotFoundException ex) {
        SizzleError apiError = SizzleError.builder()
                .status(BAD_REQUEST)
                .debugMessage(ex.getMessage())
                .message("Failed to found cookbook with provided user and id")
                .build();
        return buildResponseEntity(apiError);
    }
}
