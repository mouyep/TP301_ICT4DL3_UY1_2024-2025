package com.example.Syllabus.util;

import com.example.Syllabus.dto.contenu_acad.RubriqueUniteDTO;
import com.example.Syllabus.dto.contenu_acad.SyllabusRubriqueDTO;
import com.example.Syllabus.dto.contenu_acad.UniteEnseignementDTO;
import com.example.Syllabus.dto.fiche_acad.FicheAcademiqueDTO;
import com.example.Syllabus.dto.structure_acad.ComposanteNiveauEtudeDTO;
import com.example.Syllabus.dto.structure_acad.DepartementDTO;
import com.example.Syllabus.dto.structure_acad.FiliereDTO;
import com.example.Syllabus.dto.structure_acad.MethodeEvaluationDTO;
import com.example.Syllabus.dto.structure_acad.NiveauEtudeDTO;
import com.example.Syllabus.model.structure_acad.*;
import com.example.Syllabus.model.contenu_acad.UniteEnseignement;
import com.example.Syllabus.model.contenu_acad.RubriqueUnite;
import com.example.Syllabus.model.contenu_acad.SyllabusRubrique;
import com.example.Syllabus.model.fiche_acad.FicheAcademique;
import java.util.stream.Collectors;

public class MapperUtil {

    // ------------------- Mapping Departement -------------------
    public static DepartementDTO mapDepartementToDTO(Departement departement) {
        if (departement == null) return null;
        return DepartementDTO.builder()
                .id(departement.getId())
                .nom(departement.getNom())
                .description(departement.getDescription())
                .filieres(
                    departement.getFilieres().stream()
                        .map(MapperUtil::mapFiliereToDTO)
                        .collect(Collectors.toSet())
                )
                .methodesEvaluation(
                    departement.getMethodesEvaluation().stream()
                        .map(MapperUtil::mapMethodeEvaluationToDTO)
                        .collect(Collectors.toSet())
                )
                .build();
    }

    public static Departement mapDTOToDepartement(DepartementDTO dto) {
        if (dto == null) return null;
        Departement departement = Departement.builder()
                .id(dto.getId())
                .nom(dto.getNom())
                .description(dto.getDescription())
                .build();
        // La gestion des collections (filieres et methodesEvaluation)
        // peut être réalisée ensuite par le service, ou en les mappant récursivement.
        return departement;
    }

    // ------------------- Mapping Filiere -------------------
    public static FiliereDTO mapFiliereToDTO(Filiere filiere) {
        if (filiere == null) return null;
        return FiliereDTO.builder()
                .id(filiere.getId())
                .nom(filiere.getNom())
                .description(filiere.getDescription())
                .departementId(filiere.getDepartement() != null ? filiere.getDepartement().getId() : null)
                .niveaux(
                    filiere.getNiveaux().stream()
                        .map(MapperUtil::mapNiveauEtudeToDTO)
                        .collect(Collectors.toSet())
                )
                .build();
    }

    public static Filiere mapDTOToFiliere(FiliereDTO dto) {
        if (dto == null) return null;
        Filiere filiere = Filiere.builder()
                .id(dto.getId())
                .nom(dto.getNom())
                .description(dto.getDescription())
                // L'attribution du Departement devra être gérée séparément (ex. via un service)
                .build();
        return filiere;
    }

    // ------------------- Mapping NiveauEtude -------------------
    public static NiveauEtudeDTO mapNiveauEtudeToDTO(NiveauEtude niveau) {
        if (niveau == null) return null;
        return NiveauEtudeDTO.builder()
                .id(niveau.getId())
                .nom(niveau.getNom())
                .description(niveau.getDescription())
                .filiereId(niveau.getFiliere() != null ? niveau.getFiliere().getId() : null)
                .composantes(
                    niveau.getComposantes().stream()
                        .map(MapperUtil::mapComposanteNiveauEtudeToDTO)
                        .collect(Collectors.toSet())
                )
                .build();
    }

    public static NiveauEtude mapDTOToNiveauEtude(NiveauEtudeDTO dto) {
        if (dto == null) return null;
        NiveauEtude niveau = NiveauEtude.builder()
                .id(dto.getId())
                .nom(dto.getNom())
                .description(dto.getDescription())
                // L'attribution de la Filiere devra être gérée séparément
                .build();
        return niveau;
    }

    // ------------------- Mapping ComposanteNiveauEtude -------------------
    public static ComposanteNiveauEtudeDTO mapComposanteNiveauEtudeToDTO(ComposanteNiveauEtude comp) {
        if (comp == null) return null;
        return ComposanteNiveauEtudeDTO.builder()
                .id(comp.getId())
                .nom(comp.getNom())
                .niveauEtudeId(comp.getNiveauEtude() != null ? comp.getNiveauEtude().getId() : null)
                .build();
    }

    public static ComposanteNiveauEtude mapDTOToComposanteNiveauEtude(ComposanteNiveauEtudeDTO dto) {
        if (dto == null) return null;
        ComposanteNiveauEtude comp = ComposanteNiveauEtude.builder()
                .id(dto.getId())
                .nom(dto.getNom())
                // L'attribution du NiveauEtude devra être gérée séparément
                .build();
        return comp;
    }

