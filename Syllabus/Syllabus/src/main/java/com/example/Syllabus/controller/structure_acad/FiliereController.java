package com.example.Syllabus.controller.structure_acad;


import com.example.Syllabus.dto.structure_acad.FiliereDTO;
import com.example.Syllabus.services.structure_acad.FiliereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur pour la gestion des filières.
 * 
 * Expose des endpoints pour créer, récupérer, mettre à jour et supprimer des filières.
 */
@RestController
@RequestMapping("/api/filieres")
public class FiliereController {

    private final FiliereService filiereService;

    @Autowired
    public FiliereController(FiliereService filiereService) {
        this.filiereService = filiereService;
    }

    /**
     * Crée une nouvelle filière.
     *
     * @param dto Le DTO contenant les informations de la filière à créer, y compris l'identifiant du département associé.
     * @return Le DTO enrichi de la filière créée.
     */
    @PostMapping
    public FiliereDTO createFiliere(@RequestBody FiliereDTO dto) {
        return filiereService.createFiliere(dto);
    }

    /**
     * Récupère une filière par son identifiant.
     *
     * @param id L'identifiant de la filière.
     * @return Le DTO enrichi de la filière ou null si elle n'existe pas.
     */
    @GetMapping("/{id}")
    public FiliereDTO getFiliereById(@PathVariable Long id) {
        return filiereService.getFiliereById(id);
    }

    /**
     * Récupère la liste de toutes les filières.
     *
     * @return Une liste de DTO enrichis représentant les filières.
     */
    @GetMapping
    public List<FiliereDTO> getAllFilieres() {
        return filiereService.getAllFilieres();
    }

    /**
     * Met à jour une filière existante.
     *
     * @param id  L'identifiant de la filière à mettre à jour.
     * @param dto Le DTO contenant les nouvelles informations.
     * @return Le DTO de la filière mise à jour ou null si la filière n'existe pas.
     */
    @PutMapping("/{id}")
    public FiliereDTO updateFiliere(@PathVariable Long id, @RequestBody FiliereDTO dto) {
        return filiereService.updateFiliere(id, dto);
    }

    /**
     * Supprime une filière par son identifiant.
     *
     * @param id L'identifiant de la filière à supprimer.
     */
    @DeleteMapping("/{id}")
    public void deleteFiliere(@PathVariable Long id) {
        filiereService.deleteFiliere(id);
    }
}

