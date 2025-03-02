package com.example.Syllabus.dto.structure_acad;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComposanteNiveauEtudeDTO {
    private Long id;
    private String nom;
    // On stocke l'identifiant du niveau d'étude auquel cette composante appartient
    private Long niveauEtudeId;
}

