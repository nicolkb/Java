package gsb.modele.dao;

import gsb.modele.Localite;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe d'accès aux données pour les localités.
 */
public class LocaliteDao {
    
    /**
     * Recherche une localité par son code postal.
     * 
     * @param codeLocalite Code postal de la localité.
     * @return Objet Localite correspondant au code postal.
     */
    public static Localite rechercher(String codeLocalite) {
        Localite uneLocalite = null;
        ResultSet reqSelection = ConnexionMySql.execReqSelection("select * from LOCALITE where CODEPOSTAL='" + codeLocalite + "'");
        try {
            if (reqSelection.next()) {
                uneLocalite = new Localite(reqSelection.getString(1), reqSelection.getString(2));
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la requête SELECT pour la localité : " + codeLocalite);
            e.printStackTrace();
        }
        ConnexionMySql.fermerConnexionBd();
        return uneLocalite;
    }

    /**
     * Récupère la liste des codes postaux disponibles dans la table LOCALITE.
     * 
     * @return Liste des codes postaux sous forme de chaînes de caractères.
     */
    public static List<String> getAllCodePostaux() {
        List<String> codesPostaux = new ArrayList<>();
        ResultSet reqSelection = ConnexionMySql.execReqSelection("SELECT CODEPOSTAL FROM LOCALITE ORDER BY CODEPOSTAL");
        try {
            while (reqSelection.next()) {
                codesPostaux.add(reqSelection.getString("CODEPOSTAL"));
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des codes postaux.");
            e.printStackTrace();
        } finally {
            ConnexionMySql.fermerConnexionBd();
        }
        return codesPostaux;
    }
}
