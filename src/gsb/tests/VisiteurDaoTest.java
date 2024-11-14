package gsb.tests;

import gsb.modele.Visiteur;
import gsb.modele.dao.VisiteurDao;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class VisiteurDaoTest {

    public static void main(String[] args) {
        // Test de la fonction creerVisiteur
        System.out.println("Test de la fonction creerVisiteur :");
        Visiteur nouveauVisiteur = new Visiteur(
            "V001",
            "Dupont",
            "Jean",
            "jdupont",
            "password",
            "123 rue de Test",
            "13015",
            new Timestamp(new Date().getTime()),
            "U001",
            "Unité Test"
        );
        
        int resultCreer = VisiteurDao.creerVisiteur(nouveauVisiteur);
        if (resultCreer == 1) {
            System.out.println("Insertion du visiteur réussie.");
        } else {
            System.out.println("Échec de l'insertion du visiteur.");
        }

        // Test de la fonction selectionnerVisiteur
        System.out.println("\nTest de la fonction selectionnerVisiteur :");
        Visiteur visiteurRecherche = VisiteurDao.selectionnerVisiteur("a131");
        if (visiteurRecherche != null) {
            System.out.println("Visiteur trouvé : " + visiteurRecherche.getNom() + " " + visiteurRecherche.getPrenom());
        } else {
            System.out.println("Visiteur non trouvé.");
        }

        // Test de la fonction retournerCollectionDesVisiteurs
        System.out.println("\nTest de la fonction retournerCollectionDesVisiteurs :");
        ArrayList<Visiteur> collectionVisiteurs = VisiteurDao.retournerCollectionDesVisiteurs();
        if (!collectionVisiteurs.isEmpty()) {
            System.out.println("Liste des visiteurs :");
            for (Visiteur vis : collectionVisiteurs) {
                System.out.println("- " + vis.getNom() + " " + vis.getPrenom());
            }
        } else {
            System.out.println("Aucun visiteur trouvé dans la base.");
        }

    }
}
