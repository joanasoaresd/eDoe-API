package br.ufpb.edoe.exceptions.user;

import org.springframework.http.HttpStatus;

import br.ufpb.edoe.exceptions.IllegalMainException;

public class UserExistsException extends IllegalMainException {

    public UserExistsException(String msg, String errorOrigin) {
        super(msg, HttpStatus.CONFLICT.value(), errorOrigin);
    }
}
