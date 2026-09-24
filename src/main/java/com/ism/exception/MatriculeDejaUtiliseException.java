package com.ism.exception;

/**
 * Levée quand une règle métier échoue parce qu'un étudiant existe déjà
 * avec ce matricule dans le repository.
 */
public class MatriculeDejaUtiliseException extends RuntimeException {
    public MatriculeDejaUtiliseException(String message) {
        super(message);
    }
}