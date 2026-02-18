package com.hackaton.waste.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "waste_types")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WasteType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, message = "Mínimo 3 caracteres")
    String name;

    @OneToMany(mappedBy="residues", cascade= CascadeType.ALL)
    @JsonIgnore
    private List<Residue> residueList;

    @OneToMany(mappedBy="containers", cascade= CascadeType.ALL)
    @JsonIgnore
    private List<Container> containers;

}
