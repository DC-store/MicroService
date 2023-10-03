package com.HotelService.MicroService.Exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(){
        super("there is no data in the db");
    }

    public ResourceNotFoundException(String msg){
        super(msg);
    }
}
