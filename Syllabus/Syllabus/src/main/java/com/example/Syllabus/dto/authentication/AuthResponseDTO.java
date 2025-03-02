package com.example.Syllabus.dto.authentication;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponseDTO {
    private boolean valid;
    private String userIdentifier;
    private String message;
}
