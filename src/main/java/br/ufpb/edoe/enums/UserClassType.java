package br.ufpb.edoe.enums;

public enum UserClassType {
  PESSOA_FISICA("PESSOA FÍSICA"), IGREJA("IGREJA"), ORGAO_PUBLICO_MUNICIPAL("ORGÃO PÚBLICO MUNICIPAL"),
  ORGAO_PUBLICO_ESTADUAL("ORGÃO PÚBLICO ESTADUAL"), ORGAO_PUBLICO_FEDERAL("ORGÃO PÚBLICO FEDERAL"), ONG("ONG"),
  ASSOCIACAO("ASSOACIAÇÃO"), SOCIEDADE("SOCIEDADE");

  private final String value;

  UserClassType(String value) {
    this.value = value.toUpperCase();
  }

  public String getValue() {
    return value;
  }
}
