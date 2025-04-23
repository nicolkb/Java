package gsb.modele.dao;

import gsb.modele.Medicament;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicamentDao {

    /**
     * Recherche un médicament dans la base de données à partir de son dépôt légal.
     * 
     * @param depotLegal Le dépôt légal du médicament à rechercher.
     * @return Un objet Medicament correspondant à ce dépôt légal, ou null si non trouvé.
     */
    public static Medicament rechercher(String depotLegal) {
        Medicament unMedicament = null;
        // Exécution de la requête pour récupérer les informations du médicament
        ResultSet reqSelection = ConnexionMySql.execReqSelection(
            "SELECT * FROM MEDICAMENT WHERE MED_DEPOTLEGAL ='" + depotLegal + "'"
        );
        
        try {
            // Si un résultat est trouvé, on crée un objet Medicament avec les données récupérées
            if (reqSelection.next()) {
                unMedicament = new Medicament(
                    reqSelection.getString("MED_DEPOTLEGAL"),
                    reqSelection.getString("MED_NOMCOMMERCIAL"),
                    reqSelection.getString("MED_COMPOSITION"),
                    reqSelection.getString("MED_EFFETS"),
                    reqSelection.getString("MED_CONTREINDIC"),
                    reqSelection.getFloat("MED_PRIXECHANTILLON"),
                    reqSelection.getString("FAM_CODE"),
                    reqSelection.getString("FAM_LIBELLE")
                );
            }
        } catch (SQLException e) {
            // Gestion des erreurs de la requête
            System.out.println("Erreur lors de la requête - SELECT * FROM MEDICAMENT WHERE MED_DEPOTLEGAL ='" + depotLegal + "'");
            e.printStackTrace();
        }
        
        // Fermeture de la connexion à la base de données
        ConnexionMySql.fermerConnexionBd();
        return unMedicament;
    }
    
    /**
     * Retourne une collection de tous les médicaments dans la base de données.
     * 
     * @return Une liste de tous les médicaments.
     */
    public static ArrayList<Medicament> retournerCollectionDesMedicaments() {
        ArrayList<Medicament> collectionDesMedicaments = new ArrayList<>();
        // Exécution de la requête pour récupérer tous les médicaments
        ResultSet reqSelection = ConnexionMySql.execReqSelection("SELECT MED_DEPOTLEGAL FROM MEDICAMENT");
        
        try {
            // Parcours des résultats et ajout de chaque médicament à la collection
            while (reqSelection.next()) {
                String depotLegal = reqSelection.getString(1);
                collectionDesMedicaments.add(MedicamentDao.rechercher(depotLegal));
            }
        } catch (SQLException e) {
            // Gestion des erreurs lors de la récupération des médicaments
            e.printStackTrace();
            System.out.println("Erreur dans retournerCollectionDesMedicaments()");
        }
        
        // Fermeture de la connexion à la base de données
        ConnexionMySql.fermerConnexionBd();
        return collectionDesMedicaments;
    }

    /**
     * Crée un nouveau médicament dans la base de données.
     * 
     * @param unMedicament L'objet Medicament à insérer dans la base.
     * @return Le nombre de lignes affectées par la requête d'insertion (1 si succès, 0 sinon).
     */
    public static int creer(Medicament unMedicament) {
        int result = 0;
        String requeteInsertion;

        // Récupération des attributs du médicament
        String depotLegal = unMedicament.getMedDepotLegal();
        String nomCommercial = unMedicament.getMedNomCommercial();
        String composition = unMedicament.getMedComposition();
        String effets = unMedicament.getMedEffets();
        String contreIndications = unMedicament.getMedContreIndic();
        float prixEchantillon = unMedicament.getMedPrixEchantillon();
        String famCode = unMedicament.getFamCode();
        String famLibelle = unMedicament.getFamLibelle();
        String denomination = unMedicament.getDenominationCommun();
        String type = unMedicament.getType();
        String dosage = unMedicament.getDosage();
        String quantite = unMedicament.getQuantite();

        // Création de la requête d'insertion
        requeteInsertion = "INSERT INTO MEDICAMENT (MED_DEPOTLEGAL, MED_NOMCOMMERCIAL, MED_COMPOSITION, MED_EFFETS, " +
            "MED_CONTREINDIC, MED_PRIXECHANTILLON, FAM_CODE, FAM_LIBELLE, denominationCommun, type, dosage, quantité) VALUES('" +
            depotLegal + "','" + nomCommercial + "','" + composition + "','" + effets + "','" +
            contreIndications + "'," + prixEchantillon + ",'" + famCode + "','" + famLibelle + "','" +
            denomination + "','" + type + "','" + dosage + "','" + quantite + "')";

        try {
            // Exécution de la requête
            result = ConnexionMySql.execReqMaj(requeteInsertion);
        } catch (Exception e) {
            System.out.println("Échec insertion Medicament");
            e.printStackTrace();
        }

        ConnexionMySql.fermerConnexionBd();
        return result;
    }

    /**
     * Retourne la liste des familles de médicaments distinctes.
     * 
     * @return Une liste contenant les familles de médicaments sous forme de chaîne de caractères "FAM_CODE - FAM_LIBELLE".
     */
    public static List<String> retournerToutesLesFamilles() {
        List<String> familles = new ArrayList<>();
        String sql = "SELECT DISTINCT FAM_CODE, FAM_LIBELLE FROM MEDICAMENT";

        // Exécution de la requête pour récupérer les familles
        ResultSet rs = ConnexionMySql.execReqSelection(sql);
        
        try {
            while (rs != null && rs.next()) {
                // Ajouter "FAM_CODE - FAM_LIBELLE" à la liste
                String famCodeLibelle = rs.getString("FAM_CODE") + " - " + rs.getString("FAM_LIBELLE");
                familles.add(famCodeLibelle);
            }
        } catch (SQLException e) {
            // Gestion des erreurs lors de la récupération des familles
            e.printStackTrace();
        } finally {
            // Fermeture de la connexion à la base de données
            ConnexionMySql.fermerConnexionBd();  
        }
        
        return familles;
    }

    /**
     * Recherche des médicaments dans la base de données en fonction de leur famille.
     * 
     * @param famCode Le code de la famille de médicaments à rechercher.
     * @return Une liste des médicaments correspondant à cette famille.
     */
    public static List<Medicament> rechercherParFamille(String famCode) {
        List<Medicament> medicaments = new ArrayList<>();
        String sql = "SELECT MED_DEPOTLEGAL, MED_NOMCOMMERCIAL, MED_COMPOSITION, MED_EFFETS, MED_CONTREINDIC " +
                     "FROM MEDICAMENT WHERE FAM_CODE = '" + famCode + "'";

        // Exécution de la requête pour récupérer les médicaments de cette famille
        ResultSet rs = ConnexionMySql.execReqSelection(sql);
        
        try {
            while (rs != null && rs.next()) {
                // Création d'un objet Medicament pour chaque ligne de résultat
                Medicament medicament = new Medicament(
                    rs.getString("MED_DEPOTLEGAL"),
                    rs.getString("MED_NOMCOMMERCIAL"),
                    rs.getString("MED_COMPOSITION"),
                    rs.getString("MED_EFFETS"),
                    rs.getString("MED_CONTREINDIC")
                );
                // Ajout de chaque médicament à la liste
                medicaments.add(medicament);
            }
        } catch (SQLException e) {
            // Gestion des erreurs lors de la récupération des médicaments
            e.printStackTrace();
        } finally {
            // Fermeture de la connexion à la base de données
            ConnexionMySql.fermerConnexionBd();  
        }

        return medicaments;
    }

    /**
     * Retourne la liste des dépôts légaux de tous les médicaments dans la base de données.
     * 
     * @return Une liste de tous les dépôts légaux des médicaments.
     */
    public static List<String> getAllDepotLegals() {
        List<String> depotLegals = new ArrayList<>();
        try {
            String requete = "SELECT MED_DEPOTLEGAL FROM MEDICAMENT";
            ResultSet resultSet = ConnexionMySql.execReqSelection(requete);
            
            while (resultSet.next()) {
                depotLegals.add(resultSet.getString("MED_DEPOTLEGAL"));
            }
            
            resultSet.close();
        } catch (SQLException e) {
            // Gestion des erreurs lors de la récupération des dépôts légaux
            e.printStackTrace();
        }
        return depotLegals;
    }
    public static List<String> retournerToutesLesDenominations() {
        List<String> denominations = new ArrayList<>();
        ResultSet reqSelection = ConnexionMySql.execReqSelection("SELECT DISTINCT denominationCommun FROM MEDICAMENT WHERE denominationCommun IS NOT NULL");

        try {
            while (reqSelection.next()) {
                denominations.add(reqSelection.getString("denominationCommun"));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des dénominations : " + e.getMessage());
        }

        ConnexionMySql.fermerConnexionBd();
        return denominations;
    }
    public static List<Medicament> rechercherParDenomination(String denomination) {
        List<Medicament> liste = new ArrayList<>();
        ResultSet reqSelection = ConnexionMySql.execReqSelection(
            "SELECT * FROM MEDICAMENT WHERE denominationCommun = '" + denomination + "'"
        );

        try {
            while (reqSelection.next()) {
                Medicament m = new Medicament(
                    reqSelection.getString("MED_DEPOTLEGAL"),
                    reqSelection.getString("MED_NOMCOMMERCIAL"),
                    reqSelection.getString("MED_COMPOSITION"),
                    reqSelection.getString("MED_EFFETS"),
                    reqSelection.getString("MED_CONTREINDIC"),
                    reqSelection.getFloat("MED_PRIXECHANTILLON"),
                    reqSelection.getString("FAM_CODE"),
                    reqSelection.getString("FAM_LIBELLE"),
                    reqSelection.getString("denominationCommun"),
                    reqSelection.getString("type"),
                    reqSelection.getString("dosage"),
                    reqSelection.getString("quantité")
                );
                liste.add(m);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des médicaments par dénomination : " + e.getMessage());
        }

        ConnexionMySql.fermerConnexionBd();
        return liste;
    }
}
