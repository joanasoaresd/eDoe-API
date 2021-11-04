package br.ufpb.edoe.dto;

import br.ufpb.edoe.enums.UserRoleType;
import lombok.Data;

@Data
public class UpdateRoleRequestDTO {
  private String email;
  private UserRoleType papel;
}
