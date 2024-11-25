package gsb.modele.dao;

import gsb.modele.Visiteur;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VisiteurDao {

    /**
     * Crée un nouveau visiteur dans la base de données.
     * 
     * @param visiteur L'objet Visiteur à insérer dans la base.
     * @return Le nombre de lignes affectées par la requête d'insertion (1 si succès, 0 sinon).
     */
    public static int creerVisiteur(Visiteur visiteur) {
        // Requête d'insertion dans la table VISITEUR
        String requete = "INSERT INTO VISITEUR (MATRICULE, NOM, PRENOM, LOGIN, MDP, ADRESSE, CODEPOSTAL, DATEENTREE, CODEUNIT, NOMUNIT) VALUES ('"
                + visiteur.getMatricule() + "', '"
                + visiteur.getNom() + "', '"
                + visiteur.getPrenom() + "', '"
                + visiteur.getLogin() + "', '"
                + visiteur.getMdp() + "', '"
                + visiteur.getAdresse() + "', '"
                + visiteur.getCodePostal() + "', '"
                + new java.sql.Timestamp(visiteur.getDateEntree().getTime()) + "', '"
                + visiteur.getCodeUnit() + "', '"
                + visiteur.getNomUnit() + "')";
        // Exécution de la requête d'insertion et retour du résultat
        return ConnexionMySql.execReqMaj(requete);
    }

    /**
     * Sélectionne un visiteur de la base de données en fonction de son matricule.
     * 
     * @param matricule Le matricule du visiteur à rechercher.
     * @return Un objet Visiteur correspondant à ce matricule, ou null si non trouvé.
     */
    public static Visiteur selectionnerVisiteur(String matricule) {
        Visiteur visiteur = null;
        // Requête pour récupérer les informations du visiteur par matricule
        String requete = "SELECT * FROM VISITEUR WHERE MATRICULE = '" + matricule + "'";
        // Exécution de la requête et récupération du résultat
        ResultSet rs = ConnexionMySql.execReqSelection(requete);
        
        try {
            // Si le visiteur est trouvé, on crée un objet Visiteur
            if (rs.next()) {
                visiteur = new Visiteur(
                    rs.getString("MATRICULE"),
                    rs.getString("NOM"),
                    rs.getString("PRENOM"),
                    rs.getString("LOGIN"),
                    rs.getString("MDP"),
                    rs.getString("ADRESSE"),
                    rs.getString("CODEPOSTAL"),
                    rs.getTimestamp("DATEENTREE"),
                    rs.getString("CODEUNIT"),
                    rs.getString("NOMUNIT")
                );
            }
        } catch (SQLException e) {
            // Gestion des erreurs liées à la sélection du visiteur
            System.out.println("Erreur lors de la sélection du visiteur : " + e.getMessage());
        }
        return visiteur;
    }

    /**
     * Retourne une collection de tous les visiteurs de la base de données.
     * 
     * @return Une liste de tous les visiteurs présents dans la table VISITEUR.
     */
    public static ArrayList<Visiteur> retournerCollectionDesVisiteurs() {
        ArrayList<Visiteur> lesVisiteurs = new ArrayList<>();
        // Requête pour récupérer tous les visiteurs
        String requete = "SELECT * FROM VISITEUR";
        // Exécution de la requête et récupération des résultats
        ResultSet rs = ConnexionMySql.execReqSelection(requete);

        try {
            // Parcours des résultats pour créer un objet Visiteur pour chaque ligne
            while (rs.next()) {
                Visiteur visiteur = new Visiteur(
                    rs.getString("MATRICULE"),
                    rs.getString("NOM"),
                    rs.getString("PRENOM"),
                    rs.getString("LOGIN"),
                    rs.getString("MDP"),
                    rs.getString("ADRESSE"),
                    rs.getString("CODEPOSTAL"),
                    rs.getTimestamp("DATEENTREE"),
                    rs.getString("CODEUNIT"),
                    rs.getString("NOMUNIT")
                );
                // Ajout du visiteur à la liste
                lesVisiteurs.add(visiteur);
            }
        } catch (SQLException e) {
            // Gestion des erreurs lors de la récupération des visiteurs
            System.out.println("Erreur lors de la récupération des visiteurs : " + e.getMessage());
        }

        return lesVisiteurs;
    }

    /**
     * Récupère la liste de tous les matricules des visiteurs.
     * 
     * @return Une liste contenant les matricules de tous les visiteurs.
     */
    public static List<String> getAllMatricules() {
        List<String> matricules = new ArrayList<>();
        try {
            // Requête pour récupérer les matricules des visiteurs
            String requete = "SELECT MATRICULE FROM VISITEUR";
            ResultSet resultSet = ConnexionMySql.execReqSelection(requete);
            // Parcours des résultats et ajout des matricules à la liste
            while (resultSet.next()) {
                matricules.add(resultSet.getString("MATRICULE"));
            }
            resultSet.close();
        } catch (SQLException e) {
            // Gestion des erreurs lors de la récupération des matricules
            e.printStackTrace();
        }
        return matricules;
    }
}
