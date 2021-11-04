package br.ufpb.edoe.dto;

import lombok.Getter;

@Getter
public class LoginResponseDTO {
  private String message;

  public LoginResponseDTO(String message) {
    this.message = message;
  }
}
