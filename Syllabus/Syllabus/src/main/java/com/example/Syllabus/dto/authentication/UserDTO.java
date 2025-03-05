package com.example.Syllabus.dto.authentication;

/**
 * Représente un utilisateur fictif (enseignant ou étudiant).
 */
public class UserDTO {

    private Long id;     // Identifiant de l'utilisateur (enseignant ou étudiant)
    private String name; // Nom de l'utilisateur

    // Constructeurs
    public UserDTO() {}

    public UserDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
