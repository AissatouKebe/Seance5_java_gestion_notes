package com.ism.exception;

/**
 * Levée quand les données saisies ne respectent pas une règle de validation
 * (contrôlée au niveau du DTO, indépendamment de la source de données).
 */
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}