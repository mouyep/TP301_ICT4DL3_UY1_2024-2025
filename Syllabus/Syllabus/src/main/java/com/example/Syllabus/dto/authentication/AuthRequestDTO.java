package com.example.Syllabus.dto.authentication;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthRequestDTO {
    private String matricule;
    private String role;
}
