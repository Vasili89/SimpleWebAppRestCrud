package com.mastery.java.task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.UnexpectedTypeException;
import java.time.ZonedDateTime;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler({NoSuchElementException.class,
                        UnexpectedTypeException.class,
                        MethodArgumentNotValidException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiException exceptionHandling(Exception e) {

        ApiException apiException = new ApiException(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now()
        );
        return apiException;
    }

    @ExceptionHandler({EmployeeNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ApiException handle(Exception e) {

        ApiException apiException = new ApiException(
                e.getMessage(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now()
        );
        return apiException;
    }

    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public String anotherErrors() {
        return "Ooops! Something is wrong!";
    }

}
