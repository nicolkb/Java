package gsb.tests;

import gsb.service.VisiteurService;

/**
 * Classe de test pour le service VisiteurService.
 * Permet de tester la méthode ajouterVisiteur et d'afficher les résultats dans la console.
 */
public class VisiteurServiceTest {

    /**
     * Méthode principale pour exécuter les tests.
     * @param args arguments de la ligne de commande.
     */
    public static void main(String[] args) {
        // Test 1 : Ajout réussi d'un visiteur
        System.out.println("Test 1 : Ajout réussi");
        String resultat1 = VisiteurService.ajouterVisiteur(
            "V1236", 
            "Dupont", 
            "Jean", 
            "jdupont", 
            "password123", 
            "12 rue des Lilas", 
            "75011", 
            "2024-11-12", 
            "U01", 
            "Unité Paris"
        );
        System.out.println(resultat1);
        System.out.println();

        // Test 2 : Erreur - Matricule trop court
        System.out.println("Test 2 : Matricule trop long");
        String resultat2 = VisiteurService.ajouterVisiteur(
            "V122345678945566", 
            "Durand", 
            "Marie", 
            "mdurand", 
            "password456", 
            "34 avenue des Champs", 
            "75011", 
            "2024-11-12", 
            "U02", 
            "Unité Lyon"
        );
        System.out.println(resultat2);
        System.out.println();

        // Test 3 : Erreur - Mot de passe trop court
        System.out.println("Test 3 : Mot de passe trop court");
        String resultat3 = VisiteurService.ajouterVisiteur(
            "V56", 
            "Martin", 
            "Paul", 
            "pmartin", 
            "short", 
            "56 boulevard Haussmann", 
            "75011", 
            "2024-11-12", 
            "U03", 
            "Unité Marseille"
        );
        System.out.println(resultat3);
        System.out.println();

        // Test 4 : Erreur - Code postal invalide
        System.out.println("Test 4 : Code postal invalide");
        String resultat4 = VisiteurService.ajouterVisiteur(
            "V91", 
            "Lemoine", 
            "Alice", 
            "alemoine", 
            "securepass", 
            "78 rue Lafayette", 
            "INVALID", // Code postal invalide
            "2024-11-12", 
            "U04", 
            "Unité Bordeaux"
        );
        System.out.println(resultat4);
        System.out.println();

        // Test 5 : Erreur - Date d'entrée invalide
        System.out.println("Test 5 : Date d'entrée invalide");
        String resultat5 = VisiteurService.ajouterVisiteur(
            "V3", 
            "Morel", 
            "Sophie", 
            "smorel", 
            "strongpassword", 
            "90 quai de la Seine", 
            "75011", 
            "12-11-2024", // Format incorrect
            "U05", 
            "Unité Toulouse"
        );
        System.out.println(resultat5);
        System.out.println();
    }
}
