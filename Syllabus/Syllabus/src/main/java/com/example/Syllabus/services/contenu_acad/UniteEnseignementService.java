package com.example.Syllabus.services.contenu_acad;


// import com.example.Syllabus.dto.structure_acad.MethodeEvaluationDTO;
// import com.example.Syllabus.dto.contenu_acad.UniteEnseignementDTO;
// import com.example.Syllabus.model.contenu_acad.UniteEnseignement;
// import com.example.Syllabus.model.structure_acad.ComposanteNiveauEtude;
// import com.example.Syllabus.model.structure_acad.MethodeEvaluation;
// import com.example.Syllabus.repository.contenu_acad.UniteEnseignementRepository;
// import com.example.Syllabus.repository.structure_acad.ComposanteNiveauEtudeRepository;
// import com.example.Syllabus.repository.structure_acad.MethodeEvaluationRepository;
// import com.example.Syllabus.util.MapperUtil;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

// import java.util.Optional;
// import java.util.Set;
// import java.util.stream.Collectors;
// import java.util.List;

// @Service
// public class UniteEnseignementService {

//     private final UniteEnseignementRepository uniteEnseignementRepository;
//     private final ComposanteNiveauEtudeRepository composanteRepository;
//     private final MethodeEvaluationRepository methodeEvaluationRepository;

//     public UniteEnseignementService(UniteEnseignementRepository uniteEnseignementRepository,
//                                     ComposanteNiveauEtudeRepository composanteRepository,
//                                     MethodeEvaluationRepository methodeEvaluationRepository) {
//         this.uniteEnseignementRepository = uniteEnseignementRepository;
//         this.composanteRepository = composanteRepository;
//         this.methodeEvaluationRepository = methodeEvaluationRepository;
//     }

//     /**
//      * Crée une nouvelle Unité d'Enseignement à partir d'un DTO enrichi.
//      * La logique métier consiste à :
//      * - Mapper le DTO en entité.
//      * - Affecter la ComposanteNiveauEtude correspondante si elle est fournie.
//      * - Convertir et affecter les MéthodeEvaluation associées.
//      *
//      * @param dto Le DTO contenant les informations de l'Unité d'Enseignement.
//      * @return Le DTO enrichi de l'Unité d'Enseignement créée.
//      */
//     @Transactional
//     public UniteEnseignementDTO createUniteEnseignement(UniteEnseignementDTO dto) {
//         // Convertir le DTO en entité (les associations spécifiques seront gérées séparément)
//         UniteEnseignement ue = MapperUtil.mapDTOToUniteEnseignement(dto);

//         // Affectation de la ComposanteNiveauEtude si présente dans le DTO
//         if (dto.getComposanteNiveauEtude() != null && dto.getComposanteNiveauEtude().getId() != null) {
//             Optional<ComposanteNiveauEtude> compOpt = composanteRepository.findById(dto.getComposanteNiveauEtude().getId());
//             if (compOpt.isPresent()) {
//                 ue.setComposanteNiveauEtude(compOpt.get());
//             } else {
//                 throw new RuntimeException("ComposanteNiveauEtude non trouvée pour l'identifiant : " 
//                         + dto.getComposanteNiveauEtude().getId());
//             }
//         }

//         // Gestion de l'association many-to-many avec les MéthodeEvaluation, si fournies
//         if (dto.getMethodesEvaluation() != null && !dto.getMethodesEvaluation().isEmpty()) {
//             Set<MethodeEvaluation> methodes = dto.getMethodesEvaluation().stream()
//                     .map((MethodeEvaluationDTO meDTO) -> {
//                         // Ici, on peut choisir de récupérer l'entité par son id si elle existe déjà,
//                         // ou de mapper directement le DTO en entité.
//                         if (meDTO.getId() != null) {
//                             Optional<MethodeEvaluation> meOpt = methodeEvaluationRepository.findById(meDTO.getId());
//                             if (meOpt.isPresent()) {
//                                 return meOpt.get();
//                             } else {
//                                 throw new RuntimeException("MethodeEvaluation non trouvée pour l'identifiant : " + meDTO.getId());
//                             }
//                         } else {
//                             // Si l'id n'est pas fourni, on crée une nouvelle entité (attention à la cohérence des données)
//                             return MapperUtil.mapDTOToMethodeEvaluation(meDTO);
//                         }
//                     })
//                     .collect(Collectors.toSet());
//             ue.setMethodesEvaluation(methodes);
//         }

