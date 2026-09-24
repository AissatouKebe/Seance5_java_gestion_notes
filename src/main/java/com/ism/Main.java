package com.ism;

import java.util.List;

import com.ism.domain.Etudiant;
import com.ism.domain.Note;
import com.ism.dto.BulletinDTO;
import com.ism.dto.EtudiantDTO;
import com.ism.dto.NoteDTO;
import com.ism.exception.EtudiantNotFoundException;
import com.ism.exception.MatriculeDejaUtiliseException;
import com.ism.exception.ValidationException;
import com.ism.factory.ServiceFactory;
import com.ism.service.BulletinService;
import com.ism.service.EtudiantService;
import com.ism.service.NoteService;

public class Main {
    public static void main(String[] args) {
        EtudiantService etudiantService = ServiceFactory.getEtudiantService();
        NoteService noteService = ServiceFactory.getNoteService();
        BulletinService bulletinService = ServiceFactory.getBulletinService();

        String choix;
        do {
            afficherMenu();
            choix = System.console().readLine("Votre choix : ");

            try {
                switch (choix) {
                    case "1":
                        ajouterEtudiant(etudiantService);
                        break;

                    case "2":
                        listerEtudiants(etudiantService);
                        break;

                    case "3":
                        ajouterNote(noteService);
                        break;

                    case "4":
                        afficherBulletins(bulletinService);
                        break;

                    case "0":
                        break;

                    default:
                        System.out.println("Choix invalide");
                        break;
                }
            } catch (ValidationException | MatriculeDejaUtiliseException | EtudiantNotFoundException e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        } while (!choix.equals("0"));
    }

    private static void afficherMenu() {
        System.out.println("========== GESTION DES NOTES ==========");
        System.out.println();
        System.out.println("1 - Ajouter un étudiant");
        System.out.println("2 - Lister les étudiants");
        System.out.println("3 - Ajouter une note");
        System.out.println("4 - Afficher les étudiants avec leurs notes et moyennes");
        System.out.println("0 - Quitter");
        System.out.println();
    }

    private static void ajouterEtudiant(EtudiantService service) {
        String nom = System.console().readLine("Nom complet : ");
        String matricule = System.console().readLine("Matricule : ");
        String classe = System.console().readLine("Classe : ");

        EtudiantDTO dto = EtudiantDTO.of(nom, matricule, classe);
        boolean success = service.addEtudiant(dto);
        System.out.println(success ? "Succès" : "Erreur");
    }

    private static void listerEtudiants(EtudiantService service) {
        List<Etudiant> etudiants = service.findAll();
        if (etudiants.isEmpty()) {
            System.out.println("Aucun étudiant enregistré");
            return;
        }
        for (Etudiant etudiant : etudiants) {
            System.out.println(etudiant);
        }
    }

    private static void ajouterNote(NoteService service) {
        String matricule = System.console().readLine("Matricule de l'étudiant : ");
        String matiere = System.console().readLine("Matière : ");
        String valeur = System.console().readLine("Note (/20) : ");

        NoteDTO dto = NoteDTO.of(matricule, matiere, valeur);
        boolean success = service.addNote(dto);
        System.out.println(success ? "Succès" : "Erreur");
    }

    private static void afficherBulletins(BulletinService service) {
        List<BulletinDTO> bulletins = service.genererBulletins();
        if (bulletins.isEmpty()) {
            System.out.println("Aucun étudiant enregistré");
            return;
        }

        for (BulletinDTO bulletin : bulletins) {
            Etudiant etudiant = bulletin.getEtudiant();
            System.out.println(etudiant.getNomComplet() + " (" + etudiant.getMatricule() + ")");

            if (bulletin.getNotes().isEmpty()) {
                System.out.println("  Aucune note");
            } else {
                for (Note note : bulletin.getNotes()) {
                    System.out.println("  - " + note.getMatiere() + " : " + note.getValeur() + "/20");
                }
            }

            System.out.println("  Moyenne : " + bulletin.getMoyenne());
            System.out.println();
        }
    }
}