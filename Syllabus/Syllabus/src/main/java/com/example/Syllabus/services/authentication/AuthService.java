package com.example.Syllabus.services.authentication;


import java.util.List;

import com.example.Syllabus.dto.authentication.AuthRequestDTO;
import com.example.Syllabus.dto.authentication.AuthResponseDTO;
import com.example.Syllabus.dto.authentication.UserDTO;;

/**
 * Interface définissant le service d'authentification.
 * La méthode authenticate() reçoit une requête d'authentification et retourne une réponse.
 */
public interface AuthService {
    
    AuthResponseDTO authenticate(AuthRequestDTO authRequest);

     /**
     * Récupère la liste des utilisateurs fictifs filtrée par rôle (enseignant ou étudiant).
     *
     * @param role Le rôle des utilisateurs à récupérer (enseignant ou étudiant).
     * @return La liste des utilisateurs fictifs.
     */
    List<UserDTO> getUsersByRole(String role);
}
