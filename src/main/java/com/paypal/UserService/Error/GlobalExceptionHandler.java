package com.paypal.UserService.Error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.attribute.UserPrincipalNotFoundException;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ApiError> dataIntegrityVoilationError(DataIntegrityViolationException e){
        return new ResponseEntity<>(new ApiError("Data Integrity Voilation: "+e.toString(),
                HttpStatus.METHOD_NOT_ALLOWED),
                HttpStatus.METHOD_NOT_ALLOWED
        );
    }

    @ExceptionHandler
    public ResponseEntity<ApiError> handleGenericError(Exception e){
        ApiError apiError = new ApiError("An unexpected error : "+e.getLocalizedMessage()   , HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
    }

}
