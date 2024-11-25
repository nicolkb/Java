package gsb.tests;

import gsb.modele.Visite;
import gsb.modele.dao.VisiteDao;

import java.util.List;

/**
 * Classe de test pour la classe VisiteDao.
 * Permet de tester les fonctionnalités d'accès et de manipulation des données liées aux visites.
 */
public class VisiteDaoTest {

    /**
     * Méthode principale pour exécuter les tests.
     * @param args arguments de la ligne de commande.
     */
    public static void main(String[] args) {
        // Test 1 : Récupérer toutes les visites
        System.out.println("Test 1 : Récupérer toutes les visites");
        List<Visite> toutesLesVisites = VisiteDao.getAllVisites();
        for (Visite visite : toutesLesVisites) {
            System.out.println("Référence : " + visite.getReference() + ", Date : " + visite.getDateVisite() + ", Commentaire : " + visite.getCommentaire());
        }
        System.out.println();

        // Test 2 : Récupérer les matricules des visiteurs ayant effectué des visites
        System.out.println("Test 2 : Récupérer les matricules des visiteurs ayant effectué des visites");
        List<String> matricules = VisiteDao.getMatriculesAvecVisites();
        for (String matricule : matricules) {
            System.out.println("Matricule : " + matricule);
        }
        System.out.println();

        // Test 3 : Récupérer toutes les visites avec villes
        System.out.println("Test 3 : Récupérer toutes les visites avec les villes associées");
        List<Visite> visitesAvecVilles = VisiteDao.getAllVisitesAvecVille();
        for (Visite visite : visitesAvecVilles) {
            System.out.println("Référence : " + visite.getReference() + ", Ville : " + visite.getVille());
        }
        System.out.println();

        // Test 4 : Ajouter une nouvelle visite
        System.out.println("Test 4 : Ajouter une nouvelle visite");
        Visite nouvelleVisite = new Visite(
            "V12345", 
            "2024-11-12", 
            "M456", 
            "C789", 
            "Commentaire de test", 
            "MED1", 
            10, 
            "MED2", 
            5
        );
        int resultAjout = VisiteDao.ajouterVisite(nouvelleVisite);
        System.out.println(resultAjout > 0 ? "Ajout réussi" : "Échec de l'ajout");
        System.out.println();

        // Test 5 : Mettre à jour le commentaire d'une visite
        System.out.println("Test 5 : Mettre à jour le commentaire d'une visite");
        int resultUpdateCommentaire = VisiteDao.mettreAJourCommentaire("V12345", "Nouveau commentaire");
        System.out.println(resultUpdateCommentaire > 0 ? "Mise à jour réussie" : "Échec de la mise à jour");
        System.out.println();

        // Test 6 : Récupérer une visite par référence
        System.out.println("Test 6 : Récupérer une visite par sa référence");
        Visite visiteTrouvee = VisiteDao.getVisiteByReference("V12345");
        if (visiteTrouvee != null) {
            System.out.println("Référence : " + visiteTrouvee.getReference() + ", Date : " + visiteTrouvee.getDateVisite());
        } else {
            System.out.println("Visite non trouvée");
        }
        System.out.println();

        // Test 7 : Récupérer les visites par matricule et date
        System.out.println("Test 7 : Récupérer les visites par matricule et date");
        List<Visite> visitesFiltrees = VisiteDao.getVisitesByMatriculeAndDate("M456", "2024-11-12");
        for (Visite visite : visitesFiltrees) {
            System.out.println("Référence : " + visite.getReference() + ", Code Médecin : " + visite.getCodeMed());
        }
        System.out.println();

        // Test 8 : Récupérer les dates de visite distinctes
        System.out.println("Test 8 : Récupérer les dates de visite distinctes");
        List<String> datesVisites = VisiteDao.getDatesVisite();
        for (String date : datesVisites) {
            System.out.println("Date : " + date);
        }
        System.out.println();
    }
}
