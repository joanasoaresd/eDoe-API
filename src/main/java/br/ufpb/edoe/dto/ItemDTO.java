package br.ufpb.edoe.dto;

import br.ufpb.edoe.entity.Descriptor;
import br.ufpb.edoe.entity.Item;
import lombok.Data;

@Data
public class ItemDTO {
    
    private int id;
    private Descriptor descriptor;
    private String description;
    private int qty;

    public ItemDTO(Item i) {
        this.id = i.getId();
        this.descriptor = i.getDescriptor();
        this.description = i.getDescription();
        this.qty = i.getQty();
    }
}
