package br.ufpb.edoe.exceptions;

import org.springframework.http.HttpStatus;

public class BadRequestParamsException extends IllegalMainException {
  public BadRequestParamsException(String msg, String errorOrigin) {
    super(msg, HttpStatus.BAD_REQUEST.value(), errorOrigin);
  }
}
