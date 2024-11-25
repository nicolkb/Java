package gsb.modele;

import java.util.Date;

/**
 * La classe Visiteur représente un visiteur avec ses informations personnelles et professionnelles,
 * telles que son matricule, son nom, son prénom, ses identifiants de connexion, son adresse, et ses informations 
 * relatives à son unité d'affectation.
 */
public class Visiteur {

    // Attributs
    /**
     * Matricule unique du visiteur.
     */
    private String matricule;
    
    /**
     * Nom du visiteur.
     */
    private String nom;
    
    /**
     * Prénom du visiteur.
     */
    private String prenom;
    
    /**
     * Identifiant de connexion du visiteur.
     */
    private String login;
    
    /**
     * Mot de passe du visiteur.
     */
    private String mdp;
    
    /**
     * Adresse postale du visiteur.
     */
    private String adresse;
    
    /**
     * Code postal de l'adresse du visiteur.
     */
    private String codePostal;
    
    /**
     * Date d'entrée du visiteur dans l'entreprise.
     */
    private Date dateEntree;
    
    /**
     * Code de l'unité d'affectation du visiteur.
     */
    private String codeUnit;
    
    /**
     * Nom de l'unité d'affectation du visiteur.
     */
    private String nomUnit;

    // Constructeur

    /**
     * Constructeur de la classe Visiteur permettant d'initialiser tous les attributs.
     * 
     * @param matricule Matricule unique du visiteur.
     * @param nom Nom du visiteur.
     * @param prenom Prénom du visiteur.
     * @param login Identifiant de connexion du visiteur.
     * @param mdp Mot de passe du visiteur.
     * @param adresse Adresse postale du visiteur.
     * @param codePostal Code postal du visiteur.
     * @param dateEntree Date d'entrée du visiteur dans l'entreprise.
     * @param codeUnit Code de l'unité d'affectation du visiteur.
     * @param nomUnit Nom de l'unité d'affectation du visiteur.
     */
    public Visiteur(String matricule, String nom, String prenom, String login, String mdp, String adresse,
                    String codePostal, Date dateEntree, String codeUnit, String nomUnit) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.mdp = mdp;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.dateEntree = dateEntree;
        this.codeUnit = codeUnit;
        this.nomUnit = nomUnit;
    }

    // Getters et Setters

    /**
     * Retourne le matricule du visiteur.
     * 
     * @return le matricule du visiteur.
     */
    public String getMatricule() {
        return matricule;
    }

    /**
     * Définit le matricule du visiteur.
     * 
     * @param matricule le matricule à définir.
     */
    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    /**
     * Retourne le nom du visiteur.
     * 
     * @return le nom du visiteur.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom du visiteur.
     * 
     * @param nom le nom à définir.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne le prénom du visiteur.
     * 
     * @return le prénom du visiteur.
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Définit le prénom du visiteur.
     * 
     * @param prenom le prénom à définir.
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Retourne l'identifiant de connexion du visiteur.
     * 
     * @return l'identifiant de connexion du visiteur.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Définit l'identifiant de connexion du visiteur.
     * 
     * @param login l'identifiant de connexion à définir.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Retourne le mot de passe du visiteur.
     * 
     * @return le mot de passe du visiteur.
     */
    public String getMdp() {
        return mdp;
    }

    /**
     * Définit le mot de passe du visiteur.
     * 
     * @param mdp le mot de passe à définir.
     */
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    /**
     * Retourne l'adresse postale du visiteur.
     * 
     * @return l'adresse postale du visiteur.
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Définit l'adresse postale du visiteur.
     * 
     * @param adresse l'adresse postale à définir.
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * Retourne le code postal de l'adresse du visiteur.
     * 
     * @return le code postal du visiteur.
     */
    public String getCodePostal() {
        return codePostal;
    }

    /**
     * Définit le code postal de l'adresse du visiteur.
     * 
     * @param codePostal le code postal à définir.
     */
    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    /**
     * Retourne la date d'entrée du visiteur dans l'entreprise.
     * 
     * @return la date d'entrée du visiteur.
     */
    public Date getDateEntree() {
        return dateEntree;
    }

    /**
     * Définit la date d'entrée du visiteur dans l'entreprise.
     * 
     * @param dateEntree la date d'entrée à définir.
     */
    public void setDateEntree(Date dateEntree) {
        this.dateEntree = dateEntree;
    }

    /**
     * Retourne le code de l'unité d'affectation du visiteur.
     * 
     * @return le code de l'unité d'affectation du visiteur.
     */
    public String getCodeUnit() {
        return codeUnit;
    }

    /**
     * Définit le code de l'unité d'affectation du visiteur.
     * 
     * @param codeUnit le code de l'unité à définir.
     */
    public void setCodeUnit(String codeUnit) {
        this.codeUnit = codeUnit;
    }

    /**
     * Retourne le nom de l'unité d'affectation du visiteur.
     * 
     * @return le nom de l'unité d'affectation du visiteur.
     */
    public String getNomUnit() {
        return nomUnit;
    }

    /**
     * Définit le nom de l'unité d'affectation du visiteur.
     * 
     * @param nomUnit le nom de l'unité à définir.
     */
    public void setNomUnit(String nomUnit) {
        this.nomUnit = nomUnit;
    }
}
