package com.mastery.java.task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.UnexpectedTypeException;
import java.time.ZonedDateTime;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler({NoSuchElementException.class,
                        EmployeeNotFoundException.class,
                        UnexpectedTypeException.class,
                        MethodArgumentNotValidException.class})
    public ApiException exceptionHandling(Exception e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ApiException apiException = new ApiException(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now()
        );
        return apiException;
    }

    @ExceptionHandler({RuntimeException.class})
    public String anotherErrors() {
        return "Ooops! Something is wrong!";
    }

}
