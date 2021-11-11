package br.ufpb.edoe.exceptions.user;

import org.springframework.http.HttpStatus;

import br.ufpb.edoe.exceptions.IllegalMainException;

public class UserNotLoggedException extends IllegalMainException {
  public UserNotLoggedException(String msg, String errorOrigin) {
    super(msg, HttpStatus.UNAUTHORIZED.value(), errorOrigin);
  }
}
