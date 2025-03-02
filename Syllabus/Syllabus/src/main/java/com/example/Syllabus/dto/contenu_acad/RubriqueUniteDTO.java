package com.example.Syllabus.dto.contenu_acad;


import lombok.*;
import java.util.Set;
import java.util.HashSet;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RubriqueUniteDTO {
    private Long id;
    private String nom;
    
    
    // Intégration des syllabus associés à cette rubrique
    @Builder.Default
    private Set<SyllabusRubriqueDTO> syllabusRubriques = new HashSet<>();
    
    // On peut également inclure l'identifiant de l'unité d'enseignement si besoin
    private Long uniteEnseignementId;
}
