package gsb.tests;

import gsb.modele.*;

import java.util.Date;

/**
 * Classe de test pour la classe Visiteur.
 * Cette classe teste le constructeur, les getters et setters de la classe Visiteur.
 * Elle permet de vérifier que les valeurs initiales sont correctement attribuées via le constructeur et que les modifications via les setters fonctionnent correctement.
 */
public class VisiteurTest {

    /**
     * Méthode principale qui crée une instance de Visiteur, teste ses getters et setters,
     * et vérifie si les valeurs sont correctement modifiées.
     * 
     * @param args Les arguments de la ligne de commande (non utilisés ici).
     */
    public static void main(String[] args) {
        // Création d'une date pour les tests
        Date dateEntree = new Date();

        // Test du constructeur avec tous les paramètres
        Visiteur visiteur = new Visiteur(
            "V001",                     // Matricule
            "Dupont",                    // Nom
            "Jean",                      // Prénom
            "jdupont",                   // Login
            "password123",               // Mot de passe
            "123 Rue de Paris",          // Adresse
            "75000",                     // Code Postal
            dateEntree,                  // Date d'entrée
            "UNIT01",                    // Code Unité
            "Unité 1"                    // Nom Unité
        );

        // Affichage des valeurs après initialisation avec le constructeur
        System.out.println("Test du constructeur :");
        System.out.println("Matricule (attendu: V001) : " + visiteur.getMatricule());
        System.out.println("Nom (attendu: Dupont) : " + visiteur.getNom());
        System.out.println("Prénom (attendu: Jean) : " + visiteur.getPrenom());
        System.out.println("Login (attendu: jdupont) : " + visiteur.getLogin());
        System.out.println("Mot de passe (attendu: password123) : " + visiteur.getMdp());
        System.out.println("Adresse (attendu: 123 Rue de Paris) : " + visiteur.getAdresse());
        System.out.println("Code Postal (attendu: 75000) : " + visiteur.getCodePostal());
        System.out.println("Date d'entrée (attendue: " + dateEntree + ") : " + visiteur.getDateEntree());
        System.out.println("Code Unité (attendu: UNIT01) : " + visiteur.getCodeUnit());
        System.out.println("Nom Unité (attendu: Unité 1) : " + visiteur.getNomUnit());

        // Modification des valeurs avec les setters
        visiteur.setMatricule("V002");
        visiteur.setNom("Martin");
        visiteur.setPrenom("Lucie");
        visiteur.setLogin("lmartin");
        visiteur.setMdp("newpassword456");
        visiteur.setAdresse("456 Rue de Lyon");
        visiteur.setCodePostal("69000");
        visiteur.setDateEntree(new Date());
        visiteur.setCodeUnit("UNIT02");
        visiteur.setNomUnit("Unité 2");

        // Affichage après modification des valeurs via les setters
        System.out.println("\nAprès modification avec les setters :");
        System.out.println("Matricule (attendu: V002) : " + visiteur.getMatricule());
        System.out.println("Nom (attendu: Martin) : " + visiteur.getNom());
        System.out.println("Prénom (attendu: Lucie) : " + visiteur.getPrenom());
        System.out.println("Login (attendu: lmartin) : " + visiteur.getLogin());
        System.out.println("Mot de passe (attendu: newpassword456) : " + visiteur.getMdp());
        System.out.println("Adresse (attendu: 456 Rue de Lyon) : " + visiteur.getAdresse());
        System.out.println("Code Postal (attendu: 69000) : " + visiteur.getCodePostal());
        System.out.println("Date d'entrée : " + visiteur.getDateEntree());
        System.out.println("Code Unité (attendu: UNIT02) : " + visiteur.getCodeUnit());
        System.out.println("Nom Unité (attendu: Unité 2) : " + visiteur.getNomUnit());

        // Vérification des résultats : comparaison des valeurs attendues et actuelles
        if (
            "V002".equals(visiteur.getMatricule()) &&
            "Martin".equals(visiteur.getNom()) &&
            "Lucie".equals(visiteur.getPrenom()) &&
            "lmartin".equals(visiteur.getLogin()) &&
            "newpassword456".equals(visiteur.getMdp()) &&
            "456 Rue de Lyon".equals(visiteur.getAdresse()) &&
            "69000".equals(visiteur.getCodePostal()) &&
            "UNIT02".equals(visiteur.getCodeUnit()) &&
            "Unité 2".equals(visiteur.getNomUnit())
        ) {
            System.out.println("\nTous les tests sont passés avec succès !");
        } else {
            System.out.println("\nCertains tests ont échoué.");
        }
    }
}
