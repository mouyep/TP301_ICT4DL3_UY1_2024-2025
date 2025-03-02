package com.example.Syllabus.services.fiche_acad;


import com.example.Syllabus.dto.fiche_acad.FicheAcademiqueDTO;
import com.example.Syllabus.model.fiche_acad.FicheAcademique;
import com.example.Syllabus.model.contenu_acad.UniteEnseignement;
import com.example.Syllabus.repository.fiche_acad.FicheAcademiqueRepository;
import com.example.Syllabus.repository.contenu_acad.UniteEnseignementRepository;
import com.example.Syllabus.util.MapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class FicheAcademiqueService {

    private final FicheAcademiqueRepository ficheAcademiqueRepository;
    private final UniteEnseignementRepository uniteEnseignementRepository;

    public FicheAcademiqueService(FicheAcademiqueRepository ficheAcademiqueRepository,
                                  UniteEnseignementRepository uniteEnseignementRepository) {
        this.ficheAcademiqueRepository = ficheAcademiqueRepository;
        this.uniteEnseignementRepository = uniteEnseignementRepository;
    }

    /**
     * Crée une nouvelle fiche académique à partir d'un DTO enrichi.
     * La logique métier consiste à :
     * - Mapper le DTO en entité.
     * - Récupérer et affecter les unités d’enseignement associées via leur identifiant.
     * - Sauvegarder l'entité en cascade.
     *
     * @param dto le DTO contenant les informations de la fiche académique et ses associations.
     * @return le DTO enrichi de la fiche académique créée.
     */
    @Transactional
    public FicheAcademiqueDTO createFicheAcademique(FicheAcademiqueDTO dto) {
        // Conversion du DTO en entité (sans gestion des associations)
        FicheAcademique fiche = MapperUtil.mapDTOToFicheAcademique(dto);

        // Gestion de l'association many-to-many avec les Unités d'Enseignement
        if (dto.getUniteEnseignements() != null && !dto.getUniteEnseignements().isEmpty()) {
            Set<UniteEnseignement> unites = dto.getUniteEnseignements().stream()
                    .map(ueDTO -> {
                        if (ueDTO.getId() != null) {
                            Optional<UniteEnseignement> ueOpt = uniteEnseignementRepository.findById(ueDTO.getId());
                            if (ueOpt.isPresent()) {
                                return ueOpt.get();
                            } else {
                                throw new RuntimeException("UniteEnseignement non trouvée pour l'id: " + ueDTO.getId());
                            }
                        } else {
                            // En cas d'absence d'identifiant, on mappe le DTO en entité (attention à la cohérence des données)
                            return MapperUtil.mapDTOToUniteEnseignement(ueDTO);
                        }
                    })
                    .collect(Collectors.toSet());
            fiche.setUniteEnseignements(unites);
        }

        FicheAcademique saved = ficheAcademiqueRepository.save(fiche);
        return MapperUtil.mapFicheAcademiqueToDTO(saved);
    }

    /**
     * Récupère une fiche académique par son identifiant.
     *
     * @param id l'identifiant de la fiche académique.
     * @return le DTO enrichi de la fiche académique ou null si non trouvée.
     */
    @Transactional(readOnly = true)
    public FicheAcademiqueDTO getFicheAcademiqueById(Long id) {
        Optional<FicheAcademique> optional = ficheAcademiqueRepository.findById(id);
        return optional.map(MapperUtil::mapFicheAcademiqueToDTO).orElse(null);
    }

    /**
     * Récupère la liste de toutes les fiches académiques.
     *
     * @return une liste de DTO enrichis représentant les fiches académiques.
     */
    @Transactional(readOnly = true)
    public List<FicheAcademiqueDTO> getAllFicheAcademiques() {
        List<FicheAcademique> fiches = ficheAcademiqueRepository.findAll();
        return fiches.stream()
                .map(MapperUtil::mapFicheAcademiqueToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Met à jour une fiche académique existante.
     * La mise à jour inclut la modification des attributs simples ainsi que la réaffectation des unités d’enseignement associées.
     *
     * @param id  l'identifiant de la fiche académique à mettre à jour.
     * @param dto le DTO contenant les nouvelles informations.
     * @return le DTO de la fiche académique mise à jour, ou null si la fiche n'existe pas.
     */
    @Transactional
    public FicheAcademiqueDTO updateFicheAcademique(Long id, FicheAcademiqueDTO dto) {
        Optional<FicheAcademique> optional = ficheAcademiqueRepository.findById(id);
        if (optional.isPresent()) {
            FicheAcademique existing = optional.get();
            // Mise à jour des attributs simples
            existing.setEtudiantIdentifier(dto.getEtudiantIdentifier());
            existing.setDateCreation(dto.getDateCreation());
            
            // Mise à jour de l'association many-to-many avec les Unités d'Enseignement
            if (dto.getUniteEnseignements() != null) {
                Set<UniteEnseignement> unites = dto.getUniteEnseignements().stream()
                        .map(ueDTO -> {
                            if (ueDTO.getId() != null) {
                                Optional<UniteEnseignement> ueOpt = uniteEnseignementRepository.findById(ueDTO.getId());
                                if (ueOpt.isPresent()) {
                                    return ueOpt.get();
                                } else {
                                    throw new RuntimeException("UniteEnseignement non trouvée pour l'id: " + ueDTO.getId());
                                }
                            } else {
                                return MapperUtil.mapDTOToUniteEnseignement(ueDTO);
                            }
                        })
                        .collect(Collectors.toSet());
                existing.setUniteEnseignements(unites);
            }
            
            FicheAcademique updated = ficheAcademiqueRepository.save(existing);
            return MapperUtil.mapFicheAcademiqueToDTO(updated);
        }
        return null;
    }

    /**
     * Supprime une fiche académique par son identifiant.
     *
     * @param id l'identifiant de la fiche académique à supprimer.
     */
    @Transactional
    public void deleteFicheAcademique(Long id) {
        ficheAcademiqueRepository.deleteById(id);
    }
}

