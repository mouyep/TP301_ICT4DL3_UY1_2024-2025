package com.example.Syllabus.dto.fiche_acad;


import lombok.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.HashSet;

import com.example.Syllabus.dto.contenu_acad.UniteEnseignementDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FicheAcademiqueDTO {
    private Long id;
    private String etudiantIdentifier;
    private LocalDate dateCreation;
    // Optionnel : Ensemble des identifiants des unités d'enseignement associées
    private Set<Long> uniteEnseignementIds;


    // Pour offrir une vue détaillée, on intègre ici les unités d'enseignement complètes
    @Builder.Default
    private Set<UniteEnseignementDTO> uniteEnseignements = new HashSet<>();
}

