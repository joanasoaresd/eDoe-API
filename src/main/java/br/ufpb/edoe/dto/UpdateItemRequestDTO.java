package br.ufpb.edoe.dto;

import lombok.Data;

@Data
public class UpdateItemRequestDTO {
    private int qty;
    private String description;
}
