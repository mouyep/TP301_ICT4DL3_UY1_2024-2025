package com.example.Syllabus.model.contenu_acad;


import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "syllabus_rubrique")
public class SyllabusRubrique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String contenu;

    // Représente l'année académique pour laquelle ce syllabus est valable
    private int anneeAcademique;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rubrique_unite_id", nullable = false)
    private RubriqueUnite rubriqueUnite;
}

