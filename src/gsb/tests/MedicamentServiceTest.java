package gsb.tests;

import gsb.service.MedicamentService;

/**
 * Classe de test pour le service MedicamentService.
 * Permet de tester la méthode ajouterMedicament et d'afficher les résultats dans la console.
 */
public class MedicamentServiceTest {

    /**
     * Méthode principale pour exécuter les tests.
     * @param args arguments de la ligne de commande.
     */
    public static void main(String[] args) {
        // Test 1 : Ajout réussi d'un médicament
        System.out.println("Test 1 : Ajout réussi");
        String resultat1 = MedicamentService.ajouterMedicament(
            "DL12345678", 
            "MedicamentA", 
            "SubstanceA", 
            "EffetsBénéfiques", 
            "Aucune", 
            10.5f, 
            "ABC", 
            "Antibiotiques"
        );
        System.out.println(resultat1);
        System.out.println();

        // Test 2 : Erreur - dépôt légal nul
        System.out.println("Test 2 : Dépôt légal nul");
        String resultat2 = MedicamentService.ajouterMedicament(
            null, 
            "MedicamentB", 
            "SubstanceB", 
            "EffetsNégatifs", 
            "ContreIndicationB", 
            15.0f, 
            "DEF", 
            "Antalgiques"
        );
        System.out.println(resultat2);
        System.out.println();

        // Test 3 : Erreur - code famille invalide
        System.out.println("Test 3 : Code famille invalide");
        String resultat3 = MedicamentService.ajouterMedicament(
            "DL23456789", 
            "MedicamentC", 
            "SubstanceC", 
            "EffetsPositifs", 
            "ContreIndicationC", 
            20.0f, 
            "XY", // Code famille invalide
            "Vitamines"
        );
        System.out.println(resultat3);
        System.out.println();

        // Test 4 : Erreur - prix négatif
        System.out.println("Test 4 : Prix négatif");
        String resultat4 = MedicamentService.ajouterMedicament(
            "DL34567890", 
            "MedicamentD", 
            "SubstanceD", 
            "EffetsDivers", 
            "ContreIndicationD", 
            -5.0f, // Prix négatif
            "GHI", 
            "Antidépresseurs"
        );
        System.out.println(resultat4);
        System.out.println();
    }
}
