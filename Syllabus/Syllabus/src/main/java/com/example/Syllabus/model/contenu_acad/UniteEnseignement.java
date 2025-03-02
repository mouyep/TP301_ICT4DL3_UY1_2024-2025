package com.example.Syllabus.model.contenu_acad;

import com.example.Syllabus.model.structure_acad.ComposanteNiveauEtude;
import com.example.Syllabus.model.structure_acad.MethodeEvaluation;
import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "unite_enseignement")
public class UniteEnseignement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "composante_niveau_etude", nullable = false)
    private ComposanteNiveauEtude composanteNiveauEtude;

    // Une unité d'enseignement contient plusieurs rubriques
    @OneToMany(mappedBy = "uniteEnseignement", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<RubriqueUnite> rubriques = new HashSet<>();

    // Relation many-to-many avec Méthode d'évaluation
    @ManyToMany
    @JoinTable(
        name = "UniteEnseignement_MethodeEvaluation",
        joinColumns = @JoinColumn(name = "unite_enseignement_id"),
        inverseJoinColumns = @JoinColumn(name = "methode_evaluation_id")
    )
    @Builder.Default
    private Set<MethodeEvaluation> methodesEvaluation = new HashSet<>();
}
