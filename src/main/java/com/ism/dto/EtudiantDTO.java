package com.ism.dto;

import com.ism.exception.ValidationException;

import lombok.Getter;
import lombok.ToString;

/**
 * DTO de création d'un étudiant.
 * Porte les règles de validation (format/obligation des champs) : tout ce qui
 * ne dépend pas de la source de données passe ici, pas dans le service.
 */
@Getter
@ToString
public class EtudiantDTO {
    private final String nomComplet;
    private final String matricule;
    private final String classe;

    private EtudiantDTO(String nomComplet, String matricule, String classe) {
        this.nomComplet = nomComplet;
        this.matricule = matricule;
        this.classe = classe;
    }

    public static EtudiantDTO of(String nomComplet, String matricule, String classe) {
        if (nomComplet == null || nomComplet.isBlank()) {
            throw new ValidationException("Le nom complet est obligatoire");
        }
        if (matricule == null || matricule.isBlank()) {
            throw new ValidationException("Le matricule est obligatoire");
        }
        if (classe == null || classe.isBlank()) {
            throw new ValidationException("La classe est obligatoire");
        }
        return new EtudiantDTO(nomComplet.trim(), matricule.trim(), classe.trim());
    }
}