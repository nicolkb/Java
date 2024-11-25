package gsb.tests;

import gsb.modele.*;

/**
 * Classe de test pour la classe Visite.
 * Cette classe vérifie la création d'objets de type Visite à l'aide des différents constructeurs disponibles
 * et teste le bon fonctionnement des getters et setters.
 * Elle affiche les résultats des tests à chaque étape pour valider les comportements attendus.
 */
public class VisiteTest {

    /**
     * Méthode principale qui effectue des tests sur la classe Visite.
     * 
     * @param args Les arguments de la ligne de commande (non utilisés ici).
     */
    public static void main(String[] args) {
        System.out.println("== Début des tests de la classe Visite ==");

        // Test du premier constructeur avec tous les paramètres, sans la ville
        System.out.println("\n-- Test du constructeur complet sans ville --");
        Visite visite1 = new Visite(
            "v0024",
            "2024-11-01",
            "b59",
            "m001",
            "Première visite",
            "CARTION6",
            10,
            "BACTIG10",
            5
        );

        // Affichage de la visite pour vérifier la création
        afficherVisite(visite1);

        // Test du constructeur avec tous les paramètres, y compris la ville
        System.out.println("\n-- Test du constructeur complet avec ville --");
        Visite visite2 = new Visite(
            "v0025",
            "2024-11-02",
            "b59",
            "m002",
            "Deuxième visite",
            "CARTION6",
            15,
            "BACTIG10",
            10,
            "Paris"
        );

        // Affichage de la visite pour vérifier la création
        afficherVisite(visite2);

        // Test du constructeur simplifié sans médicaments offerts ni quantités
        System.out.println("\n-- Test du constructeur sans médicaments offerts --");
        Visite visite3 = new Visite(
            "v0026",
            "2024-11-03",
            "Test commentaire",
            "b59",
            "m003"
        );

        // Affichage de la visite pour vérifier la création
        afficherVisite(visite3);

        // Test du constructeur avec uniquement les médicaments et les quantités
        System.out.println("\n-- Test du constructeur avec médicaments et quantités uniquement --");
        Visite visite4 = new Visite(
            "v0026",
            "Visite commentée",
            "CARTION6",
            8,
            "BACTIG10",
            4
        );

        // Affichage de la visite pour vérifier la création
        afficherVisite(visite4);

        // Modification des valeurs via les setters pour tester la mise à jour des informations
        System.out.println("\n-- Test des setters --");
        visite4.setReference("v0027");
        visite4.setCommentaire("Commentaire modifié");
        visite4.setMedOffert1("BACTIG10");
        visite4.setQuantiteMed1(20);
        visite4.setMedOffert2("CARTION6");
        visite4.setQuantiteMed2(10);
        visite4.setVille("Lyon");

        // Affichage après modification des valeurs
        afficherVisite(visite4);

        // Vérification des getters après modification
        System.out.println("\n-- Vérification des getters après modification --");
        System.out.println("Référence (attendu: v0027) : " + visite4.getReference());
        System.out.println("Commentaire (attendu: Commentaire modifié) : " + visite4.getCommentaire());
        System.out.println("Médicament offert 1 (attendu: BACTIG10) : " + visite4.getMedOffert1());
        System.out.println("Quantité médicament 1 (attendu: 20) : " + visite4.getQuantiteMed1());
        System.out.println("Médicament offert 2 (attendu: CARTION6) : " + visite4.getMedOffert2());
        System.out.println("Quantité médicament 2 (attendu: 10) : " + visite4.getQuantiteMed2());
        System.out.println("Ville (attendu: Lyon) : " + visite4.getVille());

        System.out.println("\n== Fin des tests de la classe Visite ==");
    }

    /**
     * Méthode utilitaire pour afficher les détails d'une visite.
     * Elle permet de vérifier que les valeurs des attributs d'une visite sont correctement affichées.
     * 
     * @param visite L'objet Visite à afficher.
     */
    private static void afficherVisite(Visite visite) {
        System.out.println("Référence : " + visite.getReference());
        System.out.println("Date de visite : " + visite.getDateVisite());
        System.out.println("Matricule : " + visite.getMatricule());
        System.out.println("Code Médecin : " + visite.getCodeMed());
        System.out.println("Commentaire : " + visite.getCommentaire());
        System.out.println("Médicament offert 1 : " + visite.getMedOffert1());
        System.out.println("Quantité médicament 1 : " + visite.getQuantiteMed1());
        System.out.println("Médicament offert 2 : " + visite.getMedOffert2());
        System.out.println("Quantité médicament 2 : " + visite.getQuantiteMed2());
        System.out.println("Ville : " + visite.getVille());
    }
}

