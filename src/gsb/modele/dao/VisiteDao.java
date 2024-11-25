package gsb.modele.dao;

import gsb.modele.Visite;
import gsb.service.VisiteService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VisiteDao {

    /**
     * Récupère toutes les visites enregistrées dans la base de données.
     * @return Liste de toutes les visites.
     */
    public static List<Visite> getAllVisites() {
        List<Visite> listeVisites = new ArrayList<>();
        String query = "SELECT * FROM VISITE";
        
        try (ResultSet rs = ConnexionMySql.execReqSelection(query)) {
            // Parcours du résultat de la requête pour ajouter chaque visite à la liste
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
                listeVisites.add(visite); // Ajout de la visite à la liste
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la récupération des visites.");
        } finally {
            ConnexionMySql.fermerConnexionBd(); // Fermeture de la connexion à la base de données
        }

        return listeVisites; // Retourne la liste des visites
    }

    /**
     * Récupère tous les matricules des visiteurs ayant effectué une visite.
     * @return Liste des matricules.
     */
    public static List<String> getMatriculesAvecVisites() {
        List<String> matricules = new ArrayList<>();
        String query = "SELECT DISTINCT MATRICULE FROM VISITE";
        
        try (ResultSet rs = ConnexionMySql.execReqSelection(query)) {
            // Parcours des résultats pour ajouter chaque matricule à la liste
            while (rs.next()) {
                matricules.add(rs.getString("MATRICULE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnexionMySql.fermerConnexionBd(); // Fermeture de la connexion à la base de données
        }

        return matricules; // Retourne la liste des matricules
    }

    /**
     * Récupère toutes les visites avec les villes associées aux médecins.
     * @return Liste des visites avec les villes des médecins.
     */
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
        
        try (ResultSet rs = ConnexionMySql.execReqSelection(query)) {
            // Parcours du résultat de la requête pour ajouter chaque visite à la liste
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
                    rs.getString("VILLE")
                );
                listeVisites.add(visite); // Ajout de la visite à la liste
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnexionMySql.fermerConnexionBd(); // Fermeture de la connexion à la base de données
        }

        return listeVisites; // Retourne la liste des visites avec les villes
    }

    /**
     * Ajoute une nouvelle visite dans la base de données.
     * @param visite L'objet Visite à ajouter.
     * @return Le nombre de lignes affectées par la requête (1 si la visite a été ajoutée avec succès, sinon 0).
     */
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
        
        int result = ConnexionMySql.execReqMaj(query); // Exécution de la requête de modification
        ConnexionMySql.fermerConnexionBd(); // Fermeture de la connexion
        return result; // Retourne le résultat de l'insertion
    }

    /**
     * Ajoute une nouvelle visite avec un nombre limité d'informations.
     * @param visite L'objet Visite à ajouter.
     * @return Le nombre de lignes affectées par la requête (1 si la visite a été ajoutée avec succès, sinon 0).
     */
    public static int ajouteVisite(Visite visite) {
        // Appeler la méthode de validation de VisiteService
        String validationMessage = VisiteService.validerVisite(visite);

        // Si un message d'erreur est retourné, lever une exception
        if (!validationMessage.equals("Valide")) {
            throw new IllegalArgumentException(validationMessage);
        }

        // Construction de la requête SQL pour insérer la visite
        String query = "INSERT INTO VISITE (REFERENCE, DATEVISITE, COMMENTAIRE, MATRICULE, CODEMED) VALUES ('" 
                + visite.getReference() + "', '" 
                + visite.getDateVisite() + "', '" 
                + visite.getCommentaire() + "', '" 
                + visite.getMatricule() + "', '" 
                + visite.getCodeMed() + "')";
        
        // Exécution de la requête de modification
        int result = ConnexionMySql.execReqMaj(query);
        ConnexionMySql.fermerConnexionBd(); // Fermeture de la connexion à la base de données
        
        return result; // Retourner le résultat de l'insertion
    }


    /**
     * Met à jour le commentaire d'une visite existante.
     * @param reference La référence de la visite à modifier.
     * @param commentaire Le nouveau commentaire.
     * @return Le nombre de lignes affectées par la requête (1 si la mise à jour est effectuée, sinon 0).
     */
    public static int mettreAJourCommentaire(String reference, String commentaire) {
        String query = "UPDATE VISITE SET COMMENTAIRE = '" + commentaire + "' " 
                     + "WHERE REFERENCE = '" + reference + "'";
        int result = ConnexionMySql.execReqMaj(query); // Exécution de la requête de modification
        ConnexionMySql.fermerConnexionBd(); // Fermeture de la connexion
        return result; // Retourne le résultat de la mise à jour
    }

    /**
     * Met à jour le premier médicament offert d'une visite.
     * @param reference La référence de la visite à modifier.
     * @param medOffert1 Le code du premier médicament offert.
     * @param quantiteMed1 La quantité du premier médicament offert.
     * @return Le nombre de lignes affectées par la requête (1 si la mise à jour est effectuée, sinon 0).
     */
    public static int mettreAJourMedOffert1(String reference, String medOffert1, Integer quantiteMed1) {
        String query = "UPDATE VISITE SET MED_OFFERT_1 = '" + medOffert1 + "', " 
                     + "QUANTITE_MED_1 = " + (quantiteMed1 != null ? quantiteMed1 : "NULL") + " " 
                     + "WHERE REFERENCE = '" + reference + "'";
        int result = ConnexionMySql.execReqMaj(query); // Exécution de la requête de modification
        ConnexionMySql.fermerConnexionBd(); // Fermeture de la connexion
        return result; // Retourne le résultat de la mise à jour
    }

    /**
     * Met à jour le second médicament offert d'une visite.
     * @param reference La référence de la visite à modifier.
     * @param medOffert2 Le code du second médicament offert.
     * @param quantiteMed2 La quantité du second médicament offert.
     * @return Le nombre de lignes affectées par la requête (1 si la mise à jour est effectuée, sinon 0).
     */
    public static int mettreAJourMedOffert2(String reference, String medOffert2, Integer quantiteMed2) {
        String query = "UPDATE VISITE SET MED_OFFERT_2 = '" + medOffert2 + "', " 
                     + "QUANTITE_MED_2 = " + (quantiteMed2 != null ? quantiteMed2 : "NULL") + " " 
                     + "WHERE REFERENCE = '" + reference + "'";
        int result = ConnexionMySql.execReqMaj(query); // Exécution de la requête de modification
        ConnexionMySql.fermerConnexionBd(); // Fermeture de la connexion
        return result; // Retourne le résultat de la mise à jour
    }

    /**
     * Récupère une visite en fonction de sa référence.
     * 
     * @param reference La référence unique de la visite.
     * @return L'objet Visite correspondant à la référence donnée, ou null si aucune visite n'est trouvée.
     */
    public static Visite getVisiteByReference(String reference) {
        Visite visite = null;
        // Requête SQL pour récupérer les informations d'une visite par sa référence
        String query = "SELECT * FROM VISITE WHERE REFERENCE = '" + reference + "'";

        ResultSet rs = ConnexionMySql.execReqSelection(query); // Exécution de la requête

        try {
            // Si une ligne est trouvée pour la référence donnée
            if (rs.next()) {
                // Récupération des données de la visite à partir du ResultSet
                String dateVisite = rs.getString("DATEVISITE");
                String matricule = rs.getString("MATRICULE");
                String codeMed = rs.getString("CODEMED");
                String commentaire = rs.getString("COMMENTAIRE");
                String medOffert1 = rs.getString("MED_OFFERT_1");
                int quantiteMed1 = rs.getInt("QUANTITE_MED_1");
                String medOffert2 = rs.getString("MED_OFFERT_2");
                int quantiteMed2 = rs.getInt("QUANTITE_MED_2");

                // Initialisation de l'objet Visite avec les données récupérées
                visite = new Visite(reference, dateVisite, matricule, codeMed, commentaire, medOffert1, quantiteMed1, medOffert2, quantiteMed2);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // En cas d'erreur SQL, on affiche l'exception
        } finally {
            ConnexionMySql.fermerConnexionBd(); // Fermeture de la connexion à la base de données
        }

        return visite; // Retourne l'objet Visite ou null si non trouvé
    }

    /**
     * Récupère les visites d'un visiteur en fonction de son matricule et de la date de la visite.
     * 
     * @param matricule Le matricule du visiteur.
     * @param dateVisite La date de la visite.
     * @return Une liste de visites correspondant aux critères spécifiés.
     */
    public static List<Visite> getVisitesByMatriculeAndDate(String matricule, String dateVisite) {
        List<Visite> listeVisites = new ArrayList<>();
        // Requête SQL pour récupérer les visites d'un visiteur pour une date donnée
        String query = """
            SELECT VISITE.REFERENCE, VISITE.DATEVISITE, VISITE.MATRICULE, VISITE.CODEMED, VISITE.COMMENTAIRE,
                   VISITE.MED_OFFERT_1, VISITE.QUANTITE_MED_1, VISITE.MED_OFFERT_2, VISITE.QUANTITE_MED_2,
                   LOCALITE.VILLE
            FROM VISITE
            JOIN MEDECIN ON VISITE.CODEMED = MEDECIN.CODEMED
            JOIN LOCALITE ON MEDECIN.CODEPOSTAL = LOCALITE.CODEPOSTAL
            WHERE VISITE.MATRICULE = '""" + matricule + "' AND VISITE.DATEVISITE = '" + dateVisite + "'";

        ResultSet rs = ConnexionMySql.execReqSelection(query); // Exécution de la requête

        try {
            // Parcours des résultats et ajout de chaque visite dans la liste
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
                    rs.getString("VILLE") // Ville du médecin
                );
                listeVisites.add(visite); // Ajout de la visite à la liste
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gestion des erreurs SQL
        } finally {
            ConnexionMySql.fermerConnexionBd(); // Fermeture de la connexion à la base de données
        }

        return listeVisites; // Retourne la liste des visites
    }

    /**
     * Récupère la liste des dates de visite distinctes dans la base de données.
     * 
     * @return Une liste des dates de visite disponibles, triées par date.
     */
    public static List<String> getDatesVisite() {
        List<String> datesVisite = new ArrayList<>();
        // Requête SQL pour obtenir toutes les dates distinctes de visite
        String query = "SELECT DISTINCT DATEVISITE FROM VISITE ORDER BY DATEVISITE";

        ResultSet rs = ConnexionMySql.execReqSelection(query); // Exécution de la requête

        try {
            // Parcours des résultats et ajout des dates à la liste
            while (rs.next()) {
                datesVisite.add(rs.getString("DATEVISITE"));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gestion des erreurs SQL
        } finally {
            ConnexionMySql.fermerConnexionBd(); // Fermeture de la connexion à la base de données
        }

        return datesVisite; // Retourne la liste des dates de visite
    }
}