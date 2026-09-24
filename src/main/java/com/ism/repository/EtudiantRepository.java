package com.ism.repository;

import java.util.List;
import java.util.Optional;

import com.ism.domain.Etudiant;

public interface EtudiantRepository {
    boolean insert(Etudiant nouveau);
    List<Etudiant> selectAll();
    Optional<Etudiant> findByMatricule(String matricule);
}