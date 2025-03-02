package com.example.Syllabus.controller.structure_acad;


import com.example.Syllabus.dto.structure_acad.NiveauEtudeDTO;
import com.example.Syllabus.services.structure_acad.NiveauEtudeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur pour la gestion des niveaux d'étude.
 * 
 * Expose des endpoints pour créer, récupérer, mettre à jour et supprimer des niveaux d'étude.
 */
@RestController
@RequestMapping("/api/niveau-etudes")
public class NiveauEtudeController {

    private final NiveauEtudeService niveauEtudeService;

    @Autowired
    public NiveauEtudeController(NiveauEtudeService niveauEtudeService) {
        this.niveauEtudeService = niveauEtudeService;
    }

    /**
     * Crée un nouveau niveau d'étude.
     *
     * @param dto Le DTO contenant les informations du niveau d'étude à créer, y compris l'identifiant de la filière associée.
     * @return Le DTO enrichi du niveau d'étude créé.
     */
    @PostMapping
    public NiveauEtudeDTO createNiveauEtude(@RequestBody NiveauEtudeDTO dto) {
        return niveauEtudeService.createNiveauEtude(dto);
    }

    /**
     * Récupère un niveau d'étude par son identifiant.
     *
     * @param id L'identifiant du niveau d'étude.
     * @return Le DTO enrichi du niveau d'étude ou null s'il n'existe pas.
     */
    @GetMapping("/{id}")
    public NiveauEtudeDTO getNiveauEtudeById(@PathVariable Long id) {
        return niveauEtudeService.getNiveauEtudeById(id);
    }

    /**
     * Récupère la liste de tous les niveaux d'étude.
     *
     * @return Une liste de DTO enrichis représentant les niveaux d'étude.
     */
    @GetMapping
    public List<NiveauEtudeDTO> getAllNiveauEtudes() {
        return niveauEtudeService.getAllNiveauEtudes();
    }

    /**
     * Met à jour un niveau d'étude existant.
     *
     * @param id  L'identifiant du niveau d'étude à mettre à jour.
     * @param dto Le DTO contenant les nouvelles informations.
     * @return Le DTO du niveau d'étude mis à jour ou null si le niveau n'existe pas.
     */
    @PutMapping("/{id}")
    public NiveauEtudeDTO updateNiveauEtude(@PathVariable Long id, @RequestBody NiveauEtudeDTO dto) {
        return niveauEtudeService.updateNiveauEtude(id, dto);
    }

    /**
     * Supprime un niveau d'étude par son identifiant.
     *
     * @param id L'identifiant du niveau d'étude à supprimer.
     */
    @DeleteMapping("/{id}")
    public void deleteNiveauEtude(@PathVariable Long id) {
        niveauEtudeService.deleteNiveauEtude(id);
    }
}

