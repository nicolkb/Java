package gsb.tests;

import gsb.modele.Visite;
import gsb.service.VisiteService;

/**
 * Classe de test pour le service VisiteService.
 * Permet de vérifier les validations et la logique métier liées à l'ajout de visites.
 */
public class VisiteServiceTest {

    /**
     * Méthode principale pour exécuter les tests sur le service VisiteService.
     * 
     * @param args Arguments de la ligne de commande.
     */
    public static void main(String[] args) {
        // Test 1 : Cas valide
        System.out.println("Test 1 : Cas valide");
        Visite visiteValide = new Visite("12345", "2024-11-12", "Commentaire", "MATR001", "MED001");
        String resultatValide = VisiteService.validerVisite(visiteValide);
        System.out.println("Résultat attendu : Valide");
        System.out.println("Résultat obtenu : " + resultatValide);
        System.out.println();

        // Test 2 : Référence invalide (moins de 5 caractères)
        System.out.println("Test 2 : Référence invalide (moins de 5 caractères)");
        Visite visiteRefCourte = new Visite("1234", "2024-11-12", "Commentaire", "MATR001", "MED001");
        String resultatRefCourte = VisiteService.validerVisite(visiteRefCourte);
        System.out.println("Résultat attendu : La référence doit comporter exactement 5 caractères.");
        System.out.println("Résultat obtenu : " + resultatRefCourte);
        System.out.println();

        // Test 3 : Date invalide
        System.out.println("Test 3 : Date invalide");
        Visite visiteDateInvalide = new Visite("12345", "12-11-2024", "Commentaire", "MATR001", "MED001");
        String resultatDateInvalide = VisiteService.validerVisite(visiteDateInvalide);
        System.out.println("Résultat attendu : La date de visite doit être au format 'YYYY-MM-DD'.");
        System.out.println("Résultat obtenu : " + resultatDateInvalide);
        System.out.println();

        // Test 4 : Matricule visiteur manquant
        System.out.println("Test 4 : Matricule visiteur manquant");
        Visite visiteSansMatricule = new Visite("12345", "2024-11-12", "Commentaire", null, "MED001");
        String resultatSansMatricule = VisiteService.validerVisite(visiteSansMatricule);
        System.out.println("Résultat attendu : Le matricule du visiteur est obligatoire.");
        System.out.println("Résultat obtenu : " + resultatSansMatricule);
        System.out.println();

        // Test 5 : Code médecin manquant
        System.out.println("Test 5 : Code médecin manquant");
        Visite visiteSansCodeMed = new Visite("12345", "2024-11-12", "Commentaire", "MATR001", null);
        String resultatSansCodeMed = VisiteService.validerVisite(visiteSansCodeMed);
        System.out.println("Résultat attendu : Le code médecin est obligatoire.");
        System.out.println("Résultat obtenu : " + resultatSansCodeMed);
        System.out.println();
    }
}
