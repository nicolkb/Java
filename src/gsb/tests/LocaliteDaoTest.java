package gsb.tests;

import gsb.modele.Localite;
import gsb.modele.dao.LocaliteDao;

/**
 * Classe de test pour la méthode de recherche de localités dans la base de données.
 * Cette classe permet de tester la fonctionnalité de recherche de localité
 * via le code postal en utilisant la méthode 'rechercher' de la classe LocaliteDao.
 */
public class LocaliteDaoTest {

    /**
     * Méthode principale permettant de tester la fonction rechercher de LocaliteDao.
     * Elle effectue deux tests avec différents codes postaux et affiche les résultats.
     * 
     * @param args Les arguments de la ligne de commande (non utilisés ici).
     */
    public static void main(String[] args) {
        // Test de la fonction rechercher
        System.out.println("Test de la fonction rechercher :");

        // Premier test avec le code postal "75001"
        String codeLocaliteTest1 = "75001"; // Assurez-vous d'utiliser un code postal existant dans votre base pour ce test
        // Recherche de la localité pour le code postal "75001"
        Localite localiteRecherchee1 = LocaliteDao.rechercher(codeLocaliteTest1);

        // Vérification si la localité a été trouvée
        if (localiteRecherchee1 != null) {
            System.out.println("Localité trouvée : " + localiteRecherchee1.getCodePostal() + ", " + localiteRecherchee1.getVille());
        } else {
            System.out.println("Localité non trouvée pour le code postal : " + codeLocaliteTest1);
        }

        // Deuxième test avec le code postal "13015"
        String codeLocaliteTest2 = "13015"; // Assurez-vous d'utiliser un code postal existant dans votre base pour ce test
        // Recherche de la localité pour le code postal "13015"
        Localite localiteRecherchee2 = LocaliteDao.rechercher(codeLocaliteTest2);

        // Vérification si la localité a été trouvée
        if (localiteRecherchee2 != null) {
            System.out.println("Localité trouvée : " + localiteRecherchee2.getCodePostal() + ", " + localiteRecherchee2.getVille());
        } else {
            System.out.println("Localité non trouvée pour le code postal : " + codeLocaliteTest2);
        }
    }
}
