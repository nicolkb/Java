package gsb.modele.dao;

import gsb.modele.Stock;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

public class StockDao {

    // Méthode pour récupérer tous les stocks
    public static ArrayList<Stock> getAllStocks() {
        ArrayList<Stock> stocks = new ArrayList<>();
        String requete = "SELECT * FROM STOCK";
        try {
            ResultSet resultat = ConnexionMySql.execReqSelection(requete);
            while (resultat.next()) {
                int stockId = resultat.getInt("STOCK_ID");
                String medDepotLegal = resultat.getString("MED_DEPOTLEGAL");
                String matricule = resultat.getString("MATRICULE");
                int stock = resultat.getInt("QUANTITE");

                Stock s = new Stock(stockId, medDepotLegal, matricule, stock);
                stocks.add(s);
            }
            ConnexionMySql.fermerConnexionBd();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la récupération des stocks.");
        }
        return stocks;
    }

    public static boolean ajouterStock(String matricule, String depotLegal, int quantite) {
        try {
            // Vérifie si l'enregistrement existe déjà
            String requeteVerif = "SELECT * FROM STOCK WHERE MATRICULE = '" + matricule + "' AND MED_DEPOTLEGAL = '" + depotLegal + "'";
            ResultSet resultSet = ConnexionMySql.execReqSelection(requeteVerif);

            if (resultSet.next()) {
                // Enregistrement trouvé, met à jour la quantité
                int quantiteExistante = resultSet.getInt("QUANTITE");
                int nouvelleQuantite = quantiteExistante + quantite;
                String requeteMaj = "UPDATE STOCK SET QUANTITE = " + nouvelleQuantite +
                                    " WHERE MATRICULE = '" + matricule + "' AND MED_DEPOTLEGAL = '" + depotLegal + "'";
                ConnexionMySql.execReqMaj(requeteMaj);
            } else {
                // Pas d'enregistrement trouvé, insère une nouvelle ligne
                String requeteInsertion = "INSERT INTO STOCK (MATRICULE, MED_DEPOTLEGAL, QUANTITE) VALUES ('" + matricule + "', '" + depotLegal + "', " + quantite + ")";
                ConnexionMySql.execReqMaj(requeteInsertion);
            }
            
            // Fermeture du ResultSet
            resultSet.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Méthode pour récupérer le stock par visiteur et médicament
    public static Stock getStockByVisiteurEtMedicament(String codeVisiteur, String medDepotLegal) {
        Stock stock = null;
        String requete = "SELECT * FROM STOCK WHERE MATRICULE = '" + codeVisiteur + "' AND MED_DEPOTLEGAL = '" + medDepotLegal + "'";
        try {
            ResultSet resultat = ConnexionMySql.execReqSelection(requete);
            if (resultat.next()) {
                int stockId = resultat.getInt("STOCK_ID");
                int quantite = resultat.getInt("QUANTITE");
                stock = new Stock(stockId, medDepotLegal, codeVisiteur, quantite);
            }
            ConnexionMySql.fermerConnexionBd();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la récupération du stock pour le visiteur et le médicament.");
        }
        return stock;
    }

    // Méthode pour mettre à jour un stock existant
    public static int modifierStock(Stock stock) {
        String requete = "UPDATE STOCK SET MED_DEPOTLEGAL = '" + stock.getMedDepotLegal()
                + "', MATRICULE = '" + stock.getMatricule()
                + "', QUANTITE = " + stock.getStock()
                + " WHERE STOCK_ID = " + stock.getStockId();
        int resultat = ConnexionMySql.execReqMaj(requete);
        ConnexionMySql.fermerConnexionBd();
        return resultat;
    }


    public static ArrayList<Stock> getStockByVisiteur(String codeVisiteur) {
        ArrayList<Stock> listeStock = new ArrayList<>();
        String requete = "SELECT STOCK_ID, MED_DEPOTLEGAL,MATRICULE,QUANTITE FROM Stock WHERE matricule = '" + codeVisiteur + "'";

        try {
            ResultSet resultat = ConnexionMySql.execReqSelection(requete);
            while (resultat.next()) {
                int stockId = resultat.getInt("STOCK_ID");
                String medDepotLegal = resultat.getString("MED_DEPOTLEGAL");
                String matricule = resultat.getString("MATRICULE");
                int quantite = resultat.getInt("QUANTITE");

                Stock stockObjet = new Stock(stockId, medDepotLegal, matricule, quantite);
                listeStock.add(stockObjet);
            }
            ConnexionMySql.fermerConnexionBd();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la récupération des stocks pour le visiteur : " + codeVisiteur);
        }
        
        return listeStock;
    }
}
