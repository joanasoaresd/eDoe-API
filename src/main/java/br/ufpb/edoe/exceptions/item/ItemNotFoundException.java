package br.ufpb.edoe.exceptions.item;

import org.springframework.http.HttpStatus;

import br.ufpb.edoe.exceptions.IllegalMainException;

public class ItemNotFoundException extends IllegalMainException {

    public ItemNotFoundException(String msg, String errorOrigin) {
        super(msg, HttpStatus.NOT_FOUND.value(), errorOrigin);
    }
}
