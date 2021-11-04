package br.ufpb.edoe.exceptions;

import org.springframework.http.HttpStatus;

public class DescriptorExistsException extends IllegalMainException {
    
    public DescriptorExistsException(String msg, String errorOrigin){
        super(msg, HttpStatus.CONFLICT.value(), errorOrigin);
    }
}
