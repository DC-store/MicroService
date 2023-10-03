package com.HotelService.MicroService.Exceptions;

import com.HotelService.MicroService.ResponseCapture.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GobleExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ApiResponse resourceNotFoundExceptionHandler(ResourceNotFoundException excpetion){

       return ApiResponse.builder().msg(excpetion.getLocalizedMessage()).sucess(false).status(HttpStatus.NOT_FOUND).build();


    }
}
