package br.ufpb.edoe.dto;

import br.ufpb.edoe.entity.Descriptor;
import lombok.Data;

@Data
public class DescriptorDTO {

    private int id;
    private String name;

    public DescriptorDTO(Descriptor d){
        this.id = d.getId();
        this.name = d.getName().toLowerCase();
    }
}
