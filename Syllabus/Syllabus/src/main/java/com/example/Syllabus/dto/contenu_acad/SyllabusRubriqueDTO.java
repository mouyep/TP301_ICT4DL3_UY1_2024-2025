package com.example.Syllabus.dto.contenu_acad;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SyllabusRubriqueDTO {
    private Long id;
    private String contenu;
    private int anneeAcademique;
    // Identifiant de la RubriqueUnite associée à ce syllabus
    private Long rubriqueUniteId;
}
