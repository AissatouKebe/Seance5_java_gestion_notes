package com.ism.repository.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ism.domain.Note;
import com.ism.repository.NoteRepository;

public class NoteRepositoryMemory implements NoteRepository {
    private List<Note> notes = new ArrayList<>();

    @Override
    public boolean insert(Note note) {
        notes.add(note);
        return true;
    }

    @Override
    public List<Note> selectAll() {
        return notes;
    }

    @Override
    public List<Note> findByMatriculeEtudiant(String matricule) {
        return notes.stream()
                .filter(n -> n.getMatriculeEtudiant().equals(matricule))
                .collect(Collectors.toList());
    }
}