package gsb.tests;

import gsb.modele.Localite;

/**
 * Classe de test pour la classe Localite.
 * Cette classe permet de tester les méthodes getters et setters de la classe Localite.
 * Elle crée une instance de Localite, teste la modification de ses attributs,
 * puis vérifie si les changements ont été effectués correctement.
 */
public class LocaliteTest {

    /**
     * Méthode principale permettant de tester les getters et setters de la classe Localite.
     * Elle crée une instance de Localite, teste l'accès et la modification de ses attributs, 
     * puis vérifie les résultats des tests.
     *
     * @param args Les arguments de la ligne de commande (non utilisés ici).
     */
    public static void main(String[] args) {
        // Création d'une instance de Localite avec le code postal "75000" et la ville "Paris"
        Localite localite = new Localite("75000", "Paris");

        // Test des getters pour vérifier la récupération des valeurs initiales
        System.out.println("Code Postal (attendu: 75000) : " + localite.getCodePostal());
        System.out.println("Ville (attendu: Paris) : " + localite.getVille());

        // Modification des valeurs avec les setters
        localite.setCodePostal("69000");  // Changement du code postal en "69000"
        localite.setVille("Lyon");        // Changement de la ville en "Lyon"

        // Vérification des nouvelles valeurs après modification
        System.out.println("Code Postal après modification (attendu: 69000) : " + localite.getCodePostal());
        System.out.println("Ville après modification (attendu: Lyon) : " + localite.getVille());

        // Résultats : Vérification que les valeurs ont été correctement modifiées
        if (localite.getCodePostal().equals("69000") && localite.getVille().equals("Lyon")) {
            // Si les valeurs sont correctes, afficher un message de succès
            System.out.println("Tous les tests sont passés avec succès !");
        } else {
            // Si les valeurs ne sont pas correctes, afficher un message d'échec
            System.out.println("Certains tests ont échoué.");
        }
    }
}
