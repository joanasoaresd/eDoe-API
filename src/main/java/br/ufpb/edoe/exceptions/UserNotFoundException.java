package br.ufpb.edoe.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends IllegalMainException {
  public UserNotFoundException(String msg, String errorOrigin) {
    super(msg, HttpStatus.NOT_FOUND.value(), errorOrigin);
  }
}
