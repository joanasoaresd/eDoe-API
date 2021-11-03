package br.ufpb.edoe.exceptions.advice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.ufpb.edoe.exceptions.DescriptorExistsException;
import br.ufpb.edoe.exceptions.UserExistsException;
import org.springframework.http.HttpStatus;

@RestControllerAdvice
public class RestExceptionHandler {

@ResponseStatus(HttpStatus.CONFLICT)
@ExceptionHandler(UserExistsException.class)
public ErrorResponseBody handleUserExistsException(UserExistsException e) {
    ErrorResponseBody error = new ErrorResponseBody();
    error.setStatus(e.getErrorStatus());
    error.setOrigin(e.getErrorOrigin());
    error.setDetail(e.getMessage());
    return error;
  }

@ResponseStatus(HttpStatus.CONFLICT)
@ExceptionHandler(DescriptorExistsException.class)
public ErrorResponseBody handleDescriptorExistsException(DescriptorExistsException e) {
    ErrorResponseBody error = new ErrorResponseBody();
    error.setStatus(e.getErrorStatus());
    error.setOrigin(e.getErrorOrigin());
    error.setDetail(e.getMessage());
    return error;
  }
    
}
