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

    String name;

    @OneToMany(mappedBy="wasteType", cascade= CascadeType.ALL)
    @JsonIgnore
    private List<Residue> residueList;

    @OneToMany(mappedBy="wasteType", cascade= CascadeType.ALL)
    @JsonIgnore
    private List<Container> containers;

}
