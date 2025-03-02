package com.example.Syllabus.dto.structure_acad;


import lombok.*;
import java.util.Set;
import java.util.HashSet;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartementDTO {
    private Long id;
    private String nom;
    private String description;

     // Intégration des filières associées sous forme de DTO imbriqués
     @Builder.Default
     private Set<FiliereDTO> filieres = new HashSet<>();
     
     // Intégration des méthodes d'évaluation associées
     @Builder.Default
     private Set<MethodeEvaluationDTO> methodesEvaluation = new HashSet<>();
}
