package com.ism.service;

import java.util.List;

import com.ism.domain.Note;
import com.ism.dto.NoteDTO;

public interface NoteService {
    boolean addNote(NoteDTO dto);
    List<Note> findByMatricule(String matricule);
}