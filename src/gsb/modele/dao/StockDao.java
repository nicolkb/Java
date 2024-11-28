package gsb.modele.dao;

import gsb.modele.Stock;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

public class StockDao {

    /**
     * Récupère tous les stocks dans la base de données.
     * 
     * @return Une liste d'objets Stock contenant toutes les entrées de la table STOCK.
     */
    public static ArrayList<Stock> getAllStocks() {
        ArrayList<Stock> stocks = new ArrayList<>();
        // Requête SQL pour récupérer tous les stocks
        String requete = "SELECT * FROM STOCK";
        try {
            ResultSet resultat = ConnexionMySql.execReqSelection(requete);
            // Parcours des résultats pour créer des objets Stock
            while (resultat.next()) {
                int stockId = resultat.getInt("STOCK_ID");
                String medDepotLegal = resultat.getString("MED_DEPOTLEGAL");
                String matricule = resultat.getString("MATRICULE");
                int stock = resultat.getInt("QUANTITE");

                // Création d'un objet Stock et ajout à la liste
                Stock s = new Stock(stockId, medDepotLegal, matricule, stock);
                stocks.add(s);
            }
            // Fermeture de la connexion à la base de données
            ConnexionMySql.fermerConnexionBd();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la récupération des stocks.");
        }
        return stocks;
    }

    /**
     * Ajoute un stock pour un visiteur et un médicament spécifiés. 
     * Si un enregistrement existe déjà, la quantité est mise à jour.
     * 
     * @param matricule Le matricule du visiteur.
     * @param depotLegal Le code du médicament.
     * @param quantite La quantité à ajouter.
     * @return true si l'ajout ou la mise à jour a réussi, false sinon.
     */
    public static boolean ajouterStock(String matricule, String depotLegal, int quantite) {
        try {
            // Vérifie si le stock existe déjà pour ce visiteur et ce médicament
            String requeteVerif = "SELECT * FROM STOCK WHERE MATRICULE = '" + matricule + "' AND MED_DEPOTLEGAL = '" + depotLegal + "'";
            ResultSet resultSet = ConnexionMySql.execReqSelection(requeteVerif);

            if (resultSet.next()) {
                // Si l'enregistrement existe, on met à jour la quantité
                int quantiteExistante = resultSet.getInt("QUANTITE");
                int nouvelleQuantite = quantiteExistante + quantite;
                String requeteMaj = "UPDATE STOCK SET QUANTITE = " + nouvelleQuantite +
                                    " WHERE MATRICULE = '" + matricule + "' AND MED_DEPOTLEGAL = '" + depotLegal + "'";
                ConnexionMySql.execReqMaj(requeteMaj);
            } else {
                // Si l'enregistrement n'existe pas, on insère un nouveau stock
                String requeteInsertion = "INSERT INTO STOCK (MATRICULE, MED_DEPOTLEGAL, QUANTITE) VALUES ('" + matricule + "', '" + depotLegal + "', " + quantite + ")";
                ConnexionMySql.execReqMaj(requeteInsertion);
            }
            
            // Fermeture du ResultSet après l'opération
            resultSet.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Récupère le stock pour un visiteur et un médicament spécifiques.
     * 
     * @param codeVisiteur Le matricule du visiteur.
     * @param medDepotLegal Le code du médicament.
     * @return Un objet Stock contenant les informations de stock pour ce visiteur et médicament, ou null si non trouvé.
     */
    public static Stock getStockByVisiteurEtMedicament(String codeVisiteur, String medDepotLegal) {
        Stock stock = null;
        // Requête pour récupérer un stock spécifique par visiteur et médicament
        String requete = "SELECT * FROM STOCK WHERE MATRICULE = '" + codeVisiteur + "' AND MED_DEPOTLEGAL = '" + medDepotLegal + "'";
        try {
            ResultSet resultat = ConnexionMySql.execReqSelection(requete);
            if (resultat.next()) {
                // Si un stock est trouvé, on le crée
                int stockId = resultat.getInt("STOCK_ID");
                int quantite = resultat.getInt("QUANTITE");
                stock = new Stock(stockId, medDepotLegal, codeVisiteur, quantite);
            }
            // Fermeture de la connexion à la base de données
            ConnexionMySql.fermerConnexionBd();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la récupération du stock pour le visiteur et le médicament.");
        }
        return stock;
    }

    /**
     * Met à jour un stock existant dans la base de données.
     * 
     * @param stock L'objet Stock contenant les informations à mettre à jour.
     * @return Le nombre de lignes affectées par la mise à jour.
     */
    public static int modifierStock(Stock stock) {
        // Requête SQL pour mettre à jour les informations du stock
        String requete = "UPDATE STOCK SET MED_DEPOTLEGAL = '" + stock.getMedDepotLegal()
                + "', MATRICULE = '" + stock.getMatricule()
                + "', QUANTITE = " + stock.getStock()
                + " WHERE STOCK_ID = " + stock.getStockId();
        // Exécution de la mise à jour
        int resultat = ConnexionMySql.execReqMaj(requete);
        // Fermeture de la connexion
        ConnexionMySql.fermerConnexionBd();
        return resultat;
    }

    /**
     * Récupère tous les stocks d'un visiteur donné.
     * 
     * @param codeVisiteur Le matricule du visiteur.
     * @return Une liste d'objets Stock représentant les stocks pour ce visiteur.
     */
    public static ArrayList<Stock> getStockByVisiteur(String codeVisiteur) {
        ArrayList<Stock> listeStock = new ArrayList<>();
        // Requête pour récupérer tous les stocks d'un visiteur
        String requete = "SELECT STOCK_ID, MED_DEPOTLEGAL, MATRICULE, QUANTITE FROM STOCK WHERE MATRICULE = '" + codeVisiteur + "'";

        try {
            ResultSet resultat = ConnexionMySql.execReqSelection(requete);
            // Parcours des résultats pour créer des objets Stock
            while (resultat.next()) {
                int stockId = resultat.getInt("STOCK_ID");
                String medDepotLegal = resultat.getString("MED_DEPOTLEGAL");
                String matricule = resultat.getString("MATRICULE");
                int quantite = resultat.getInt("QUANTITE");

                // Création d'un objet Stock et ajout à la liste
                Stock stockObjet = new Stock(stockId, medDepotLegal, matricule, quantite);
                listeStock.add(stockObjet);
            }
            // Fermeture de la connexion à la base de données
            ConnexionMySql.fermerConnexionBd();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la récupération des stocks pour le visiteur : " + codeVisiteur);
        }
        
        return listeStock;
    }
}
