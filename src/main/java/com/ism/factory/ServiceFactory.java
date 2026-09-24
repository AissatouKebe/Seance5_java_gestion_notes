package com.ism.factory;

import com.ism.repository.EtudiantRepository;
import com.ism.repository.NoteRepository;
import com.ism.repository.memory.EtudiantRepositoryMemory;
import com.ism.repository.memory.NoteRepositoryMemory;
import com.ism.service.BulletinService;
import com.ism.service.BulletinServiceImpl;
import com.ism.service.EtudiantService;
import com.ism.service.EtudiantServiceImpl;
import com.ism.service.NoteService;
import com.ism.service.NoteServiceImpl;

/**
 * Factory : point d'entrée unique pour obtenir les services de l'application.
 * Garantit qu'un seul repository (et un seul service) de chaque type est créé
 * (Singleton géré par la factory) : la vue ne fait jamais "new" un repo/service.
 */
public final class ServiceFactory {

    private static EtudiantRepository etudiantRepository;
    private static NoteRepository noteRepository;

    private static EtudiantService etudiantService;
    private static NoteService noteService;
    private static BulletinService bulletinService;

    private ServiceFactory() {
    }

    private static EtudiantRepository getEtudiantRepository() {
        if (etudiantRepository == null) {
            etudiantRepository = new EtudiantRepositoryMemory();
        }
        return etudiantRepository;
    }

    private static NoteRepository getNoteRepository() {
        if (noteRepository == null) {
            noteRepository = new NoteRepositoryMemory();
        }
        return noteRepository;
    }

    public static EtudiantService getEtudiantService() {
        if (etudiantService == null) {
            etudiantService = new EtudiantServiceImpl(getEtudiantRepository());
        }
        return etudiantService;
    }

    public static NoteService getNoteService() {
        if (noteService == null) {
            noteService = new NoteServiceImpl(getNoteRepository(), getEtudiantRepository());
        }
        return noteService;
    }

    public static BulletinService getBulletinService() {
        if (bulletinService == null) {
            bulletinService = new BulletinServiceImpl(getEtudiantService(), getNoteService());
        }
        return bulletinService;
    }
}