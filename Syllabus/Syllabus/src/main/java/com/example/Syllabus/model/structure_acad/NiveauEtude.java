package com.example.Syllabus.model.structure_acad;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "niveau_etude")
public class NiveauEtude {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "filiere_id", nullable = false)
    private Filiere filiere;

    @OneToMany(mappedBy = "niveauEtude", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<ComposanteNiveauEtude> composantes = new HashSet<>();
}
