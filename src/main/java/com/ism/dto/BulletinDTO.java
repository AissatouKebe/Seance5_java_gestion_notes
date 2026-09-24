package com.ism.dto;

import java.util.List;

import com.ism.domain.Etudiant;
import com.ism.domain.Note;

import lombok.Getter;
import lombok.ToString;

/**
 * DTO de sortie : un étudiant, ses notes, et sa moyenne calculée.
 * Produit par le BulletinService (façade Etudiant + Note).
 */
@Getter
@ToString
public class BulletinDTO {
    private final Etudiant etudiant;
    private final List<Note> notes;
    private final double moyenne;

    public BulletinDTO(Etudiant etudiant, List<Note> notes, double moyenne) {
        this.etudiant = etudiant;
        this.notes = notes;
        this.moyenne = moyenne;
    }
}