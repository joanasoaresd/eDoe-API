package br.ufpb.edoe.dto;

import lombok.Data;

@Data
public class DonateDTO {
  private int itemDonateId;
  private int itemRequiredId;
  private int qty;
}
