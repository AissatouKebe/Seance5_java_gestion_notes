package com.ism.service;

import java.util.List;

import com.ism.domain.Etudiant;
import com.ism.dto.EtudiantDTO;
import com.ism.exception.EtudiantNotFoundException;
import com.ism.exception.MatriculeDejaUtiliseException;
import com.ism.repository.EtudiantRepository;

public class EtudiantServiceImpl implements EtudiantService {
    private EtudiantRepository repo;

    public EtudiantServiceImpl(EtudiantRepository repo) {
        this.repo = repo;
    }

    @Override
    public boolean addEtudiant(EtudiantDTO dto) {
        // Règle métier : dépend de la source de données (repo), donc vérifiée ici et pas dans le DTO
        if (repo.findByMatricule(dto.getMatricule()).isPresent()) {
            throw new MatriculeDejaUtiliseException(
                    "Un étudiant existe déjà avec le matricule " + dto.getMatricule());
        }

        Etudiant nouveau = new Etudiant(dto.getNomComplet(), dto.getMatricule(), dto.getClasse());
        return repo.insert(nouveau);
    }

    @Override
    public List<Etudiant> findAll() {
        return repo.selectAll();
    }

    @Override
    public Etudiant findByMatricule(String matricule) {
        return repo.findByMatricule(matricule)
                .orElseThrow(() -> new EtudiantNotFoundException(
                        "Aucun étudiant trouvé avec le matricule " + matricule));
    }
}