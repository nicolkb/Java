package gsb.service;

import gsb.modele.Visite;
import gsb.modele.dao.VisiteDao;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Service pour la gestion des visites, incluant des validations et des appels au DAO.
 */
public class VisiteService {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Ajoute une nouvelle visite après validation des données.
     * 
     * @param reference Référence unique de la visite (5 caractères).
     * @param dateVisite Date de la visite au format "YYYY-MM-DD".
     * @param commentaire Commentaire lié à la visite.
     * @param matricule Matricule du visiteur.
     * @param codeMed Code du médecin.
     * @return Message indiquant le résultat de l'opération.
     */
    public static String validerVisite(Visite visite) {
        // Validation de la référence
        if (visite.getReference() == null || visite.getReference().isEmpty() || visite.getReference().length() != 5) {
            return "La référence doit comporter exactement 5 caractères.";
        }

        // Validation de la date
        if (!isValidDate(visite.getDateVisite())) {
            return "La date de visite doit être au format 'YYYY-MM-DD'.";
        }

        // Validation des champs obligatoires
        if (visite.getMatricule() == null || visite.getMatricule().isEmpty()) {
            return "Le matricule du visiteur est obligatoire.";
        }
        if (visite.getCodeMed() == null || visite.getCodeMed().isEmpty()) {
            return "Le code médecin est obligatoire.";
        }

        return "Valide"; // Aucun problème détecté
    }

    private static boolean isValidDate(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}