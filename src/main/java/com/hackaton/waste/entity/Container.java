package com.hackaton.waste.entity;

import com.hackaton.waste.entity.enums.State;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table (name = "containers")
@Data
@NoArgsConstructor
@AllArgsConstructor

    
public class Container {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private State state;

    @ManyToOne
    @JoinColumn (name="id_waste_type", nullable =false, referencedColumnName = "id")
    private WasteType wasteType;

    private int maxCapacity = 1000;

    private int currentVolume;

}
