package gsb.modele;

/**
 * La classe Stock représente l'état de stock d'un médicament pour un visiteur particulier.
 * Elle contient des informations sur le stock disponible, ainsi que l'identifiant du stock,
 * le dépôt légal du médicament et le matricule du visiteur associé.
 */
public class Stock {

    // Attributs
    /**
     * Identifiant unique du stock.
     */
    private int stockId;
    
    /**
     * Dépôt légal du médicament (code unique du médicament).
     */
    private String medDepotLegal;
    
    /**
     * Matricule du visiteur associé au stock du médicament.
     */
    private String matricule;
    
    /**
     * Quantité de médicament disponible dans le stock.
     */
    private int stock;

    // Constructeur

    /**
     * Constructeur de la classe Stock permettant d'initialiser tous les attributs.
     * 
     * @param stockId Identifiant du stock.
     * @param medDepotLegal Dépôt légal du médicament.
     * @param matricule Matricule du visiteur associé au stock.
     * @param stock Quantité de médicament disponible dans le stock.
     */
    public Stock(int stockId, String medDepotLegal, String matricule, int stock) {
        this.stockId = stockId;
        this.medDepotLegal = medDepotLegal;
        this.matricule = matricule;
        this.stock = stock;
    }

    // Getters et Setters

    /**
     * Retourne l'identifiant du stock.
     * 
     * @return l'identifiant du stock.
     */
    public int getStockId() {
        return stockId;
    }

    /**
     * Définit l'identifiant du stock.
     * 
     * @param stockId l'identifiant du stock à définir.
     */
    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    /**
     * Retourne le dépôt légal du médicament.
     * 
     * @return le dépôt légal du médicament.
     */
    public String getMedDepotLegal() {
        return medDepotLegal;
    }

    /**
     * Définit le dépôt légal du médicament.
     * 
     * @param medDepotLegal le dépôt légal du médicament à définir.
     */
    public void setMedDepotLegal(String medDepotLegal) {
        this.medDepotLegal = medDepotLegal;
    }

    /**
     * Retourne le matricule du visiteur associé au stock.
     * 
     * @return le matricule du visiteur.
     */
    public String getMatricule() {
        return matricule;
    }

    /**
     * Définit le matricule du visiteur associé au stock.
     * 
     * @param matricule le matricule à définir.
     */
    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    /**
     * Retourne la quantité de médicament disponible dans le stock.
     * 
     * @return la quantité de médicament en stock.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Définit la quantité de médicament disponible dans le stock.
     * 
     * @param stock la quantité de médicament à définir.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
}