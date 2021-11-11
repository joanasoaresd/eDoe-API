package br.ufpb.edoe.exceptions.user;

import org.springframework.http.HttpStatus;

import br.ufpb.edoe.exceptions.IllegalMainException;

public class UserNotFoundException extends IllegalMainException {
  public UserNotFoundException(String msg, String errorOrigin) {
    super(msg, HttpStatus.NOT_FOUND.value(), errorOrigin);
  }
}
