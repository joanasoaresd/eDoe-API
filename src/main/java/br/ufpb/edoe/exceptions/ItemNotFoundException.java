package br.ufpb.edoe.exceptions;

import org.springframework.http.HttpStatus;

public class ItemNotFoundException extends IllegalMainException {

    public ItemNotFoundException(String msg, String errorOrigin) {
        super(msg, HttpStatus.NOT_FOUND.value(), errorOrigin);
    }
}
