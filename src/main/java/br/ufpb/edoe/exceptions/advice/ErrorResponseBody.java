package br.ufpb.edoe.exceptions.advice;

import lombok.Data;

@Data
public class ErrorResponseBody {
    
    private int status;
    private String origin;
    private String detail;
  
    public ErrorResponseBody() {
      super();
    }
}
