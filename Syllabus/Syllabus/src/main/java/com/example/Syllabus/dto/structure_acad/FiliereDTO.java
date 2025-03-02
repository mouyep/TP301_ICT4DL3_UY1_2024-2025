package com.example.Syllabus.dto.structure_acad;

import lombok.*;
import java.util.Set;
import java.util.HashSet;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FiliereDTO {
    private Long id;
    private String nom;
    private String description;


   // Pour éviter de dupliquer la hiérarchie complète du département, on peut transmettre uniquement l'identifiant du département
    private Long departementId;
    
    // Optionnel : si le besoin existe d'avoir la liste des niveaux d'étude détaillés pour une filière
    @Builder.Default
    private Set<NiveauEtudeDTO> niveaux = new HashSet<>();
}
