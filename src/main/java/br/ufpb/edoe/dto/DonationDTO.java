package br.ufpb.edoe.dto;

import java.time.LocalDateTime;

import br.ufpb.edoe.entity.Descriptor;
import br.ufpb.edoe.entity.Donation;
import lombok.Data;

@Data
public class DonationDTO {
    private int id;
    private int idItem;
    private String descriptor;
    private String description;
    private int qty;
    private String nameUserDonate;
    private LocalDateTime createDateTime;

    public DonationDTO(Donation d) {
        this.id = d.getId();
        this.nameUserDonate = d.getUserDonate().getNome();
        this.idItem = d.getItemDonate().getId();
        this.description = d.getItemDonate().getDescription();
        this.qty = d.getQty();
        this.descriptor = d.getItemDonate().getDescriptor().getName();
        this.createDateTime = d.getCreateDateTime();
    }
}
