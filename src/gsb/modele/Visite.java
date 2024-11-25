package gsb.modele;

/**
 * La classe Visite représente une visite médicale d'un visiteur avec les médicaments offerts.
 * Elle contient des informations sur la référence de la visite, la date, le matricule du visiteur,
 * les médicaments offerts et leurs quantités, ainsi que des commentaires et une ville associée à la visite.
 */
public class Visite {

    // Attributs
    /**
     * Référence unique de la visite.
     */
    private String reference;
    
    /**
     * Date de la visite médicale.
     */
    private String dateVisite;
    
    /**
     * Commentaire associé à la visite médicale.
     */
    private String commentaire;
    
    /**
     * Matricule du visiteur ayant effectué la visite.
     */
    private String matricule;
    
    /**
     * Code du médicament offert pendant la visite.
     */
    private String codeMed;
    
    /**
     * Premier médicament offert lors de la visite.
     */
    private String medOffert1;
    
    /**
     * Quantité du premier médicament offert.
     */
    private int quantiteMed1;
    
    /**
     * Deuxième médicament offert lors de la visite.
     */
    private String medOffert2;
    
    /**
     * Quantité du deuxième médicament offert.
     */
    private int quantiteMed2;
    
    /**
     * Ville où la visite a eu lieu.
     */
    private String ville;

    // Constructeurs

    /**
     * Constructeur de la classe Visite permettant d'initialiser tous les attributs.
     * 
     * @param reference Référence unique de la visite.
     * @param dateVisite Date de la visite médicale.
     * @param matricule Matricule du visiteur.
     * @param codeMed Code du médicament offert.
     * @param commentaire Commentaire associé à la visite.
     * @param medOffert1 Premier médicament offert.
     * @param quantiteMed1 Quantité du premier médicament offert.
     * @param medOffert2 Deuxième médicament offert.
     * @param quantiteMed2 Quantité du deuxième médicament offert.
     */
    public Visite(String reference, String dateVisite, String matricule, String codeMed, String commentaire, 
            String medOffert1, int quantiteMed1, String medOffert2, int quantiteMed2) {
        this.reference = reference;
        this.dateVisite = dateVisite;
        this.matricule = matricule;
        this.codeMed = codeMed;
        this.commentaire = commentaire;
        this.medOffert1 = medOffert1;
        this.quantiteMed1 = quantiteMed1;
        this.medOffert2 = medOffert2;
        this.quantiteMed2 = quantiteMed2;
    }

    /**
     * Constructeur de la classe Visite permettant d'initialiser tous les attributs, y compris la ville.
     * 
     * @param reference Référence unique de la visite.
     * @param dateVisite Date de la visite médicale.
     * @param matricule Matricule du visiteur.
     * @param codeMed Code du médicament offert.
     * @param commentaire Commentaire associé à la visite.
     * @param medOffert1 Premier médicament offert.
     * @param quantiteMed1 Quantité du premier médicament offert.
     * @param medOffert2 Deuxième médicament offert.
     * @param quantiteMed2 Quantité du deuxième médicament offert.
     * @param ville Ville où la visite a eu lieu.
     */
    public Visite(String reference, String dateVisite, String matricule, String codeMed, String commentaire, 
            String medOffert1, int quantiteMed1, String medOffert2, int quantiteMed2, String ville) {
        this.reference = reference;
        this.dateVisite = dateVisite;
        this.matricule = matricule;
        this.codeMed = codeMed;
        this.commentaire = commentaire;
        this.medOffert1 = medOffert1;
        this.quantiteMed1 = quantiteMed1;
        this.medOffert2 = medOffert2;
        this.quantiteMed2 = quantiteMed2;
        this.ville = ville;
    }

    /**
     * Constructeur de la classe Visite pour une version plus simple, sans ville.
     * 
     * @param reference Référence unique de la visite.
     * @param dateVisite Date de la visite médicale.
     * @param commentaire Commentaire associé à la visite.
     * @param matricule Matricule du visiteur.
     * @param codeMed Code du médicament offert.
     */
    public Visite(String reference, String dateVisite, String commentaire, String matricule, String codeMed) {
        this.reference = reference;
        this.dateVisite = dateVisite;
        this.commentaire = commentaire;
        this.matricule = matricule;
        this.codeMed = codeMed;
    }

