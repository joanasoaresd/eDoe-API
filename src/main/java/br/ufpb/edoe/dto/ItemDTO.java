package br.ufpb.edoe.dto;

import br.ufpb.edoe.entity.Item;
import lombok.Data;

@Data
public class ItemDTO {

    private int id;
    private DescriptorDTO descriptor;
    private String description;
    private int qty;
    private String associatedEmail;

    public ItemDTO(Item i) {
        this.id = i.getId();
        this.descriptor = new DescriptorDTO(i.getDescriptor());
        this.description = i.getDescription();
        this.qty = i.getQty();
        this.associatedEmail = i.getAssociatedEmail();
    }
}
