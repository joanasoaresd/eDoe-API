package br.ufpb.edoe.exceptions.descriptor;

import org.springframework.http.HttpStatus;

import br.ufpb.edoe.exceptions.IllegalMainException;

public class DescriptorNotFoundException extends IllegalMainException {

    public DescriptorNotFoundException(String msg, String errorOrigin) {
        super(msg, HttpStatus.NOT_FOUND.value(), errorOrigin);
    }
}
