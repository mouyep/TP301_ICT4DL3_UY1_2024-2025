package com.example.Syllabus.repository.structure_acad;

import com.example.Syllabus.model.structure_acad.NiveauEtude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NiveauEtudeRepository extends JpaRepository<NiveauEtude, Long> {
}
