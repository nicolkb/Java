package gsb.modele.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import gsb.modele.Visite;

public class VisiteDao {

    // Méthode pour récupérer toutes les visites
    public static List<Visite> getAllVisites() {
        List<Visite> listeVisites = new ArrayList<>();
        String query = "SELECT * FROM VISITE";
        ResultSet rs = ConnexionMySql.execReqSelection(query);

        try {
            while (rs.next()) {
                Visite visite = new Visite(
                    rs.getString("REFERENCE"),
                    rs.getString("DATEVISITE"),
                    rs.getString("MATRICULE"),
                    rs.getString("CODEMED"),
                    rs.getString("COMMENTAIRE"),
                    rs.getString("MED_OFFERT_1"),
                    rs.getInt("QUANTITE_MED_1"),
                    rs.getString("MED_OFFERT_2"),
                    rs.getInt("QUANTITE_MED_2")
                );
                listeVisites.add(visite);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la récupération des visites.");
        } finally {
            ConnexionMySql.fermerConnexionBd();
        }

        return listeVisites;
    }
    public static List<String> getMatriculesAvecVisites() {
        List<String> matricules = new ArrayList<>();
        String query = "SELECT DISTINCT MATRICULE FROM VISITE";
        ResultSet rs = ConnexionMySql.execReqSelection(query);

        try {
            while (rs.next()) {
                matricules.add(rs.getString("MATRICULE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnexionMySql.fermerConnexionBd();
        }

        return matricules;
    }
    public static List<Visite> getAllVisitesAvecVille() {
        List<Visite> listeVisites = new ArrayList<>();
        String query = """
            SELECT VISITE.REFERENCE, VISITE.DATEVISITE, VISITE.MATRICULE, VISITE.CODEMED, VISITE.COMMENTAIRE,
                   VISITE.MED_OFFERT_1, VISITE.QUANTITE_MED_1, VISITE.MED_OFFERT_2, VISITE.QUANTITE_MED_2,
                   LOCALITE.VILLE
            FROM VISITE
            JOIN MEDECIN ON VISITE.CODEMED = MEDECIN.CODEMED
            JOIN LOCALITE ON MEDECIN.CODEPOSTAL = LOCALITE.CODEPOSTAL
        """;

        ResultSet rs = ConnexionMySql.execReqSelection(query);

        try {
            while (rs.next()) {
                Visite visite = new Visite(
                    rs.getString("REFERENCE"),
                    rs.getString("DATEVISITE"),
                    rs.getString("MATRICULE"),
                    rs.getString("CODEMED"),
                    rs.getString("COMMENTAIRE"),
                    rs.getString("MED_OFFERT_1"),
                    rs.getInt("QUANTITE_MED_1"),
                    rs.getString("MED_OFFERT_2"),
                    rs.getInt("QUANTITE_MED_2"),
                    rs.getString("VILLE") // Ville incluse
                );
                listeVisites.add(visite);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnexionMySql.fermerConnexionBd();
        }

        return listeVisites;
    }
    

    // Méthode pour ajouter une visite
    public static int ajouterVisite(Visite visite) {
        String query = "INSERT INTO VISITE (REFERENCE, DATEVISITE, COMMENTAIRE, MATRICULE, CODEMED, MED_OFFERT_1, QUANTITE_MED_1, MED_OFFERT_2, QUANTITE_MED_2) VALUES ('" 
                + visite.getReference() + "', '" 
                + visite.getDateVisite() + "', '" 
                + visite.getCommentaire() + "', '" 
                + visite.getMatricule() + "', '" 
                + visite.getCodeMed() + "', '" 
                + visite.getMedOffert1() + "', " 
                + visite.getQuantiteMed1() + ", '" 
                + visite.getMedOffert2() + "', " 
                + visite.getQuantiteMed2() + ")";
        int result = ConnexionMySql.execReqMaj(query);
        ConnexionMySql.fermerConnexionBd();
        return result;
    }
    public static int ajouteVisite(Visite visite) {
        String query = "INSERT INTO VISITE (REFERENCE, DATEVISITE, COMMENTAIRE, MATRICULE, CODEMED) VALUES ('" 
                + visite.getReference() + "', '" 
                + visite.getDateVisite() + "', '" 
                + visite.getCommentaire() + "', '" 
                + visite.getMatricule() + "', '" 
                + visite.getCodeMed() + "')";
        int result = ConnexionMySql.execReqMaj(query);
        ConnexionMySql.fermerConnexionBd();
        return result;
    }


    // Méthode pour mettre à jour une visite
    public static int mettreAJourVisite(Visite visite) {
        String query = "UPDATE VISITE SET COMMENTAIRE = '" + visite.getCommentaire() 
                + "', MED_OFFERT_1 = '" + visite.getMedOffert1() 
                + "', QUANTITE_MED_1 = " + visite.getQuantiteMed1() 
                + ", MED_OFFERT_2 = '" + visite.getMedOffert2() 
                + "', QUANTITE_MED_2 = " + visite.getQuantiteMed2() 
                + " WHERE REFERENCE = '" + visite.getReference() + "'";
        int result = ConnexionMySql.execReqMaj(query);
        ConnexionMySql.fermerConnexionBd();
        return result;
    }
    public static Visite getVisiteByReference(String reference) {
        Visite visite = null;
        String query = "SELECT * FROM VISITE WHERE REFERENCE = '" + reference + "'";

        ResultSet rs = ConnexionMySql.execReqSelection(query);

        try {
            if (rs.next()) {
                // Récupération des données du ResultSet
                String dateVisite = rs.getString("DATEVISITE");
                String matricule = rs.getString("MATRICULE");
                String codeMed = rs.getString("CODEMED");
                String commentaire = rs.getString("COMMENTAIRE");
                String medOffert1 = rs.getString("MED_OFFERT_1");
                int quantiteMed1 = rs.getInt("QUANTITE_MED_1");
                String medOffert2 = rs.getString("MED_OFFERT_2");
                int quantiteMed2 = rs.getInt("QUANTITE_MED_2");

                // Initialisation de l'objet Visite avec les informations exactes
                visite = new Visite(reference, dateVisite, matricule, codeMed, commentaire, medOffert1, quantiteMed1, medOffert2, quantiteMed2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnexionMySql.fermerConnexionBd();
        }

        return visite;
    }
    public static List<Visite> getVisitesByMatriculeAndDate(String matricule, String dateVisite) {
        List<Visite> listeVisites = new ArrayList<>();
        String query = """
            SELECT VISITE.REFERENCE, VISITE.DATEVISITE, VISITE.MATRICULE, VISITE.CODEMED, VISITE.COMMENTAIRE,
                   VISITE.MED_OFFERT_1, VISITE.QUANTITE_MED_1, VISITE.MED_OFFERT_2, VISITE.QUANTITE_MED_2,
                   LOCALITE.VILLE
            FROM VISITE
            JOIN MEDECIN ON VISITE.CODEMED = MEDECIN.CODEMED
            JOIN LOCALITE ON MEDECIN.CODEPOSTAL = LOCALITE.CODEPOSTAL
            WHERE VISITE.MATRICULE = '""" + matricule + "' AND VISITE.DATEVISITE = '" + dateVisite + "'";

        ResultSet rs = ConnexionMySql.execReqSelection(query);

        try {
            while (rs.next()) {
                Visite visite = new Visite(
                    rs.getString("REFERENCE"),
                    rs.getString("DATEVISITE"),
                    rs.getString("MATRICULE"),
                    rs.getString("CODEMED"),
                    rs.getString("COMMENTAIRE"),
                    rs.getString("MED_OFFERT_1"),
                    rs.getInt("QUANTITE_MED_1"),
                    rs.getString("MED_OFFERT_2"),
                    rs.getInt("QUANTITE_MED_2"),
                    rs.getString("VILLE") // Ville incluse
                );
                listeVisites.add(visite);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnexionMySql.fermerConnexionBd();
        }

        return listeVisites;
    }
    public static List<String> getDatesVisite() {
        List<String> datesVisite = new ArrayList<>();
        String query = "SELECT DISTINCT DATEVISITE FROM VISITE ORDER BY DATEVISITE";
        ResultSet rs = ConnexionMySql.execReqSelection(query);

        try {
            while (rs.next()) {
                datesVisite.add(rs.getString("DATEVISITE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnexionMySql.fermerConnexionBd();
        }

        return datesVisite;
    }
    
}

