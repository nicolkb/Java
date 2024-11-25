package gsb.tests;

import gsb.modele.*;

public class MedicamentTest {
    public static void main(String[] args) {
        // Test avec le premier constructeur (tous les paramètres)
        Medicament medicament1 = new Medicament(
            "D12345",                   // Dépôt légal
            "Paracétamol",              // Nom commercial
            "Paracétamol 500mg",        // Composition
            "Soulage la douleur",       // Effets
            "Insuffisance hépatique",   // Contre-indications
            2.5f,                       // Prix échantillon
            "A01",                      // Code famille
            "Antalgique"                // Libellé famille
        );

        System.out.println("Test du premier constructeur :");
        System.out.println("Dépôt légal (attendu: D12345) : " + medicament1.getMedDepotLegal());
        System.out.println("Nom commercial (attendu: Paracétamol) : " + medicament1.getMedNomCommercial());
        System.out.println("Composition (attendu: Paracétamol 500mg) : " + medicament1.getMedComposition());
        System.out.println("Effets (attendu: Soulage la douleur) : " + medicament1.getMedEffets());
        System.out.println("Contre-indications (attendu: Insuffisance hépatique) : " + medicament1.getMedContreIndic());
        System.out.println("Prix échantillon (attendu: 2.5) : " + medicament1.getMedPrixEchantillon());
        System.out.println("Code famille (attendu: A01) : " + medicament1.getFamCode());
        System.out.println("Libellé famille (attendu: Antalgique) : " + medicament1.getFamLibelle());

        // Test avec le second constructeur (partiel)
        Medicament medicament2 = new Medicament(
            "D67890",                   // Dépôt légal
            "Ibuprofène",               // Nom commercial
            "Ibuprofène 200mg",         // Composition
            "Anti-inflammatoire",       // Effets
            "Asthme"                    // Contre-indications
        );

        System.out.println("\nTest du second constructeur :");
        System.out.println("Dépôt légal (attendu: D67890) : " + medicament2.getMedDepotLegal());
        System.out.println("Nom commercial (attendu: Ibuprofène) : " + medicament2.getMedNomCommercial());
        System.out.println("Composition (attendu: Ibuprofène 200mg) : " + medicament2.getMedComposition());
        System.out.println("Effets (attendu: Anti-inflammatoire) : " + medicament2.getMedEffets());
        System.out.println("Contre-indications (attendu: Asthme) : " + medicament2.getMedContreIndic());
        System.out.println("Prix échantillon (attendu: 0.0 par défaut) : " + medicament2.getMedPrixEchantillon());

        // Modification des valeurs via les setters
        medicament2.setMedPrixEchantillon(3.0f);
        medicament2.setFamCode("B02");
        medicament2.setFamLibelle("Anti-inflammatoire");

        System.out.println("\nAprès modification avec les setters :");
        System.out.println("Prix échantillon (attendu: 3.0) : " + medicament2.getMedPrixEchantillon());
        System.out.println("Code famille (attendu: B02) : " + medicament2.getFamCode());
        System.out.println("Libellé famille (attendu: Anti-inflammatoire) : " + medicament2.getFamLibelle());

        // Vérification globale
        if (medicament1.getMedDepotLegal().equals("D12345") &&
            medicament2.getFamLibelle().equals("Anti-inflammatoire")) {
            System.out.println("\nTous les tests sont passés avec succès !");
        } else {
            System.out.println("\nCertains tests ont échoué.");
        }
    }
}
