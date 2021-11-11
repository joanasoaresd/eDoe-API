package br.ufpb.edoe.exceptions.user;

import org.springframework.http.HttpStatus;

import br.ufpb.edoe.exceptions.IllegalMainException;

public class InvalidUserRoleException extends IllegalMainException {
  public InvalidUserRoleException(String msg, String errorOrigin) {
    super(msg, HttpStatus.UNAUTHORIZED.value(), errorOrigin);
  }
}
