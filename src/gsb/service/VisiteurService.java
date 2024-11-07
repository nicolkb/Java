package gsb.service;

import gsb.modele.Visiteur;
import gsb.modele.dao.VisiteurDao;
import gsb.utils.ValidationUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VisiteurService {

    private static final SimpleDateFormat formatAffichage = new SimpleDateFormat("dd-MM-yyyy");

    public static String ajouterVisiteur(String matricule, String nom, String prenom, String login, String motDePasse, 
                                         String adresse, String codePostal, String dateEntree, 
                                         String codeUnit, String nomUnit) {
        try {
            if (matricule == null || matricule.isEmpty()) {
                return "Le matricule est obligatoire.";
            }
            if (nom == null || nom.isEmpty()) {
                return "Le nom est obligatoire.";
            }
            if (prenom == null || prenom.isEmpty()) {
                return "Le prénom est obligatoire.";
            }
            if (login == null || login.isEmpty()) {
                return "Le login est obligatoire.";
            }
            if (motDePasse == null || motDePasse.length() < 8) {
                return "Le mot de passe doit comporter au moins 8 caractères.";
            }
            if (!ValidationUtils.isCodePostalValide(codePostal)) {
                return "Le code postal est invalide.";
            }

            Date dateEntreeFormat;
            try {
                dateEntreeFormat = new SimpleDateFormat("dd-MM-yyyy").parse(dateEntree);
            } catch (ParseException e) {
                return "La date d'entrée est invalide. Format attendu : jj/mm/aaaa.";
            }

            // Création de l'objet Visiteur
            Visiteur unVisiteur = new Visiteur(matricule, nom, prenom, login, motDePasse, adresse, codePostal, dateEntreeFormat, codeUnit, nomUnit);

            // Insertion du visiteur dans la base de données
            int result = VisiteurDao.creerVisiteur(unVisiteur);

            // Si l'ajout est un succès, retourne un message avec la date formatée
            if (result > 0) {
                return "Visiteur ajouté avec succès ! Date d'entrée : " + formatAffichage.format(dateEntreeFormat);
            } else {
                return "Erreur lors de l'ajout du visiteur.";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}