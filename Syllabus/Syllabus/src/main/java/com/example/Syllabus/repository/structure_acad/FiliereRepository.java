package com.example.Syllabus.repository.structure_acad;


import com.example.Syllabus.model.structure_acad.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FiliereRepository extends JpaRepository<Filiere, Long> {
}

