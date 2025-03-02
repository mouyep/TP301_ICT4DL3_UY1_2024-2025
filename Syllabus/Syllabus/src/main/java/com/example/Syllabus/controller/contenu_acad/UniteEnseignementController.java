package com.example.Syllabus.controller.contenu_acad;


import com.example.Syllabus.dto.contenu_acad.UniteEnseignementDTO;
import com.example.Syllabus.dto.contenu_acad.RubriqueUniteDTO;
import com.example.Syllabus.dto.contenu_acad.SyllabusRubriqueDTO;
import com.example.Syllabus.services.contenu_acad.UniteEnseignementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur pour la gestion des Unités d'Enseignement et de leurs éléments associés.
 */
@RestController
@RequestMapping("/api/unite-enseignement")
public class UniteEnseignementController {

    private final UniteEnseignementService uniteEnseignementService;

    @Autowired
    public UniteEnseignementController(UniteEnseignementService uniteEnseignementService) {
        this.uniteEnseignementService = uniteEnseignementService;
    }

    // Endpoints pour la gestion des Unités d'Enseignement

    @PostMapping
    public UniteEnseignementDTO createUniteEnseignement(@RequestBody UniteEnseignementDTO dto) {
        return uniteEnseignementService.createUniteEnseignement(dto);
    }

    @GetMapping("/{id}")
    public UniteEnseignementDTO getUniteEnseignementById(@PathVariable Long id) {
        return uniteEnseignementService.getUniteEnseignementById(id);
    }

    @GetMapping
    public List<UniteEnseignementDTO> getAllUniteEnseignements() {
        return uniteEnseignementService.getAllUniteEnseignements();
    }

    @PutMapping("/{id}")
    public UniteEnseignementDTO updateUniteEnseignement(@PathVariable Long id, @RequestBody UniteEnseignementDTO dto) {
        return uniteEnseignementService.updateUniteEnseignement(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteUniteEnseignement(@PathVariable Long id) {
        uniteEnseignementService.deleteUniteEnseignement(id);
    }

    // Endpoints pour la gestion des RubriqueUnite associées à une Unité d'Enseignement

    @PostMapping("/{uniteEnseignementId}/rubriques")
    public RubriqueUniteDTO createRubriqueUnite(@PathVariable Long uniteEnseignementId,
                                                @RequestBody RubriqueUniteDTO dto) {
        return uniteEnseignementService.createRubriqueUnite(uniteEnseignementId, dto);
    }

    @PutMapping("/rubriques/{rubriqueId}")
    public RubriqueUniteDTO updateRubriqueUnite(@PathVariable Long rubriqueId,
                                                @RequestBody RubriqueUniteDTO dto) {
        return uniteEnseignementService.updateRubriqueUnite(rubriqueId, dto);
    }

    @DeleteMapping("/rubriques/{rubriqueId}")
    public void deleteRubriqueUnite(@PathVariable Long rubriqueId) {
        uniteEnseignementService.deleteRubriqueUnite(rubriqueId);
    }

    // Endpoints pour la gestion des SyllabusRubrique associés à une RubriqueUnite

    @PostMapping("/rubriques/{rubriqueId}/syllabus")
    public SyllabusRubriqueDTO createSyllabusRubrique(@PathVariable Long rubriqueId,
                                                      @RequestBody SyllabusRubriqueDTO dto) {
        return uniteEnseignementService.createSyllabusRubrique(rubriqueId, dto);
    }

    @PutMapping("/syllabus/{syllabusId}")
    public SyllabusRubriqueDTO updateSyllabusRubrique(@PathVariable Long syllabusId,
                                                      @RequestBody SyllabusRubriqueDTO dto) {
        return uniteEnseignementService.updateSyllabusRubrique(syllabusId, dto);
    }

    @DeleteMapping("/syllabus/{syllabusId}")
    public void deleteSyllabusRubrique(@PathVariable Long syllabusId) {
        uniteEnseignementService.deleteSyllabusRubrique(syllabusId);
    }
}

