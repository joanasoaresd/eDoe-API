package br.ufpb.edoe.exceptions;

import org.springframework.http.HttpStatus;

public class UserExistsException extends IllegalMainException {

    public UserExistsException(String msg, String errorOrigin){
        super(msg, HttpStatus.CONFLICT.value(), errorOrigin);
    }
}
