package com.UserService.MicroUserService.Exceptions;

public class ResourceNotFoundException extends RuntimeException{


    public ResourceNotFoundException(){
        super("the Resource not Found for this Id");
    }

    public ResourceNotFoundException(String msg){
        super(msg);
    }
}
