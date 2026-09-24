package com.ism.service;

import java.util.List;

import com.ism.dto.BulletinDTO;

/**
 * Façade : combine EtudiantService et NoteService pour exposer une vue
 * "étudiant + notes + moyenne" sans que la vue ait à connaître les deux services.
 */
public interface BulletinService {
    List<BulletinDTO> genererBulletins();
}