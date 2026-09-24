package com.ism.service;

import java.util.List;

import com.ism.domain.Note;
import com.ism.dto.NoteDTO;
import com.ism.exception.EtudiantNotFoundException;
import com.ism.repository.EtudiantRepository;
import com.ism.repository.NoteRepository;

public class NoteServiceImpl implements NoteService {
    private NoteRepository noteRepo;
    private EtudiantRepository etudiantRepo;

    public NoteServiceImpl(NoteRepository noteRepo, EtudiantRepository etudiantRepo) {
        this.noteRepo = noteRepo;
        this.etudiantRepo = etudiantRepo;
    }

    @Override
    public boolean addNote(NoteDTO dto) {
        // Règle métier : dépend de la source de données (repo étudiant), donc vérifiée ici
        etudiantRepo.findByMatricule(dto.getMatriculeEtudiant())
                .orElseThrow(() -> new EtudiantNotFoundException(
                        "Aucun étudiant trouvé avec le matricule " + dto.getMatriculeEtudiant()));

        Note note = new Note(dto.getMatriculeEtudiant(), dto.getMatiere(), dto.getValeur());
        return noteRepo.insert(note);
    }

    @Override
    public List<Note> findByMatricule(String matricule) {
        return noteRepo.findByMatriculeEtudiant(matricule);
    }
}