    // ------------------- Mapping MethodeEvaluation -------------------
    public static MethodeEvaluationDTO mapMethodeEvaluationToDTO(MethodeEvaluation me) {
        if (me == null) return null;
        return MethodeEvaluationDTO.builder()
                .id(me.getId())
                .nom(me.getNom())
                .departementId(me.getDepartement() != null ? me.getDepartement().getId() : null)
                .build();
    }

    public static MethodeEvaluation mapDTOToMethodeEvaluation(MethodeEvaluationDTO dto) {
        if (dto == null) return null;
        MethodeEvaluation me = MethodeEvaluation.builder()
                .id(dto.getId())
                .nom(dto.getNom())
                // L'attribution du Departement devra être gérée séparément
                .build();
        return me;
    }

    // ------------------- Mapping UniteEnseignement -------------------
    public static UniteEnseignementDTO mapUniteEnseignementToDTO(UniteEnseignement ue) {
        if (ue == null) return null;
        return UniteEnseignementDTO.builder()
                .id(ue.getId())
                .nom(ue.getNom())
                .description(ue.getDescription())
                .composanteNiveauEtude(
                    ue.getComposanteNiveauEtude() != null ?
                    mapComposanteNiveauEtudeToDTO(ue.getComposanteNiveauEtude()) : null)
                .rubriques(
                    ue.getRubriques().stream()
                        .map(MapperUtil::mapRubriqueUniteToDTO)
                        .collect(Collectors.toSet())
                )
                .methodesEvaluation(
                    ue.getMethodesEvaluation().stream()
                        .map(MapperUtil::mapMethodeEvaluationToDTO)
                        .collect(Collectors.toSet())
                )
                .build();
    }

    public static UniteEnseignement mapDTOToUniteEnseignement(UniteEnseignementDTO dto) {
        if (dto == null) return null;
        UniteEnseignement ue = UniteEnseignement.builder()
                .id(dto.getId())
                .nom(dto.getNom())
                .description(dto.getDescription())
                // L'attribution de la ComposanteNiveauEtude devra être gérée séparément
                .build();
        return ue;
    }

    // ------------------- Mapping RubriqueUnite -------------------
    public static RubriqueUniteDTO mapRubriqueUniteToDTO(RubriqueUnite ru) {
        if (ru == null) return null;
        return RubriqueUniteDTO.builder()
                .id(ru.getId())
                .nom(ru.getNom())
                .uniteEnseignementId(ru.getUniteEnseignement() != null ? ru.getUniteEnseignement().getId() : null)
                .syllabusRubriques(
                    ru.getSyllabusRubriques().stream()
                        .map(MapperUtil::mapSyllabusRubriqueToDTO)
                        .collect(Collectors.toSet())
                )
                .build();
    }

    public static RubriqueUnite mapDTOToRubriqueUnite(RubriqueUniteDTO dto) {
        if (dto == null) return null;
        RubriqueUnite ru = RubriqueUnite.builder()
                .id(dto.getId())
                .nom(dto.getNom())
                // L'attribution de l'UniteEnseignement devra être gérée séparément
                .build();
        return ru;
    }

    // ------------------- Mapping SyllabusRubrique -------------------
    public static SyllabusRubriqueDTO mapSyllabusRubriqueToDTO(SyllabusRubrique sr) {
        if (sr == null) return null;
        return SyllabusRubriqueDTO.builder()
                .id(sr.getId())
                .contenu(sr.getContenu())
                .anneeAcademique(sr.getAnneeAcademique())
                .rubriqueUniteId(sr.getRubriqueUnite() != null ? sr.getRubriqueUnite().getId() : null)
                .build();
    }

    public static SyllabusRubrique mapDTOToSyllabusRubrique(SyllabusRubriqueDTO dto) {
        if (dto == null) return null;
        SyllabusRubrique sr = SyllabusRubrique.builder()
                .id(dto.getId())
                .contenu(dto.getContenu())
                .anneeAcademique(dto.getAnneeAcademique())
                // L'attribution de la RubriqueUnite devra être gérée séparément
                .build();
        return sr;
    }

    // ------------------- Mapping FicheAcademique -------------------
    public static FicheAcademiqueDTO mapFicheAcademiqueToDTO(FicheAcademique fiche) {
        if (fiche == null) return null;
        return FicheAcademiqueDTO.builder()
                .id(fiche.getId())
                .etudiantIdentifier(fiche.getEtudiantIdentifier())
                .dateCreation(fiche.getDateCreation())
                .uniteEnseignements(
                    fiche.getUniteEnseignements().stream()
                        .map(MapperUtil::mapUniteEnseignementToDTO)
                        .collect(Collectors.toSet())
                )
                .build();
    }

    public static FicheAcademique mapDTOToFicheAcademique(FicheAcademiqueDTO dto) {
        if (dto == null) return null;
        FicheAcademique fiche = FicheAcademique.builder()
                .id(dto.getId())
                .etudiantIdentifier(dto.getEtudiantIdentifier())
                .dateCreation(dto.getDateCreation())
                // La gestion des associations (unitesEnseignements) devra être traitée séparément
                .build();
        return fiche;
    }
}
