package com.ism.service;

import java.util.List;

import com.ism.domain.Etudiant;
import com.ism.dto.EtudiantDTO;

public interface EtudiantService {
    boolean addEtudiant(EtudiantDTO dto);
    List<Etudiant> findAll();
    Etudiant findByMatricule(String matricule);
}