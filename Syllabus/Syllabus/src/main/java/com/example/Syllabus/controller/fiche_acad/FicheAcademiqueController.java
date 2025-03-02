package com.example.Syllabus.controller.fiche_acad;


import com.example.Syllabus.dto.fiche_acad.FicheAcademiqueDTO;
import com.example.Syllabus.services.fiche_acad.FicheAcademiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur pour la gestion des fiches académiques.
 * Il expose les endpoints pour créer, récupérer, mettre à jour et supprimer une fiche académique.
 */
@RestController
@RequestMapping("/api/fiche-academique")
public class FicheAcademiqueController {

    private final FicheAcademiqueService ficheAcademiqueService;

    @Autowired
    public FicheAcademiqueController(FicheAcademiqueService ficheAcademiqueService) {
        this.ficheAcademiqueService = ficheAcademiqueService;
    }

    /**
     * Crée une nouvelle fiche académique.
     *
     * @param dto Le DTO de la fiche académique à créer.
     * @return Le DTO de la fiche académique créée.
     */
    @PostMapping
    public FicheAcademiqueDTO createFicheAcademique(@RequestBody FicheAcademiqueDTO dto) {
        return ficheAcademiqueService.createFicheAcademique(dto);
    }

    /**
     * Récupère une fiche académique par son identifiant.
     *
     * @param id L'identifiant de la fiche académique.
     * @return Le DTO enrichi de la fiche académique ou null si non trouvée.
     */
    @GetMapping("/{id}")
    public FicheAcademiqueDTO getFicheAcademiqueById(@PathVariable Long id) {
        return ficheAcademiqueService.getFicheAcademiqueById(id);
    }

    /**
     * Récupère la liste de toutes les fiches académiques.
     *
     * @return Une liste de DTO enrichis représentant les fiches académiques.
     */
    @GetMapping
    public List<FicheAcademiqueDTO> getAllFicheAcademiques() {
        return ficheAcademiqueService.getAllFicheAcademiques();
    }

    /**
     * Met à jour une fiche académique existante.
     *
     * @param id  L'identifiant de la fiche académique à mettre à jour.
     * @param dto Le DTO contenant les nouvelles informations.
     * @return Le DTO de la fiche académique mise à jour ou null si la fiche n'existe pas.
     */
    @PutMapping("/{id}")
    public FicheAcademiqueDTO updateFicheAcademique(@PathVariable Long id, @RequestBody FicheAcademiqueDTO dto) {
        return ficheAcademiqueService.updateFicheAcademique(id, dto);
    }

    /**
     * Supprime une fiche académique par son identifiant.
     *
     * @param id L'identifiant de la fiche académique à supprimer.
     */
    @DeleteMapping("/{id}")
    public void deleteFicheAcademique(@PathVariable Long id) {
        ficheAcademiqueService.deleteFicheAcademique(id);
    }
}

