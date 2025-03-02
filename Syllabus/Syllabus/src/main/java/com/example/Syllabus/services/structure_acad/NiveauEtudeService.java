package com.example.Syllabus.services.structure_acad;


import com.example.Syllabus.dto.structure_acad.NiveauEtudeDTO;
import com.example.Syllabus.model.structure_acad.Filiere;
import com.example.Syllabus.model.structure_acad.NiveauEtude;
import com.example.Syllabus.repository.structure_acad.NiveauEtudeRepository;
import com.example.Syllabus.repository.structure_acad.FiliereRepository;
import com.example.Syllabus.util.MapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service dédié à la gestion des niveaux d'étude.
 * 
 * Ce service orchestre la création, la récupération, la mise à jour et la suppression
 * des niveaux d'étude, en assurant l'attribution de la filière correspondante via le repository.
 * La logique métier relative aux associations est gérée ici, et le MapperUtil se charge uniquement du mapping.
 */
@Service
public class NiveauEtudeService {

    private final NiveauEtudeRepository niveauEtudeRepository;
    private final FiliereRepository filiereRepository;

    public NiveauEtudeService(NiveauEtudeRepository niveauEtudeRepository, FiliereRepository filiereRepository) {
        this.niveauEtudeRepository = niveauEtudeRepository;
        this.filiereRepository = filiereRepository;
    }

    /**
     * Crée un nouveau niveau d'étude à partir d'un DTO enrichi.
     * La logique métier consiste ici à associer le niveau à la filière correspondant.
     *
     * @param dto Le DTO contenant les informations du niveau d'étude et l'identifiant de la filière associée.
     * @return Le DTO du niveau d'étude créé, enrichi avec ses associations.
     */
    @Transactional
    public NiveauEtudeDTO createNiveauEtude(NiveauEtudeDTO dto) {
        // Conversion du DTO en entité
        NiveauEtude niveau = MapperUtil.mapDTOToNiveauEtude(dto);

        // Attribution de la filière
        if (dto.getFiliereId() != null) {
            Optional<Filiere> filiereOpt = filiereRepository.findById(dto.getFiliereId());
            if (filiereOpt.isPresent()) {
                niveau.setFiliere(filiereOpt.get());
            } else {
                throw new RuntimeException("Filière non trouvée pour l'identifiant : " + dto.getFiliereId());
            }
        }

        // Sauvegarde du niveau d'étude (les associations en cascade sont gérées par JPA)
        NiveauEtude saved = niveauEtudeRepository.save(niveau);
        return MapperUtil.mapNiveauEtudeToDTO(saved);
    }

    /**
     * Récupère un niveau d'étude par son identifiant.
     *
     * @param id L'identifiant du niveau d'étude.
     * @return Le DTO enrichi du niveau d'étude, ou null s'il n'existe pas.
     */
    @Transactional(readOnly = true)
    public NiveauEtudeDTO getNiveauEtudeById(Long id) {
        Optional<NiveauEtude> optional = niveauEtudeRepository.findById(id);
        return optional.map(MapperUtil::mapNiveauEtudeToDTO).orElse(null);
    }

    /**
     * Récupère la liste de tous les niveaux d'étude.
     *
     * @return Une liste de DTO enrichis représentant les niveaux d'étude.
     */
    @Transactional(readOnly = true)
    public List<NiveauEtudeDTO> getAllNiveauEtudes() {
        List<NiveauEtude> niveaux = niveauEtudeRepository.findAll();
        return niveaux.stream()
                .map(MapperUtil::mapNiveauEtudeToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Met à jour un niveau d'étude existant.
     * Seules les informations simples sont mises à jour ici.
     * La gestion des associations (comme l'attribution de la filière) est effectuée via le repository.
     *
     * @param id  L'identifiant du niveau d'étude à mettre à jour.
     * @param dto Le DTO contenant les nouvelles informations.
     * @return Le DTO du niveau d'étude mis à jour, ou null si le niveau n'existe pas.
     */
    @Transactional
    public NiveauEtudeDTO updateNiveauEtude(Long id, NiveauEtudeDTO dto) {
        Optional<NiveauEtude> optional = niveauEtudeRepository.findById(id);
        if (optional.isPresent()) {
            NiveauEtude existing = optional.get();
            existing.setNom(dto.getNom());
            existing.setDescription(dto.getDescription());

            // Mise à jour de l'association avec la filière si un identifiant est fourni
            if (dto.getFiliereId() != null) {
                Optional<Filiere> filiereOpt = filiereRepository.findById(dto.getFiliereId());
                if (filiereOpt.isPresent()) {
                    existing.setFiliere(filiereOpt.get());
                } else {
                    throw new RuntimeException("Filière non trouvée pour l'identifiant : " + dto.getFiliereId());
                }
            }

            NiveauEtude updated = niveauEtudeRepository.save(existing);
            return MapperUtil.mapNiveauEtudeToDTO(updated);
        }
        return null;
    }

    /**
     * Supprime un niveau d'étude par son identifiant.
     *
     * @param id L'identifiant du niveau d'étude à supprimer.
     */
    @Transactional
    public void deleteNiveauEtude(Long id) {
        niveauEtudeRepository.deleteById(id);
    }
}

