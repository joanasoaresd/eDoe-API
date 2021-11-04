package br.ufpb.edoe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.ufpb.edoe.enums.UserClassType;
import br.ufpb.edoe.enums.UserRoleType;
import lombok.Data;

@Data
@Entity
@Table(name = "Users")
public class User {

  @Id
  @Column(name = "USER_ID", unique = true)
  private String email;

  private String senha;
  private String nome;
  private int celular;
  private UserClassType classe;
  private String identificador;
  private UserRoleType papel = UserRoleType.APENAS_DOADOR;
}
