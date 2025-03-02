package com.example.Syllabus.dto.structure_acad;

import lombok.*;
import java.util.Set;
import java.util.HashSet;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NiveauEtudeDTO {
    private Long id;
    private String nom;
    private String description;

    // Pour référence, on peut inclure l'identifiant de la filière
    private Long filiereId;
    
    // Intégration des composantes (ex : semestres ou trimestres) détaillées
    @Builder.Default
    private Set<ComposanteNiveauEtudeDTO> composantes = new HashSet<>();
}

