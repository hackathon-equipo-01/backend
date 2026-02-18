package com.hackaton.waste.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "residues")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Residue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    
    @ManyToOne
    @JoinColumn (name="id_waste_type", nullable =true, referencedColumnName = "id")
    private WasteType wasteType;

    private int size;

}
