package com.example.Syllabus.services.authentication;


import com.example.Syllabus.dto.AuthRequestDTO;
import com.example.Syllabus.dto.AuthResponseDTO;

/**
 * Interface définissant le service d'authentification.
 * La méthode authenticate() reçoit une requête d'authentification et retourne une réponse.
 */
public interface AuthService {
    
    AuthResponseDTO authenticate(AuthRequestDTO authRequest);
}
