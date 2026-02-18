package com.hackaton.waste.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

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
@Table (name = "discarded_waste")
@Data
@NoArgsConstructor
@AllArgsConstructor
   
public class DiscardedWaste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_classroom", nullable = false)
    private Classroom classroom; 

    @ManyToOne
    @JoinColumn(name = "id_residue", nullable = false)
    private Residue residue; 

    @ManyToOne
    @JoinColumn(name = "id_container", nullable = false)
    private Container container; 

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date = LocalDateTime.now();
    private Boolean isCorrect; 
    private Integer pointsEarned; 
}
    
