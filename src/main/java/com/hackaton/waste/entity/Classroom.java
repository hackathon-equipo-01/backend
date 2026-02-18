package com.hackaton.waste.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "classrooms")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Classroom {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne 
    @JoinColumn(name = "id_teacher", referencedColumnName = "id", nullable = false)
    private User teacher;

    @OneToMany(mappedBy = "classroom") 
    private List<User> users;

    @NotBlank(message = "El curso es obligatorio")
    @Size(min = 2, message = "Mínimo 2 caracteres")
    private String course;
    private int points;
}
