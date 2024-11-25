package gsb.tests;

import gsb.modele.*;

/**
 * Classe de test pour la classe Medecin.
 * Cette classe permet de tester les méthodes getters et setters de la classe Medecin,
 * ainsi que la modification de la localité associée à un médecin.
 */
public class MedecinTest {

    /**
     * Méthode principale permettant de tester les getters et setters de la classe Medecin.
     * Elle crée une instance de Medecin, teste l'accès et la modification de ses attributs,
     * puis vérifie les résultats des tests.
     *
     * @param args Les arguments de la ligne de commande (non utilisés ici).
     */
    public static void main(String[] args) {
        // Création d'une instance de Localite pour associer à un médecin
        Localite localite = new Localite("75000", "Paris");

        // Création d'une instance de Medecin avec tous les attributs nécessaires
        Medecin medecin = new Medecin(
            "M001",                // Code du médecin
            "Dupont",              // Nom
            "Jean",                // Prénom
            "12 Rue de Paris",     // Adresse
            localite,              // Localité
            "0102030405",          // Téléphone
            "Haut",                // Potentiel
            "Cardiologue"          // Spécialité
        );

        // Tests des getters pour vérifier la récupération des valeurs initiales
        System.out.println("Code Médecin (attendu: M001) : " + medecin.getCodeMed());
        System.out.println("Nom (attendu: Dupont) : " + medecin.getNom());
        System.out.println("Prénom (attendu: Jean) : " + medecin.getPrenom());
        System.out.println("Adresse (attendu: 12 Rue de Paris) : " + medecin.getAdresse());
        System.out.println("Localité (attendu: Paris) : " + medecin.getLaLocalite().getVille());
        System.out.println("Téléphone (attendu: 0102030405) : " + medecin.getTelephone());
        System.out.println("Potentiel (attendu: Haut) : " + medecin.getPotentiel());
        System.out.println("Spécialité (attendu: Cardiologue) : " + medecin.getSpecialite());

        // Modification des valeurs avec les setters
        medecin.setCodeMed("M002");
        medecin.setNom("Martin");
        medecin.setPrenom("Claire");
        medecin.setAdresse("34 Avenue de Lyon");
        medecin.setTelephone("0607080910");
        medecin.setPotentiel("Moyen");
        medecin.setSpecialite("Dermatologue");

        // Modification de la localité associée au médecin
        Localite nouvelleLocalite = new Localite("69000", "Lyon");
        medecin.setLaLocalite(nouvelleLocalite);

        // Vérification des nouvelles valeurs après modification
        System.out.println("\nAprès modification :");
        System.out.println("Code Médecin (attendu: M002) : " + medecin.getCodeMed());
        System.out.println("Nom (attendu: Martin) : " + medecin.getNom());
        System.out.println("Prénom (attendu: Claire) : " + medecin.getPrenom());
        System.out.println("Adresse (attendu: 34 Avenue de Lyon) : " + medecin.getAdresse());
        System.out.println("Localité (attendu: Lyon) : " + medecin.getLaLocalite().getVille());
        System.out.println("Téléphone (attendu: 0607080910) : " + medecin.getTelephone());
        System.out.println("Potentiel (attendu: Moyen) : " + medecin.getPotentiel());
        System.out.println("Spécialité (attendu: Dermatologue) : " + medecin.getSpecialite());

        // Résultats : Vérification que les valeurs ont été correctement modifiées
        if (medecin.getCodeMed().equals("M002") &&
            medecin.getNom().equals("Martin") &&
            medecin.getPrenom().equals("Claire") &&
            medecin.getAdresse().equals("34 Avenue de Lyon") &&
            medecin.getLaLocalite().getVille().equals("Lyon") &&
            medecin.getTelephone().equals("0607080910") &&
            medecin.getPotentiel().equals("Moyen") &&
            medecin.getSpecialite().equals("Dermatologue")) {
            // Si toutes les valeurs sont correctes, afficher un message de succès
            System.out.println("\nTous les tests sont passés avec succès !");
        } else {
            // Si une ou plusieurs valeurs sont incorrectes, afficher un message d'échec
            System.out.println("\nCertains tests ont échoué.");
        }
    }
}
