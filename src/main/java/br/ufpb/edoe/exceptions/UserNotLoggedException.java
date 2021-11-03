package br.ufpb.edoe.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotLoggedException extends IllegalMainException {
  public UserNotLoggedException(String msg, String errorOrigin) {
    super(msg, HttpStatus.UNAUTHORIZED.value(), errorOrigin);
  }
}
