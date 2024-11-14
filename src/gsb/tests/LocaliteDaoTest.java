package gsb.tests;

import gsb.modele.Localite;
import gsb.modele.dao.LocaliteDao;

public class LocaliteDaoTest {

    public static void main(String[] args) {
        // Test de la fonction rechercher
        System.out.println("Test de la fonction rechercher :");

        // Premier test avec le code postal "75001"
        String codeLocaliteTest1 = "75001"; // Assurez-vous d'utiliser un code postal existant dans votre base pour ce test
        Localite localiteRecherchee1 = LocaliteDao.rechercher(codeLocaliteTest1);

        if (localiteRecherchee1 != null) {
            System.out.println("Localité trouvée : " + localiteRecherchee1.getCodePostal() + ", " + localiteRecherchee1.getVille());
        } else {
            System.out.println("Localité non trouvée pour le code postal : " + codeLocaliteTest1);
        }

        // Deuxième test avec le code postal "13015"
        String codeLocaliteTest2 = "13015"; // Assurez-vous d'utiliser un code postal existant dans votre base pour ce test
        Localite localiteRecherchee2 = LocaliteDao.rechercher(codeLocaliteTest2);

        if (localiteRecherchee2 != null) {
            System.out.println("Localité trouvée : " + localiteRecherchee2.getCodePostal() + ", " + localiteRecherchee2.getVille());
        } else {
            System.out.println("Localité non trouvée pour le code postal : " + codeLocaliteTest2);
        }
    }
}
