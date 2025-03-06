package com.example.Syllabus.model.structure_acad;

import com.example.Syllabus.model.contenu_acad.UniteEnseignement;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;
import java.util.HashSet;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "composante_niveau_etude")
public class ComposanteNiveauEtude {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "niveau_etude_id", nullable = false)
    private NiveauEtude niveauEtude;

     // Une ComposanteNiveauEtude d'enseignement contient plusieurs UE
     @OneToMany(mappedBy = "composanteNiveauEtude", cascade = CascadeType.ALL, orphanRemoval = true)
     @Builder.Default
     private Set<UniteEnseignement> unitesEnseignement = new HashSet<>();
}

