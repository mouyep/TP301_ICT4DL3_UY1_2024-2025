package com.example.Syllabus.controller.authentication;


import com.example.Syllabus.dto.authentication.AuthRequestDTO;
import com.example.Syllabus.dto.authentication.AuthResponseDTO;
import com.example.Syllabus.dto.authentication.UserDTO;
import com.example.Syllabus.services.authentication.AuthService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Contrôleur exposant les endpoints d'authentification.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Endpoint pour authentifier un utilisateur.
     *
     * @param authRequest La requête d'authentification contenant le matricule et le rôle.
     * @return Une réponse indiquant si l'authentification a réussi, avec l'identifiant utilisateur.
     */
    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody AuthRequestDTO authRequest) {
        return authService.authenticate(authRequest);
    }


     /**
     * Endpoint pour obtenir la liste des enseignants fictifs.
     *
     * @return La liste des enseignants fictifs.
     */
    @GetMapping("/fake-teachers")
    public List<UserDTO> getFakeTeachers() {
        return authService.getUsersByRole("enseignant");
    }

    /**
     * Endpoint pour obtenir la liste des étudiants fictifs.
     *
     * @return La liste des étudiants fictifs.
     */
    @GetMapping("/fake-students")
    public List<UserDTO> getFakeStudents() {
        return authService.getUsersByRole("etudiant");
    }

   

    
}
