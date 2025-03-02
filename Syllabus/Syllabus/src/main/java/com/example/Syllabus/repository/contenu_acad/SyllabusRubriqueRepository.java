package com.example.Syllabus.repository.contenu_acad;


import com.example.Syllabus.model.contenu_acad.SyllabusRubrique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SyllabusRubriqueRepository extends JpaRepository<SyllabusRubrique, Long> {
}
