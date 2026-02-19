package com.hackaton.waste.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table (name = "classrooms")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"users"}) 
@EqualsAndHashCode(exclude = {"users"})
public class Classroom {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne 
    @JoinColumn(name = "id_teacher", referencedColumnName = "id", nullable = false)
    private User teacher;

    @OneToMany(mappedBy = "classroom", cascade= CascadeType.ALL) 
    private List<User> users;

    @NotBlank(message = "El curso es obligatorio")
    @Size(min = 2, message = "Mínimo 2 caracteres")
    private String course;
    private int points;
}
