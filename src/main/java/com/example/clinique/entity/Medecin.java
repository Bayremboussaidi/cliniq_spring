package com.example.clinique.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ManyToAny;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Medecin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idmedecin;
    private String nommedecin;
    @Enumerated(EnumType.STRING)
    private Specialite specialite;
    private  int telephone;
    private int prixconsultation;
    @ManyToMany(mappedBy = "medecins")
    @JsonIgnore
    private List<Clinique> cliniques;
    @OneToMany(mappedBy = "medecin")
    @JsonIgnore
    private List<Rendevous> rendevousList;
}
