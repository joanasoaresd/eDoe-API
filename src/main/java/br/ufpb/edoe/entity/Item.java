package br.ufpb.edoe.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.ufpb.edoe.dto.DescriptorDTO;
import lombok.Data;

@Data
@Entity
@Table(name = "Item")
public class Item {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Embedded
    private DescriptorDTO descriptor;
    private String description;
    private int qty;
}
