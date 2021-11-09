package br.ufpb.edoe.exceptions.advice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.ufpb.edoe.exceptions.BadRequestParamsException;
import br.ufpb.edoe.exceptions.DescriptorExistsException;
import br.ufpb.edoe.exceptions.DescriptorNotFoundException;
import br.ufpb.edoe.exceptions.InvalidUserRoleException;
import br.ufpb.edoe.exceptions.UserExistsException;
import br.ufpb.edoe.exceptions.UserNotFoundException;
import br.ufpb.edoe.exceptions.UserNotLoggedException;

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

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(UserNotFoundException.class)
  public ErrorResponseBody handleUserNotFoundException(UserNotFoundException e) {
    ErrorResponseBody error = new ErrorResponseBody();
    error.setStatus(e.getErrorStatus());
    error.setOrigin(e.getErrorOrigin());
    error.setDetail(e.getMessage());
    return error;
  }

  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ExceptionHandler(UserNotLoggedException.class)
  public ErrorResponseBody handleUserNotLoggedException(UserNotLoggedException e) {
    ErrorResponseBody error = new ErrorResponseBody();
    error.setStatus(e.getErrorStatus());
    error.setOrigin(e.getErrorOrigin());
    error.setDetail(e.getMessage());
    return error;
  }

  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ExceptionHandler(InvalidUserRoleException.class)
  public ErrorResponseBody handleInvalidUserRoleException(InvalidUserRoleException e) {
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

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(DescriptorNotFoundException.class)
  public ErrorResponseBody handleDescriptorNotFoundException(DescriptorNotFoundException e) {
    ErrorResponseBody error = new ErrorResponseBody();
    error.setStatus(e.getErrorStatus());
    error.setOrigin(e.getErrorOrigin());
    error.setDetail(e.getMessage());
    return error;
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(BadRequestParamsException.class)
  public ErrorResponseBody handleBadRequestParamsException(BadRequestParamsException e) {
    ErrorResponseBody error = new ErrorResponseBody();
    error.setStatus(e.getErrorStatus());
    error.setOrigin(e.getErrorOrigin());
    error.setDetail(e.getMessage());
    return error;
  }

}
