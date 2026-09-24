package com.ism.repository;

import java.util.List;

import com.ism.domain.Note;

public interface NoteRepository {
    boolean insert(Note note);
    List<Note> selectAll();
    List<Note> findByMatriculeEtudiant(String matricule);
}