    /**
     * Constructeur de la classe Visite avec les médicaments offerts et leur quantité.
     * 
     * @param reference Référence unique de la visite.
     * @param commentaire Commentaire associé à la visite.
     * @param medOffert1 Premier médicament offert.
     * @param quantiteMed1 Quantité du premier médicament offert.
     * @param medOffert2 Deuxième médicament offert.
     * @param quantiteMed2 Quantité du deuxième médicament offert.
     */
    public Visite(String reference, String commentaire, String medOffert1, int quantiteMed1, String medOffert2, int quantiteMed2) {
        this.reference = reference;
        this.commentaire = commentaire;
        this.medOffert1 = medOffert1;
        this.quantiteMed1 = quantiteMed1;
        this.medOffert2 = medOffert2;
        this.quantiteMed2 = quantiteMed2;
    }

    // Getters et Setters

    /**
     * Retourne la référence unique de la visite.
     * 
     * @return la référence de la visite.
     */
    public String getReference() {
        return reference;
    }

    /**
     * Définit la référence unique de la visite.
     * 
     * @param reference la référence à définir.
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * Retourne la date de la visite médicale.
     * 
     * @return la date de la visite.
     */
    public String getDateVisite() {
        return dateVisite;
    }

    /**
     * Définit la date de la visite médicale.
     * 
     * @param dateVisite la date à définir.
     */
    public void setDateVisite(String dateVisite) {
        this.dateVisite = dateVisite;
    }

    /**
     * Retourne le commentaire associé à la visite.
     * 
     * @return le commentaire de la visite.
     */
    public String getCommentaire() {
        return commentaire;
    }

    /**
     * Définit le commentaire associé à la visite.
     * 
     * @param commentaire le commentaire à définir.
     */
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    /**
     * Retourne le matricule du visiteur associé à la visite.
     * 
     * @return le matricule du visiteur.
     */
    public String getMatricule() {
        return matricule;
    }

    /**
     * Définit le matricule du visiteur associé à la visite.
     * 
     * @param matricule le matricule à définir.
     */
    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    /**
     * Retourne le code du médicament offert lors de la visite.
     * 
     * @return le code du médicament.
     */
    public String getCodeMed() {
        return codeMed;
    }

    /**
     * Définit le code du médicament offert lors de la visite.
     * 
     * @param codeMed le code du médicament à définir.
     */
    public void setCodeMed(String codeMed) {
        this.codeMed = codeMed;
    }

    /**
     * Retourne le premier médicament offert lors de la visite.
     * 
     * @return le premier médicament offert.
     */
    public String getMedOffert1() {
        return medOffert1;
    }

    /**
     * Définit le premier médicament offert lors de la visite.
     * 
     * @param medOffert1 le premier médicament à définir.
     */
    public void setMedOffert1(String medOffert1) {
        this.medOffert1 = medOffert1;
    }

    /**
     * Retourne la quantité du premier médicament offert.
     * 
     * @return la quantité du premier médicament.
     */
    public int getQuantiteMed1() {
        return quantiteMed1;
    }

    /**
     * Définit la quantité du premier médicament offert.
     * 
     * @param quantiteMed1 la quantité à définir.
     */
    public void setQuantiteMed1(int quantiteMed1) {
        this.quantiteMed1 = quantiteMed1;
    }

    /**
     * Retourne le deuxième médicament offert lors de la visite.
     * 
     * @return le deuxième médicament offert.
     */
    public String getMedOffert2() {
        return medOffert2;
    }

    /**
     * Définit le deuxième médicament offert lors de la visite.
     * 
     * @param medOffert2 le deuxième médicament à définir.
     */
    public void setMedOffert2(String medOffert2) {
        this.medOffert2 = medOffert2;
    }

    /**
     * Retourne la quantité du deuxième médicament offert.
     * 
     * @return la quantité du deuxième médicament.
     */
    public int getQuantiteMed2() {
        return quantiteMed2;
    }

    /**
     * Définit la quantité du deuxième médicament offert.
     * 
     * @param quantiteMed2 la quantité à définir.
     */
    public void setQuantiteMed2(int quantiteMed2) {
        this.quantiteMed2 = quantiteMed2;
    }

    /**
     * Retourne la ville où la visite a eu lieu.
     * 
     * @return la ville de la visite.
     */
    public String getVille() {
        return ville;
    }

    /**
     * Définit la ville où la visite a eu lieu.
     * 
     * @param ville la ville à définir.
     */
    public void setVille(String ville) {
        this.ville = ville;
    }
}