//         // Les rubriques pourront être gérées via un endpoint spécifique, 
//         // ici on ne traite pas la création automatique des rubriques lors de la création de l'UE.

//         UniteEnseignement saved = uniteEnseignementRepository.save(ue);
//         return MapperUtil.mapUniteEnseignementToDTO(saved);
//     }

//     /**
//      * Récupère une Unité d'Enseignement par son identifiant.
//      *
//      * @param id L'identifiant de l'Unité d'Enseignement.
//      * @return Le DTO enrichi de l'Unité d'Enseignement, ou null si non trouvée.
//      */
//     @Transactional(readOnly = true)
//     public UniteEnseignementDTO getUniteEnseignementById(Long id) {
//         Optional<UniteEnseignement> optional = uniteEnseignementRepository.findById(id);
//         return optional.map(MapperUtil::mapUniteEnseignementToDTO).orElse(null);
//     }

//     /**
//      * Récupère la liste de toutes les Unités d'Enseignement.
//      *
//      * @return Une liste de DTO enrichis des Unités d'Enseignement.
//      */
//     @Transactional(readOnly = true)
//     public List<UniteEnseignementDTO> getAllUniteEnseignements() {
//         List<UniteEnseignement> unites = uniteEnseignementRepository.findAll();
//         return unites.stream()
//                 .map(MapperUtil::mapUniteEnseignementToDTO)
//                 .collect(Collectors.toList());
//     }

//     /**
//      * Met à jour une Unité d'Enseignement existante.
//      * La mise à jour inclut la modification des attributs simples ainsi que l'attribution de la ComposanteNiveauEtude
//      * et des MéthodeEvaluation associées, si nécessaire.
//      *
//      * @param id  L'identifiant de l'Unité d'Enseignement à mettre à jour.
//      * @param dto Le DTO contenant les nouvelles informations.
//      * @return Le DTO de l'Unité d'Enseignement mise à jour, ou null si non trouvée.
//      */
//     @Transactional
//     public UniteEnseignementDTO updateUniteEnseignement(Long id, UniteEnseignementDTO dto) {
//         Optional<UniteEnseignement> optional = uniteEnseignementRepository.findById(id);
//         if (optional.isPresent()) {
//             UniteEnseignement existing = optional.get();
//             existing.setNom(dto.getNom());
//             existing.setDescription(dto.getDescription());

//             // Mise à jour de la ComposanteNiveauEtude si nécessaire
//             if (dto.getComposanteNiveauEtude() != null && dto.getComposanteNiveauEtude().getId() != null) {
//                 Optional<ComposanteNiveauEtude> compOpt = composanteRepository.findById(dto.getComposanteNiveauEtude().getId());
//                 if (compOpt.isPresent()) {
//                     existing.setComposanteNiveauEtude(compOpt.get());
//                 } else {
//                     throw new RuntimeException("ComposanteNiveauEtude non trouvée pour l'identifiant : " 
//                         + dto.getComposanteNiveauEtude().getId());
//                 }
//             }

//             // Mise à jour de la collection des MéthodeEvaluation si fournie
//             if (dto.getMethodesEvaluation() != null) {
//                 Set<MethodeEvaluation> methodes = dto.getMethodesEvaluation().stream()
//                         .map((MethodeEvaluationDTO meDTO) -> {
//                             if (meDTO.getId() != null) {
//                                 Optional<MethodeEvaluation> meOpt = methodeEvaluationRepository.findById(meDTO.getId());
//                                 if (meOpt.isPresent()) {
//                                     return meOpt.get();
//                                 } else {
//                                     throw new RuntimeException("MethodeEvaluation non trouvée pour l'identifiant : " + meDTO.getId());
//                                 }
//                             } else {
//                                 return MapperUtil.mapDTOToMethodeEvaluation(meDTO);
//                             }
//                         })
//                         .collect(Collectors.toSet());
//                 existing.setMethodesEvaluation(methodes);
//             }

