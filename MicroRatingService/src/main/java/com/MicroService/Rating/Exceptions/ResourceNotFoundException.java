package com.MicroService.Rating.Exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String msg){

        super(msg);
    }

    public ResourceNotFoundException(){
        super("no data found for the id you gave");
    }
}
