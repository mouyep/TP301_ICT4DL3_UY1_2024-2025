package com.example.Syllabus.controller.structure_acad;


import com.example.Syllabus.dto.structure_acad.DepartementDTO;
import com.example.Syllabus.services.structure_acad.DepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur pour la gestion des départements.
 * 
 * Expose des endpoints pour créer, récupérer, mettre à jour et supprimer des départements.
 */
@RestController
@RequestMapping("/api/departements")
public class DepartementController {

    private final DepartementService departementService;

    @Autowired
    public DepartementController(DepartementService departementService) {
        this.departementService = departementService;
    }

    /**
     * Crée un nouveau département.
     *
     * @param dto le DTO contenant les informations du département à créer, y compris ses associations (filières et méthodes d'évaluation)
     * @return le DTO enrichi du département créé
     */
    @PostMapping
    public DepartementDTO createDepartement(@RequestBody DepartementDTO dto) {
        return departementService.createDepartement(dto);
    }

    /**
     * Récupère un département par son identifiant.
     *
     * @param id l'identifiant du département
     * @return le DTO enrichi du département ou null s'il n'existe pas
     */
    @GetMapping("/{id}")
    public DepartementDTO getDepartementById(@PathVariable Long id) {
        return departementService.getDepartementById(id);
    }

    /**
     * Récupère la liste de tous les départements.
     *
     * @return une liste de DTO enrichis des départements
     */
    @GetMapping
    public List<DepartementDTO> getAllDepartements() {
        return departementService.getAllDepartements();
    }

    /**
     * Met à jour un département existant.
     *
     * @param id  l'identifiant du département à mettre à jour
     * @param dto le DTO contenant les nouvelles informations
     * @return le DTO du département mis à jour ou null si le département n'existe pas
     */
    @PutMapping("/{id}")
    public DepartementDTO updateDepartement(@PathVariable Long id, @RequestBody DepartementDTO dto) {
        return departementService.updateDepartement(id, dto);
    }

    /**
     * Supprime un département par son identifiant.
     *
     * @param id l'identifiant du département à supprimer
     */
    @DeleteMapping("/{id}")
    public void deleteDepartement(@PathVariable Long id) {
        departementService.deleteDepartement(id);
    }
}

