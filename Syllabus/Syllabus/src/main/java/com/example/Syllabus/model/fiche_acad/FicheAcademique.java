package com.example.Syllabus.model.fiche_acad;


import com.example.Syllabus.model.contenu_acad.UniteEnseignement;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "fiche_academique")
public class FicheAcademique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "etudiant_identifier", nullable = false)
    private String etudiantIdentifier;

    @Column(name = "date_creation", nullable = false)
    private LocalDate dateCreation;

    // Association many-to-many avec UniteEnseignement
    @ManyToMany
    @JoinTable(
        name = "FicheAcademique_Unite",
        joinColumns = @JoinColumn(name = "fiche_academique_id"),
        inverseJoinColumns = @JoinColumn(name = "unite_enseignement_id")
    )
    @Builder.Default
    private Set<UniteEnseignement> uniteEnseignements = new HashSet<>();
}

