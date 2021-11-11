package br.ufpb.edoe.exceptions.descriptor;

import org.springframework.http.HttpStatus;

import br.ufpb.edoe.exceptions.IllegalMainException;

public class DescriptorExistsException extends IllegalMainException {
    
    public DescriptorExistsException(String msg, String errorOrigin){
        super(msg, HttpStatus.CONFLICT.value(), errorOrigin);
    }
}
