package br.ufpb.edoe.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidUserRoleException extends IllegalMainException {
  public InvalidUserRoleException(String msg, String errorOrigin) {
    super(msg, HttpStatus.UNAUTHORIZED.value(), errorOrigin);
  }
}
