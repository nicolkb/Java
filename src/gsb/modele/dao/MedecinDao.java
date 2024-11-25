package gsb.modele.dao;

import gsb.modele.Localite;
import gsb.modele.Medecin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * La classe MedecinDao contient des méthodes pour interagir avec la base de données concernant les médecins.
 * Elle permet de rechercher un médecin par son code, de récupérer une collection ou un dictionnaire des médecins,
 * et d'obtenir tous les codes des médecins disponibles.
 * Elle utilise la classe ConnexionMySql pour exécuter des requêtes SQL.
 * 
 * @author Isabelle
 * @version 17 nov. 2021
 */
public class MedecinDao {

    /**
     * Recherche un médecin en fonction de son code.
     * 
     * @param codeMedecin Le code du médecin à rechercher.
     * @return Un objet Medecin contenant les informations du médecin, ou null si le médecin n'est pas trouvé.
     */
    public static Medecin rechercher(String codeMedecin){
        Medecin unMedecin = null;
        Localite uneLocalite = null;
        // Exécution de la requête pour récupérer le médecin par son code
        ResultSet reqSelection = ConnexionMySql.execReqSelection("select * from MEDECIN where CODEMED ='"+codeMedecin+"'");
        try {
            if (reqSelection.next()) {
                // Récupération de la localité associée au médecin
                uneLocalite = LocaliteDao.rechercher(reqSelection.getString(5));
                // Création de l'objet Medecin avec les informations récupérées
                unMedecin = new Medecin(reqSelection.getString(1), reqSelection.getString(2), reqSelection.getString(3), reqSelection.getString(4), uneLocalite, reqSelection.getString(6), reqSelection.getString(7), reqSelection.getString(8));  
            }
        } catch(Exception e) {
            // Gestion des erreurs lors de l'exécution de la requête
            System.out.println("erreur reqSelection.next() pour la requete - select * from MEDECIN where CODEMED ='"+codeMedecin+"'");
            e.printStackTrace();
        }
        // Fermeture de la connexion à la base de données
        ConnexionMySql.fermerConnexionBd();
        return unMedecin;
    }

    /**
     * Récupère tous les médecins sous forme de collection d'objets Medecin.
     * 
     * @return Une ArrayList contenant tous les objets Medecin présents dans la base de données.
     */
    public static ArrayList<Medecin> retournerCollectionDesMedecins(){
        ArrayList<Medecin> collectionDesMedecins = new ArrayList<Medecin>();
        // Exécution de la requête pour récupérer tous les codes des médecins
        ResultSet reqSelection = ConnexionMySql.execReqSelection("select CODEMED from MEDECIN");
        try {
            // Parcours des résultats pour récupérer chaque médecin
            while (reqSelection.next()) {
                String codeMedecin = reqSelection.getString(1);
                // Ajout du médecin à la collection
                collectionDesMedecins.add(MedecinDao.rechercher(codeMedecin));
            }
        } catch (SQLException e) {
            // Gestion des erreurs lors de l'exécution de la requête
            e.printStackTrace();
            System.out.println("erreur retournerCollectionDesMedecins()");
        }
        return collectionDesMedecins;
    }

    /**
     * Récupère tous les médecins sous forme de dictionnaire, avec le code du médecin comme clé.
     * 
     * @return Un HashMap contenant les médecins, où la clé est le code du médecin et la valeur est l'objet Medecin.
     */
    public static HashMap<String, Medecin> retournerDictionnaireDesMedecins(){
        HashMap<String, Medecin> diccoDesMedecins = new HashMap<String, Medecin>();
        // Exécution de la requête pour récupérer tous les codes des médecins
        ResultSet reqSelection = ConnexionMySql.execReqSelection("select CODEMED from MEDECIN");
        try {
            // Parcours des résultats pour ajouter chaque médecin dans le dictionnaire
            while (reqSelection.next()) {
                String codeMedecin = reqSelection.getString(1);
                diccoDesMedecins.put(codeMedecin, MedecinDao.rechercher(codeMedecin));
            }
        } catch (SQLException e) {
            // Gestion des erreurs lors de l'exécution de la requête
            e.printStackTrace();
            System.out.println("erreur retournerDiccoDesMedecins()");
        }
        return diccoDesMedecins;
    }

    /**
     * Récupère tous les codes des médecins présents dans la base de données.
     * 
     * @return Une liste contenant tous les codes des médecins.
     */
    public static List<String> getAllCode() {
        List<String> depotLegals = new ArrayList<>();
        try {
            // Requête pour récupérer les codes des médecins
            String requete = "SELECT CODEMED FROM MEDECIN";
            ResultSet resultSet = ConnexionMySql.execReqSelection(requete);
            // Parcours des résultats pour ajouter les codes dans la liste
            while (resultSet.next()) {
                depotLegals.add(resultSet.getString("CODEMED"));
            }
            resultSet.close();
        } catch (SQLException e) {
            // Gestion des erreurs lors de l'exécution de la requête
            e.printStackTrace();
        }
        return depotLegals;
    }
}