//             UniteEnseignement updated = uniteEnseignementRepository.save(existing);
//             return MapperUtil.mapUniteEnseignementToDTO(updated);
//         }
//         return null;
//     }

//     /**
//      * Supprime une Unité d'Enseignement par son identifiant.
//      *
//      * @param id L'identifiant de l'Unité d'Enseignement à supprimer.
//      */
//     @Transactional
//     public void deleteUniteEnseignement(Long id) {
//         uniteEnseignementRepository.deleteById(id);
//     }
// }

import com.example.Syllabus.dto.contenu_acad.RubriqueUniteDTO;
import com.example.Syllabus.dto.contenu_acad.SyllabusRubriqueDTO;
import com.example.Syllabus.dto.contenu_acad.UniteEnseignementDTO;
import com.example.Syllabus.model.contenu_acad.RubriqueUnite;
import com.example.Syllabus.model.contenu_acad.SyllabusRubrique;
import com.example.Syllabus.model.contenu_acad.UniteEnseignement;
import com.example.Syllabus.model.structure_acad.ComposanteNiveauEtude;
import com.example.Syllabus.model.structure_acad.MethodeEvaluation;
import com.example.Syllabus.repository.structure_acad.ComposanteNiveauEtudeRepository;
import com.example.Syllabus.repository.structure_acad.MethodeEvaluationRepository;
import com.example.Syllabus.repository.contenu_acad.RubriqueUniteRepository;
import com.example.Syllabus.repository.contenu_acad.SyllabusRubriqueRepository;
import com.example.Syllabus.repository.contenu_acad.UniteEnseignementRepository;
import com.example.Syllabus.util.MapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class UniteEnseignementService {

    private final UniteEnseignementRepository uniteEnseignementRepository;
    private final ComposanteNiveauEtudeRepository composanteRepository;
    private final MethodeEvaluationRepository methodeEvaluationRepository;
    // Nouveaux repositories pour RubriqueUnite et SyllabusRubrique
    private final RubriqueUniteRepository rubriqueUniteRepository;
    private final SyllabusRubriqueRepository syllabusRubriqueRepository;

    public UniteEnseignementService(UniteEnseignementRepository uniteEnseignementRepository,
                                    ComposanteNiveauEtudeRepository composanteRepository,
                                    MethodeEvaluationRepository methodeEvaluationRepository,
                                    RubriqueUniteRepository rubriqueUniteRepository,
                                    SyllabusRubriqueRepository syllabusRubriqueRepository) {
        this.uniteEnseignementRepository = uniteEnseignementRepository;
        this.composanteRepository = composanteRepository;
        this.methodeEvaluationRepository = methodeEvaluationRepository;
        this.rubriqueUniteRepository = rubriqueUniteRepository;
        this.syllabusRubriqueRepository = syllabusRubriqueRepository;
    }

    // ----------------- Gestion de l'UniteEnseignement -----------------

    @Transactional
    public UniteEnseignementDTO createUniteEnseignement(UniteEnseignementDTO dto) {
        // Mapper le DTO en entité
        UniteEnseignement ue = MapperUtil.mapDTOToUniteEnseignement(dto);

        // Affectation de la ComposanteNiveauEtude si renseignée
        if (dto.getComposanteNiveauEtude() != null && dto.getComposanteNiveauEtude().getId() != null) {
            Optional<ComposanteNiveauEtude> compOpt = composanteRepository.findById(dto.getComposanteNiveauEtude().getId());
            if (compOpt.isPresent()) {
                ue.setComposanteNiveauEtude(compOpt.get());
            } else {
                throw new RuntimeException("ComposanteNiveauEtude non trouvée pour l'identifiant : " 
                        + dto.getComposanteNiveauEtude().getId());
            }
        }

        // Gestion de l'association many-to-many avec les MéthodeEvaluation
        if (dto.getMethodesEvaluation() != null && !dto.getMethodesEvaluation().isEmpty()) {
            Set<MethodeEvaluation> methodes = dto.getMethodesEvaluation().stream()
                    .map(meDTO -> {
                        if (meDTO.getId() != null) {
                            Optional<MethodeEvaluation> meOpt = methodeEvaluationRepository.findById(meDTO.getId());
                            if (meOpt.isPresent()) {
                                return meOpt.get();
                            } else {
                                throw new RuntimeException("MethodeEvaluation non trouvée pour l'identifiant : " + meDTO.getId());
                            }
                        } else {
                            return MapperUtil.mapDTOToMethodeEvaluation(meDTO);
                        }
                    })
                    .collect(Collectors.toSet());
            ue.setMethodesEvaluation(methodes);
        }

        // Sauvegarder l'UE
        UniteEnseignement saved = uniteEnseignementRepository.save(ue);
        return MapperUtil.mapUniteEnseignementToDTO(saved);
    }

    @Transactional(readOnly = true)
    public UniteEnseignementDTO getUniteEnseignementById(Long id) {
        Optional<UniteEnseignement> optional = uniteEnseignementRepository.findById(id);
        return optional.map(MapperUtil::mapUniteEnseignementToDTO).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<UniteEnseignementDTO> getAllUniteEnseignements() {
        List<UniteEnseignement> unites = uniteEnseignementRepository.findAll();
        return unites.stream()
                .map(MapperUtil::mapUniteEnseignementToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public UniteEnseignementDTO updateUniteEnseignement(Long id, UniteEnseignementDTO dto) {
        Optional<UniteEnseignement> optional = uniteEnseignementRepository.findById(id);
        if (optional.isPresent()) {
            UniteEnseignement existing = optional.get();
            existing.setNom(dto.getNom());
            existing.setDescription(dto.getDescription());

            // Mise à jour de la ComposanteNiveauEtude si nécessaire
            if (dto.getComposanteNiveauEtude() != null && dto.getComposanteNiveauEtude().getId() != null) {
                Optional<ComposanteNiveauEtude> compOpt = composanteRepository.findById(dto.getComposanteNiveauEtude().getId());
                if (compOpt.isPresent()) {
                    existing.setComposanteNiveauEtude(compOpt.get());
                } else {
                    throw new RuntimeException("ComposanteNiveauEtude non trouvée pour l'identifiant : " 
                        + dto.getComposanteNiveauEtude().getId());
                }
            }

            // Mise à jour de la collection des MéthodeEvaluation si fournie
            if (dto.getMethodesEvaluation() != null) {
                Set<MethodeEvaluation> methodes = dto.getMethodesEvaluation().stream()
                        .map(meDTO -> {
                            if (meDTO.getId() != null) {
                                Optional<MethodeEvaluation> meOpt = methodeEvaluationRepository.findById(meDTO.getId());
                                if (meOpt.isPresent()) {
                                    return meOpt.get();
                                } else {
                                    throw new RuntimeException("MethodeEvaluation non trouvée pour l'identifiant : " + meDTO.getId());
                                }
                            } else {
                                return MapperUtil.mapDTOToMethodeEvaluation(meDTO);
                            }
                        })
                        .collect(Collectors.toSet());
                existing.setMethodesEvaluation(methodes);
            }

            UniteEnseignement updated = uniteEnseignementRepository.save(existing);
            return MapperUtil.mapUniteEnseignementToDTO(updated);
        }
        return null;
    }

    @Transactional
    public void deleteUniteEnseignement(Long id) {
        uniteEnseignementRepository.deleteById(id);
    }

    // ----------------- Gestion des RubriqueUnite -----------------

    /**
     * Crée une nouvelle RubriqueUnite pour une Unité d'Enseignement donnée.
     *
     * @param uniteEnseignementId l'identifiant de l'UE à laquelle la rubrique est associée.
     * @param dto le DTO de RubriqueUnite contenant les informations nécessaires.
     * @return le DTO enrichi de la RubriqueUnite créée.
     */
    @Transactional
    public RubriqueUniteDTO createRubriqueUnite(Long uniteEnseignementId, RubriqueUniteDTO dto) {
        Optional<UniteEnseignement> ueOpt = uniteEnseignementRepository.findById(uniteEnseignementId);
        if (!ueOpt.isPresent()) {
            throw new RuntimeException("UniteEnseignement non trouvée pour l'id " + uniteEnseignementId);
        }
        UniteEnseignement ue = ueOpt.get();

        RubriqueUnite ru = MapperUtil.mapDTOToRubriqueUnite(dto);
        ru.setUniteEnseignement(ue);
        RubriqueUnite saved = rubriqueUniteRepository.save(ru);
        return MapperUtil.mapRubriqueUniteToDTO(saved);
    }

    @Transactional
    public RubriqueUniteDTO updateRubriqueUnite(Long rubriqueId, RubriqueUniteDTO dto) {
        Optional<RubriqueUnite> ruOpt = rubriqueUniteRepository.findById(rubriqueId);
        if (!ruOpt.isPresent()) {
            throw new RuntimeException("RubriqueUnite non trouvée pour l'id " + rubriqueId);
        }
        RubriqueUnite existing = ruOpt.get();
        existing.setNom(dto.getNom());
        RubriqueUnite updated = rubriqueUniteRepository.save(existing);
        return MapperUtil.mapRubriqueUniteToDTO(updated);
    }

    @Transactional
    public void deleteRubriqueUnite(Long rubriqueId) {
        rubriqueUniteRepository.deleteById(rubriqueId);
    }

    // ----------------- Gestion des SyllabusRubrique -----------------

    /**
     * Crée un nouveau SyllabusRubrique pour une RubriqueUnite donnée.
     *
     * @param rubriqueUniteId l'identifiant de la RubriqueUnite associée.
     * @param dto le DTO de SyllabusRubrique contenant les informations nécessaires.
     * @return le DTO enrichi du SyllabusRubrique créé.
     */
    @Transactional
    public SyllabusRubriqueDTO createSyllabusRubrique(Long rubriqueUniteId, SyllabusRubriqueDTO dto) {
        Optional<RubriqueUnite> ruOpt = rubriqueUniteRepository.findById(rubriqueUniteId);
        if (!ruOpt.isPresent()) {
            throw new RuntimeException("RubriqueUnite non trouvée pour l'id " + rubriqueUniteId);
        }
        RubriqueUnite ru = ruOpt.get();

        SyllabusRubrique sr = MapperUtil.mapDTOToSyllabusRubrique(dto);
        sr.setRubriqueUnite(ru);
        SyllabusRubrique saved = syllabusRubriqueRepository.save(sr);
        return MapperUtil.mapSyllabusRubriqueToDTO(saved);
    }

    @Transactional
    public SyllabusRubriqueDTO updateSyllabusRubrique(Long syllabusId, SyllabusRubriqueDTO dto) {
        Optional<SyllabusRubrique> srOpt = syllabusRubriqueRepository.findById(syllabusId);
        if (!srOpt.isPresent()) {
            throw new RuntimeException("SyllabusRubrique non trouvée pour l'id " + syllabusId);
        }
        SyllabusRubrique existing = srOpt.get();
        existing.setContenu(dto.getContenu());
        existing.setAnneeAcademique(dto.getAnneeAcademique());
        SyllabusRubrique updated = syllabusRubriqueRepository.save(existing);
        return MapperUtil.mapSyllabusRubriqueToDTO(updated);
    }

    @Transactional
    public void deleteSyllabusRubrique(Long syllabusId) {
        syllabusRubriqueRepository.deleteById(syllabusId);
    }
}
