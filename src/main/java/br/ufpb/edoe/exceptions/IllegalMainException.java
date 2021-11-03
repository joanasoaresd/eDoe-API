package br.ufpb.edoe.exceptions;

import lombok.Getter;

@Getter
public class IllegalMainException extends IllegalArgumentException {
    
    private final int errorStatus;
    private final String errorOrigin;
    private final String message;

  public IllegalMainException(String message, int errorStatus, String errorOrigin) {
    super();
    this.errorStatus = errorStatus;
    this.errorOrigin = errorOrigin;
    this.message = message;
  }
}
