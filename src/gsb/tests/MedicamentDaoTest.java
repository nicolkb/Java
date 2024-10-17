package gsb.tests;
import gsb.modele.Medicament;
import gsb.modele.dao.MedicamentDao;
import java.util.ArrayList;

public class MedicamentDaoTest {

    public static void main(String[] args) {
        // Test de la fonction creer
        System.out.println("Test de la fonction creer :");
        Medicament nouveauMedicament = new Medicament(
            "A123",
            "Doliprane",
            "Paracétamol",
            "Soulage la douleur",
            "Pas indications",
            2.5f,
            "C01",
            "Antidouleur"
        );
        
        int resultCreer = MedicamentDao.creer(nouveauMedicament);
        if (resultCreer == 1) {
            System.out.println("Insertion du médicament réussie.");
        } else {
            System.out.println("Échec de l'insertion du médicament.");
        }

        // Test de la fonction rechercher
        System.out.println("\nTest de la fonction rechercher :");
        Medicament medicamentRecherche = MedicamentDao.rechercher("3MYC7");
        if (medicamentRecherche != null) {
            System.out.println("Médicament trouvé : " + medicamentRecherche.getMedNomCommercial());
        } else {
            System.out.println("Médicament non trouvé.");
        }

        // Test de la fonction retournerCollectionDesMedicaments
        System.out.println("\nTest de la fonction retournerCollectionDesMedicaments :");
        ArrayList<Medicament> collectionMedicament = MedicamentDao.retournerCollectionDesMedicaments();
        if (!collectionMedicament.isEmpty()) {
            System.out.println("Liste des médicaments :");
            for (Medicament med : collectionMedicament) {
                System.out.println("- " + med.getMedNomCommercial());
            }
        } else {
            System.out.println("Aucun médicament trouvé dans la base.");
        }
    }
}