package com.lcwd.electronic.store.exceptions;

import lombok.Builder;

@Builder
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(){

    }

    public ResourceNotFoundException(String message){
        super(message);
    }



}
