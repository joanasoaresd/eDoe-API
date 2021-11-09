package br.ufpb.edoe.exceptions;

import org.springframework.http.HttpStatus;

public class DescriptorNotFoundException extends IllegalMainException {

    public DescriptorNotFoundException(String msg, String errorOrigin) {
        super(msg, HttpStatus.NOT_FOUND.value(), errorOrigin);
    }
}
