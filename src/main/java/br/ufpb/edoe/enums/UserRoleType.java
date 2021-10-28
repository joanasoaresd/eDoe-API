package br.ufpb.edoe.enums;

public enum UserRoleType {
  APENAS_DOADOR("APENAS DOADOR"), APENAS_RECEPTOR("APENAS RECEPTOR"), DOADOR_RECEPTOR("DOADOR RECEPTOR"),
  ADMIN("ADMIN");

  private final String value;

  UserRoleType(String value) {
    this.value = value.toUpperCase();
  }

  public String getValue() {
    return value;
  }
}
