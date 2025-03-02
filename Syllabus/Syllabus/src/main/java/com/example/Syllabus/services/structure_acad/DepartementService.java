package com.example.Syllabus.services.structure_acad;


import com.example.Syllabus.dto.structure_acad.DepartementDTO;
import com.example.Syllabus.dto.structure_acad.FiliereDTO;
import com.example.Syllabus.dto.structure_acad.MethodeEvaluationDTO;
import com.example.Syllabus.model.structure_acad.Departement;
import com.example.Syllabus.model.structure_acad.Filiere;
import com.example.Syllabus.model.structure_acad.MethodeEvaluation;
import com.example.Syllabus.repository.structure_acad.DepartementRepository;
import com.example.Syllabus.util.MapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service pour gérer la logique métier associée aux départements.
 * 
 * Ce service orchestre la création, la récupération, la mise à jour et la suppression
 * des départements tout en gérant l'attribution des associations (filieres, methodesEvaluation)
 * via les repositories et en enrichissant les DTO en conséquence.
 */
@Service
public class DepartementService {

    private final DepartementRepository departementRepository;

    public DepartementService(DepartementRepository departementRepository) {
        this.departementRepository = departementRepository;
    }

    /**
     * Crée un nouveau département à partir d'un DTO enrichi.
     * La logique métier ici consiste à associer chaque filière et méthode d'évaluation
     * au département créé.
     *
     * @param dto le DTO contenant les informations du département et ses associations
     * @return le DTO du département créé, enrichi avec ses associations
     */
    @Transactional
    public DepartementDTO createDepartement(DepartementDTO dto) {
        // Convertir le DTO en entité (sans associations enrichies)
        Departement departement = MapperUtil.mapDTOToDepartement(dto);

        // Gestion de l'attribution des filières (si présentes dans le DTO)
        if (dto.getFilieres() != null && !dto.getFilieres().isEmpty()) {
            Set<Filiere> filieres = dto.getFilieres().stream()
                .map((FiliereDTO filiereDTO) -> {
                    // Convertir le DTO de Filiere en entité
                    Filiere filiere = MapperUtil.mapDTOToFiliere(filiereDTO);
                    // Affecter le département à la filière
                    filiere.setDepartement(departement);
                    return filiere;
                }).collect(Collectors.toSet());
            departement.setFilieres(filieres);
        }

        // Gestion de l'attribution des méthodes d'évaluation (si présentes dans le DTO)
        if (dto.getMethodesEvaluation() != null && !dto.getMethodesEvaluation().isEmpty()) {
            Set<MethodeEvaluation> methodes = dto.getMethodesEvaluation().stream()
                .map((MethodeEvaluationDTO meDTO) -> {
                    // Convertir le DTO en entité
                    MethodeEvaluation me = MapperUtil.mapDTOToMethodeEvaluation(meDTO);
                    // Affecter le département à la méthode d'évaluation
                    me.setDepartement(departement);
                    return me;
                }).collect(Collectors.toSet());
            departement.setMethodesEvaluation(methodes);
        }

        // Sauvegarder le département (les opérations en cascade gèrent les associations)
        Departement saved = departementRepository.save(departement);
        // Convertir l'entité sauvegardée en DTO enrichi et le retourner
        return MapperUtil.mapDepartementToDTO(saved);
    }

    /**
     * Récupère un département par son identifiant.
     *
     * @param id l'identifiant du département
     * @return le DTO enrichi du département ou null s'il n'existe pas
     */
    @Transactional(readOnly = true)
    public DepartementDTO getDepartementById(Long id) {
        Optional<Departement> optional = departementRepository.findById(id);
        return optional.map(MapperUtil::mapDepartementToDTO).orElse(null);
    }

    /**
     * Récupère la liste de tous les départements.
     *
     * @return une liste de DTO enrichis des départements
     */
    @Transactional(readOnly = true)
    public List<DepartementDTO> getAllDepartements() {
        List<Departement> departements = departementRepository.findAll();
        return departements.stream()
                .map(MapperUtil::mapDepartementToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Met à jour un département existant.
     * Ici, seule la mise à jour des attributs simples est démontrée.
     * La gestion des associations (filieres, methodesEvaluation) peut être étendue selon les besoins.
     *
     * @param id  l'identifiant du département à mettre à jour
     * @param dto le DTO contenant les nouvelles informations
     * @return le DTO du département mis à jour ou null si le département n'existe pas
     */
    @Transactional
    public DepartementDTO updateDepartement(Long id, DepartementDTO dto) {
        Optional<Departement> optional = departementRepository.findById(id);
        if (optional.isPresent()) {
            Departement existing = optional.get();
            existing.setNom(dto.getNom());
            existing.setDescription(dto.getDescription());
            // Mise à jour des associations si nécessaire (à implémenter selon la logique métier)
            Departement updated = departementRepository.save(existing);
            return MapperUtil.mapDepartementToDTO(updated);
        }
        return null; // Ou lever une exception personnalisée ResourceNotFoundException
    }

    /**
     * Supprime un département par son identifiant.
     *
     * @param id l'identifiant du département à supprimer
     */
    @Transactional
    public void deleteDepartement(Long id) {
        departementRepository.deleteById(id);
    }
}
