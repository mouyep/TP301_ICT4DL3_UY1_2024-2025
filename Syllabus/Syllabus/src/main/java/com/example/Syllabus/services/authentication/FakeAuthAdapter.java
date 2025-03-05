package com.example.Syllabus.services.authentication;


import com.example.Syllabus.dto.authentication.AuthRequestDTO;
import com.example.Syllabus.dto.authentication.AuthResponseDTO;
import com.example.Syllabus.dto.authentication.UserDTO;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Implémentation simulée d'AuthService.
 * Cette classe utilise des données fictives pour authentifier un utilisateur en fonction de son matricule et de son rôle.
 */
@Service
public class FakeAuthAdapter implements AuthService {

    private static final Map<String, String> FAKE_STUDENTS = Map.of(
        "STU123", "E12345",
        "STU456", "E45678"
    );

    private static final Map<String, String> FAKE_TEACHERS = Map.of(
        "TEA789", "T78901",
        "TEA012", "T01234"
    );

    @Override
    public AuthResponseDTO authenticate(AuthRequestDTO authRequest) {
        String identifier = null;
        if ("ETUDIANT".equalsIgnoreCase(authRequest.getRole())) {
            identifier = FAKE_STUDENTS.get(authRequest.getMatricule());
        } else if ("ENSEIGNANT".equalsIgnoreCase(authRequest.getRole()) 
                || "ADMIN".equalsIgnoreCase(authRequest.getRole())) {
            identifier = FAKE_TEACHERS.get(authRequest.getMatricule());
        }
        
        if (identifier != null) {
            return AuthResponseDTO.builder()
                    .valid(true)
                    .userIdentifier(identifier)
                    .message("Authentification réussie")
                    .build();
        } else {
            return AuthResponseDTO.builder()
                    .valid(false)
                    .userIdentifier(null)
                    .message("Matricule ou rôle invalide")
                    .build();
        }
    }



    @Override
    public List<UserDTO> getUsersByRole(String role) {
        List<UserDTO> users = new ArrayList<>();

        if ("enseignant".equals(role)) {
            users.add(new UserDTO(1L, "Professeur John"));
            users.add(new UserDTO(2L, "Professeur Jane"));
        } else if ("etudiant".equals(role)) {
            users.add(new UserDTO(3L, "Étudiant Alice"));
            users.add(new UserDTO(4L, "Étudiant Bob"));
        }

        return users;
    }

}

