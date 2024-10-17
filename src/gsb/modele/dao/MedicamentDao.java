package gsb.modele.dao;
import gsb.modele.Medicament;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedicamentDao {

    // Recherche un médicament par son dépôt légal
    public static Medicament rechercher(String depotLegal) {
        Medicament unMedicament = null;
        ResultSet reqSelection = ConnexionMySql.execReqSelection(
            "SELECT * FROM MEDICAMENT WHERE MED_DEPOTLEGAL ='" + depotLegal + "'"
        );
        
        try {
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
            System.out.println("Erreur lors de la requête - SELECT * FROM MEDICAMENT WHERE MED_DEPOTLEGAL ='" + depotLegal + "'");
            e.printStackTrace();
        }
        
        ConnexionMySql.fermerConnexionBd();
        return unMedicament;
    }
    
    // Retourne une collection de tous les médicaments
    public static ArrayList<Medicament> retournerCollectionDesMedicaments() {
        ArrayList<Medicament> collectionDesMedicaments = new ArrayList<>();
        ResultSet reqSelection = ConnexionMySql.execReqSelection("SELECT MED_DEPOTLEGAL FROM MEDICAMENT");
        
        try {
            while (reqSelection.next()) {
                String depotLegal = reqSelection.getString(1);
                collectionDesMedicaments.add(MedicamentDao.rechercher(depotLegal));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur dans retournerCollectionDesMedicaments()");
        }
        
        ConnexionMySql.fermerConnexionBd();
        return collectionDesMedicaments;
    }

    // Crée un nouveau médicament
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

        // Création de la requête d'insertion
        requeteInsertion = "INSERT INTO MEDICAMENT VALUES('" + depotLegal + "','" + nomCommercial + "','" +
                composition + "','" + effets + "','" + contreIndications + "'," + prixEchantillon + ",'" +
                famCode + "','" + famLibelle + "')";

        try {
            // Exécution de la requête
            result = ConnexionMySql.execReqMaj(requeteInsertion);
        } catch (Exception e) {
            System.out.println("Echec insertion Medicament");
            e.printStackTrace();
        }

        // Fermeture de la connexion à la base de données
        ConnexionMySql.fermerConnexionBd();
        return result;
    }
}