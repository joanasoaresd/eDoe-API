package br.ufpb.edoe.dto;

import br.ufpb.edoe.entity.User;
import br.ufpb.edoe.enums.UserClassType;
import br.ufpb.edoe.enums.UserRoleType;
import lombok.Data;

@Data
public class UserDTO {

  private String email;
  private String nome;
  private int celular;
  private UserClassType classe;
  private String identificador;
  private UserRoleType papel = UserRoleType.APENAS_DOADOR;

  public UserDTO(User u) {
    this.email = u.getEmail();
    this.nome = u.getNome();
    this.celular = u.getCelular();
    this.papel = u.getPapel();
    this.classe = u.getClasse();
    this.identificador = u.getIdentificador();
  }
}
