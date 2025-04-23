package gsb.service;

import gsb.modele.Medicament;
import gsb.modele.dao.MedicamentDao;

public class MedicamentService {

    public static String ajouterMedicament(
            String depotLegal, String nomCommercial, String composition,
            String effets, String contreIndications, float prixEchantillon,
            String famCode, String famLibelle,
            String denomination, String type, String dosage, String quantite) {

        try {
            if (depotLegal == null || depotLegal.isEmpty() || depotLegal.length() >= 10) {
                return "Donnée obligatoire : dépôt légal";
            }
            if (famCode == null || famCode.length() != 3) {
                return "Le code famille doit comporter exactement 3 caractères.";
            }

            Medicament unMedicament = new Medicament(
                depotLegal, nomCommercial, composition, effets, contreIndications,
                prixEchantillon, famCode, famLibelle,
                denomination, type, dosage, quantite
            );

            int result = MedicamentDao.creer(unMedicament);
            return result > 0 ? "Médicament ajouté avec succès !" : "Erreur lors de l'ajout du médicament.";

        } catch (Exception e) {
            return e.getMessage();
        }
    }
}

