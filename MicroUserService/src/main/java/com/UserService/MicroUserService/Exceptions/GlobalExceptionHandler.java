package com.UserService.MicroUserService.Exceptions;

import com.UserService.MicroUserService.ResponseCapture.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler
    public ResponseEntity<ApiResponse> exceptionHandlingForResourceNotFound(ResourceNotFoundException exception){


       ApiResponse response= ApiResponse.builder().msg(exception.getMessage()).status(HttpStatus.NOT_FOUND).sucess(false).build();

       return new ResponseEntity(response, HttpStatus.NOT_FOUND);




    }



}
