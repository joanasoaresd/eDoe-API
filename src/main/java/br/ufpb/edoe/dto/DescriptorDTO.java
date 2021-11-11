package br.ufpb.edoe.dto;

import javax.persistence.Column;

import br.ufpb.edoe.entity.Descriptor;
import lombok.Data;

@Data
public class DescriptorDTO {

    @Column(name = "id_descriptor")
    private int id;
    @Column(name = "name")
    private String name;

    public DescriptorDTO(Descriptor d) {
        this.id = d.getId();
        this.name = d.getName().toLowerCase();
    }
}
