package gsb.tests;

import gsb.modele.*;

/**
 * Classe de test pour la classe Stock.
 * Cette classe teste le constructeur, les getters et setters de la classe Stock.
 * Elle permet de vérifier que les valeurs sont correctement attribuées via le constructeur et que les modifications via les setters fonctionnent correctement.
 */
public class StockTest {

    /**
     * Méthode principale qui crée une instance de Stock, teste ses getters et setters,
     * et vérifie si les valeurs sont correctement modifiées.
     * 
     * @param args Les arguments de la ligne de commande (non utilisés ici).
     */
    public static void main(String[] args) {
        // Test du constructeur avec tous les paramètres
        Stock stock1 = new Stock(1, "BACTIG10", "f21", 50);

        // Affichage des valeurs après initialisation avec le constructeur
        System.out.println("Test du constructeur :");
        System.out.println("ID Stock (attendu: 1) : " + stock1.getStockId());
        System.out.println("Dépôt légal (attendu: BACTIG10) : " + stock1.getMedDepotLegal());
        System.out.println("Matricule visiteur (attendu: f21) : " + stock1.getMatricule());
        System.out.println("Quantité en stock (attendu: 50) : " + stock1.getStock());

        // Modification des valeurs avec les setters
        stock1.setStockId(2);
        stock1.setMedDepotLegal("BACTIV13");
        stock1.setMatricule("f39");
        stock1.setStock(100);

        // Affichage après modification des valeurs via les setters
        System.out.println("\nAprès modification avec les setters :");
        System.out.println("ID Stock (attendu: 2) : " + stock1.getStockId());
        System.out.println("Dépôt légal (attendu: BACTIV13) : " + stock1.getMedDepotLegal());
        System.out.println("Matricule visiteur (attendu: f39) : " + stock1.getMatricule());
        System.out.println("Quantité en stock (attendu: 100) : " + stock1.getStock());

        // Vérification des résultats : comparaison des valeurs attendues et actuelles
        if (stock1.getStockId() == 2 &&
            "BACTIV13".equals(stock1.getMedDepotLegal()) &&
            "f39".equals(stock1.getMatricule()) &&
            stock1.getStock() == 100) {
            System.out.println("\nTous les tests sont passés avec succès !");
        } else {
            System.out.println("\nCertains tests ont échoué.");
        }
    }
}
