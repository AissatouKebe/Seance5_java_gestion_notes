package com.ism.repository.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ism.domain.Etudiant;
import com.ism.repository.EtudiantRepository;

public class EtudiantRepositoryMemory implements EtudiantRepository {
    private List<Etudiant> etudiants = new ArrayList<>();

    @Override
    public boolean insert(Etudiant nouveau) {
        etudiants.add(nouveau);
        return true;
    }

    @Override
    public List<Etudiant> selectAll() {
        return etudiants;
    }

    @Override
    public Optional<Etudiant> findByMatricule(String matricule) {
        return etudiants.stream()
                .filter(e -> e.getMatricule().equals(matricule))
                .findFirst();
    }
}