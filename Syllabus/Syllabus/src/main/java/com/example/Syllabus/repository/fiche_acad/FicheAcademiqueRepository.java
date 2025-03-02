package com.example.Syllabus.repository.fiche_acad;


import com.example.Syllabus.model.fiche_acad.FicheAcademique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FicheAcademiqueRepository extends JpaRepository<FicheAcademique, Long> {
}

