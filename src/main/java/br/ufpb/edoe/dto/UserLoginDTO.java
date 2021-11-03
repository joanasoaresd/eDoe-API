package br.ufpb.edoe.dto;

import lombok.Data;

@Data
public class UserLoginDTO {
  private String email;
  private String senha;
}
