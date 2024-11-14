package gsb.tests;

import gsb.modele.Stock;
import gsb.modele.dao.StockDao;

import java.util.ArrayList;

public class StockDaoTest {

    public static void main(String[] args) {
        // Test de la fonction getAllStocks
        System.out.println("Test de la fonction getAllStocks :");
        ArrayList<Stock> allStocks = StockDao.getAllStocks();
        if (!allStocks.isEmpty()) {
            System.out.println("Liste des stocks :");
            for (Stock stock : allStocks) {
                System.out.println("- Stock ID : " + stock.getStockId() + ", Médicament : " + stock.getMedDepotLegal() + ", Visiteur : " + stock.getMatricule() + ", Quantité : " + stock.getStock());
            }
        } else {
            System.out.println("Aucun stock trouvé dans la base.");
        }

        // Test de la fonction ajouterStock
        System.out.println("\nTest de la fonction ajouterStock :");
        String matriculeTest = "a131";
        String medDepotLegalTest = "INSXT5";
        int quantiteAjout = 10;
        boolean ajoutReussi = StockDao.ajouterStock(matriculeTest, medDepotLegalTest, quantiteAjout);
        if (ajoutReussi) {
            System.out.println("Ajout ou mise à jour du stock réussie pour le visiteur " + matriculeTest + " et le médicament " + medDepotLegalTest);
        } else {
            System.out.println("Échec de l'ajout ou de la mise à jour du stock.");
        }

        // Test de la fonction getStockByVisiteurEtMedicament
        System.out.println("\nTest de la fonction getStockByVisiteurEtMedicament :");
        Stock stockRecherche = StockDao.getStockByVisiteurEtMedicament(matriculeTest, medDepotLegalTest);
        if (stockRecherche != null) {
            System.out.println("Stock trouvé : ID : " + stockRecherche.getStockId() + ", Quantité : " + stockRecherche.getStock());
        } else {
            System.out.println("Stock non trouvé pour le visiteur " + matriculeTest + " et le médicament " + medDepotLegalTest);
        }

        // Test de la fonction modifierStock
        System.out.println("\nTest de la fonction modifierStock :");
        if (stockRecherche != null) {
            int nouvelleQuantite = stockRecherche.getStock() + 5;
            stockRecherche.setStock(nouvelleQuantite);
            int resultModifier = StockDao.modifierStock(stockRecherche);
            if (resultModifier == 1) {
                System.out.println("Modification du stock réussie pour le Stock ID : " + stockRecherche.getStockId());
            } else {
                System.out.println("Échec de la modification du stock.");
            }
        } else {
            System.out.println("Aucun stock trouvé pour effectuer la modification.");
        }

        // Test de la fonction getStockByVisiteur
        System.out.println("\nTest de la fonction getStockByVisiteur :");
        ArrayList<Stock> stocksParVisiteur = StockDao.getStockByVisiteur(matriculeTest);
        if (!stocksParVisiteur.isEmpty()) {
            System.out.println("Stocks pour le visiteur " + matriculeTest + " :");
            for (Stock stock : stocksParVisiteur) {
                System.out.println("- Stock ID : " + stock.getStockId() + ", Médicament : " + stock.getMedDepotLegal() + ", Quantité : " + stock.getStock());
            }
        } else {
            System.out.println("Aucun stock trouvé pour le visiteur " + matriculeTest);
        }
    }
}
