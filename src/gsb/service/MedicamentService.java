package gsb.service;
import gsb.modele.Medicament;
import gsb.modele.dao.MedicamentDao;

public class MedicamentService {
    public static String ajouterMedicament(String depotLegal, String nomCommercial, String composition, 
            String effets, String contreIndications, float prixEchantillon, 
            String famCode, String famLibelle) {
        try {
            if (depotLegal == null || depotLegal.isEmpty()) {
                return "Donnée obligatoire : dépôt légal";
            }
            if (famCode == null || famCode.length() != 3) {
                return "Le code famille doit comporter exactement 3 caractères.";
            }
            Medicament unMedicament = new Medicament(depotLegal, nomCommercial, composition, effets, contreIndications, prixEchantillon, famCode, famLibelle);
            int result = MedicamentDao.creer(unMedicament);
            return result > 0 ? "Médicament ajouté avec succès !" : "Erreur lors de l'ajout du médicament.";
        } catch (Exception e) {
            return e.getMessage(); // Retourne le message d'erreur s'il y a une exception
        }
    }
}