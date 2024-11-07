package gsb.modele.dao;

import gsb.modele.Visiteur;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class VisiteurDao {
    public static int creerVisiteur(Visiteur visiteur) {
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
        return ConnexionMySql.execReqMaj(requete);
    }

    public static Visiteur selectionnerVisiteur(String matricule) {
        Visiteur visiteur = null;
        String requete = "SELECT * FROM VISITEUR WHERE MATRICULE = '" + matricule + "'";
        ResultSet rs = ConnexionMySql.execReqSelection(requete);
        
        try {
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
            System.out.println("Erreur lors de la sélection du visiteur : " + e.getMessage());
        }
        return visiteur;
    }
    public static ArrayList<Visiteur> retournerCollectionDesVisiteurs() {
        ArrayList<Visiteur> lesVisiteurs = new ArrayList<>();
        String requete = "SELECT * FROM VISITEUR";
        ResultSet rs = ConnexionMySql.execReqSelection(requete);

        try {
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
                lesVisiteurs.add(visiteur);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des visiteurs : " + e.getMessage());
        }

        return lesVisiteurs;
    }
}