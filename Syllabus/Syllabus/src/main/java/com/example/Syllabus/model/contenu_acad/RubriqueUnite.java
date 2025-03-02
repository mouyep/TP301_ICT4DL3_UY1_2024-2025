package com.example.Syllabus.model.contenu_acad;


import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "rubrique_unite")
public class RubriqueUnite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unite_enseignement_id", nullable = false)
    private UniteEnseignement uniteEnseignement;

    // Une rubrique peut avoir plusieurs syllabus (par année, par exemple)
    @OneToMany(mappedBy = "rubriqueUnite", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<SyllabusRubrique> syllabusRubriques = new HashSet<>();
}
