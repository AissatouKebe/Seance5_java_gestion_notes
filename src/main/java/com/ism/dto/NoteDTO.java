package com.ism.dto;

import com.ism.exception.ValidationException;

import lombok.Getter;
import lombok.ToString;

/**
 * DTO de création d'une note.
 * Règles de validation (obligation des champs, format numérique, plage 0-20) :
 * elles ne dépendent pas de la source de données, donc elles restent ici.
 */
@Getter
@ToString
public class NoteDTO {
    private final String matriculeEtudiant;
    private final String matiere;
    private final double valeur;

    private NoteDTO(String matriculeEtudiant, String matiere, double valeur) {
        this.matriculeEtudiant = matriculeEtudiant;
        this.matiere = matiere;
        this.valeur = valeur;
    }

    public static NoteDTO of(String matriculeEtudiant, String matiere, String valeurSaisie) {
        if (matriculeEtudiant == null || matriculeEtudiant.isBlank()) {
            throw new ValidationException("Le matricule de l'étudiant est obligatoire");
        }
        if (matiere == null || matiere.isBlank()) {
            throw new ValidationException("La matière est obligatoire");
        }

        double valeur;
        try {
            valeur = Double.parseDouble(valeurSaisie);
        } catch (NumberFormatException | NullPointerException e) {
            throw new ValidationException("La note doit être un nombre");
        }

        if (valeur < 0 || valeur > 20) {
            throw new ValidationException("La note doit être comprise entre 0 et 20");
        }

        return new NoteDTO(matriculeEtudiant.trim(), matiere.trim(), valeur);
    }
}