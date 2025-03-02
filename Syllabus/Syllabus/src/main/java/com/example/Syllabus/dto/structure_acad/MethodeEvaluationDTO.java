package com.example.Syllabus.dto.structure_acad;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MethodeEvaluationDTO {
    private Long id;
    private String nom;
    // Identifiant du département auquel la méthode d'évaluation appartient
    private Long departementId;
}
