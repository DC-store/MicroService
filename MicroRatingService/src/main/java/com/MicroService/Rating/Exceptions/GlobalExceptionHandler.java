package com.MicroService.Rating.Exceptions;

import com.MicroService.Rating.Dtos.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.concurrent.ThreadPoolExecutor;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ApiResponse resourceNotfoundExcpetionHandler(ResourceNotFoundException exception){

        ApiResponse response = ApiResponse.builder().msg(exception.getMessage()).sucess(false).status(HttpStatus.NOT_FOUND).build();


        return response;



    }
}
