package com.example.Syllabus.services.structure_acad;


import com.example.Syllabus.dto.structure_acad.FiliereDTO;
import com.example.Syllabus.model.structure_acad.Departement;
import com.example.Syllabus.model.structure_acad.Filiere;
import com.example.Syllabus.repository.structure_acad.DepartementRepository;
import com.example.Syllabus.repository.structure_acad.FiliereRepository;
import com.example.Syllabus.util.MapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service dédié à la gestion des filières.
 * 
 * Ce service orchestre la création, la récupération, la mise à jour et la suppression
 * des filières, tout en gérant l'attribution du département via le DepartementRepository.
 */
@Service
public class FiliereService {

    private final FiliereRepository filiereRepository;
    private final DepartementRepository departementRepository;

    public FiliereService(FiliereRepository filiereRepository, DepartementRepository departementRepository) {
        this.filiereRepository = filiereRepository;
        this.departementRepository = departementRepository;
    }

    /**
     * Crée une nouvelle filière à partir d'un DTO enrichi.
     * La logique métier consiste ici à associer la filière au département correspondant.
     *
     * @param dto Le DTO contenant les informations de la filière et l'identifiant du département associé.
     * @return Le DTO de la filière créée, enrichi avec ses associations.
     */
    @Transactional
    public FiliereDTO createFiliere(FiliereDTO dto) {
        // Convertir le DTO en entité (sans associations enrichies)
        Filiere filiere = MapperUtil.mapDTOToFiliere(dto);

        // Attribution du département à la filière
        if (dto.getDepartementId() != null) {
            Optional<Departement> deptOpt = departementRepository.findById(dto.getDepartementId());
            if (deptOpt.isPresent()) {
                filiere.setDepartement(deptOpt.get());
            } else {
                throw new RuntimeException("Département non trouvé pour l'identifiant : " + dto.getDepartementId());
            }
        }

        // La gestion de la collection des niveaux d'étude peut être ajoutée ici si nécessaire

        Filiere saved = filiereRepository.save(filiere);
        return MapperUtil.mapFiliereToDTO(saved);
    }

    /**
     * Récupère une filière par son identifiant.
     *
     * @param id L'identifiant de la filière.
     * @return Le DTO enrichi de la filière ou null si elle n'existe pas.
     */
    @Transactional(readOnly = true)
    public FiliereDTO getFiliereById(Long id) {
        Optional<Filiere> optional = filiereRepository.findById(id);
        return optional.map(MapperUtil::mapFiliereToDTO).orElse(null);
    }

    /**
     * Récupère la liste de toutes les filières.
     *
     * @return Une liste de DTO enrichis des filières.
     */
    @Transactional(readOnly = true)
    public List<FiliereDTO> getAllFilieres() {
        List<Filiere> filieres = filiereRepository.findAll();
        return filieres.stream()
                .map(MapperUtil::mapFiliereToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Met à jour une filière existante.
     * Seules les informations simples sont mises à jour ici.
     * La gestion des associations (comme la mise à jour du département ou des niveaux d'étude)
     * peut être étendue selon les besoins.
     *
     * @param id  L'identifiant de la filière à mettre à jour.
     * @param dto Le DTO contenant les nouvelles informations.
     * @return Le DTO de la filière mise à jour ou null si la filière n'existe pas.
     */
    @Transactional
    public FiliereDTO updateFiliere(Long id, FiliereDTO dto) {
        Optional<Filiere> optional = filiereRepository.findById(id);
        if (optional.isPresent()) {
            Filiere existing = optional.get();
            existing.setNom(dto.getNom());
            existing.setDescription(dto.getDescription());

            // Mise à jour de l'association avec le département si un identifiant est fourni
            if (dto.getDepartementId() != null) {
                Optional<Departement> deptOpt = departementRepository.findById(dto.getDepartementId());
                if (deptOpt.isPresent()) {
                    existing.setDepartement(deptOpt.get());
                } else {
                    throw new RuntimeException("Département non trouvé pour l'identifiant : " + dto.getDepartementId());
                }
            }

            // La gestion des niveaux d'étude pourra être ajoutée ici

            Filiere updated = filiereRepository.save(existing);
            return MapperUtil.mapFiliereToDTO(updated);
        }
        return null;
    }

    /**
     * Supprime une filière par son identifiant.
     *
     * @param id L'identifiant de la filière à supprimer.
     */
    @Transactional
    public void deleteFiliere(Long id) {
        filiereRepository.deleteById(id);
    }
}

