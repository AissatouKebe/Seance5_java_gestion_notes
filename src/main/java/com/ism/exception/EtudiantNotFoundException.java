package com.ism.exception;

/**
 * Levée quand une règle métier échoue parce qu'aucun étudiant ne correspond
 * au matricule demandé dans le repository (règle métier = dépend de la source de données).
 */
public class EtudiantNotFoundException extends RuntimeException {
    public EtudiantNotFoundException(String message) {
        super(message);
    }
}