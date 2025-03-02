package com.example.Syllabus.dto.contenu_acad;


import lombok.*;
import java.util.Set;

import com.example.Syllabus.dto.structure_acad.ComposanteNiveauEtudeDTO;
import com.example.Syllabus.dto.structure_acad.MethodeEvaluationDTO;

import java.util.HashSet;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UniteEnseignementDTO {
    private Long id;
    private String nom;
    private String description;

     // Inclusion du DTO de la composante pour fournir plus de détails sur l'appartenance
    private ComposanteNiveauEtudeDTO composanteNiveauEtude;
    
     // Intégration des rubriques associées
    @Builder.Default
    private Set<RubriqueUniteDTO> rubriques = new HashSet<>();
     
     // Optionnel : inclusion des méthodes d'évaluation associées
    @Builder.Default
    private Set<MethodeEvaluationDTO> methodesEvaluation = new HashSet<>();
}
