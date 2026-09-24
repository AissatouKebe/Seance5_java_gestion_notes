package com.ism.service;

import java.util.ArrayList;
import java.util.List;

import com.ism.domain.Etudiant;
import com.ism.domain.Note;
import com.ism.dto.BulletinDTO;

public class BulletinServiceImpl implements BulletinService {
    private EtudiantService etudiantService;
    private NoteService noteService;

    public BulletinServiceImpl(EtudiantService etudiantService, NoteService noteService) {
        this.etudiantService = etudiantService;
        this.noteService = noteService;
    }

    @Override
    public List<BulletinDTO> genererBulletins() {
        List<BulletinDTO> bulletins = new ArrayList<>();

        for (Etudiant etudiant : etudiantService.findAll()) {
            List<Note> notes = noteService.findByMatricule(etudiant.getMatricule());
            double moyenne = calculerMoyenne(notes);
            bulletins.add(new BulletinDTO(etudiant, notes, moyenne));
        }

        return bulletins;
    }

    private double calculerMoyenne(List<Note> notes) {
        if (notes.isEmpty()) {
            return 0.0;
        }

        double somme = 0.0;
        for (Note note : notes) {
            somme += note.getValeur();
        }

        return somme / notes.size();
    }
}