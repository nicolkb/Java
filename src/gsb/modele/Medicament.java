package gsb.modele;

/**
 * La classe Medicament représente un médicament avec ses informations de base telles que son dépôt légal,
 * son nom commercial, sa composition, ses effets, ses contre-indications, son prix d'échantillon, et sa famille.
 */
public class Medicament {
    
    // Attributs
    /**
     * Code de dépôt légal du médicament.
     */
    private String medDepotLegal;
    
    /**
     * Nom commercial du médicament.
     */
    private String medNomCommercial;
    
    /**
     * Composition du médicament (les ingrédients actifs).
     */
    private String medComposition;
    
    /**
     * Effets du médicament sur le patient.
     */
    private String medEffets;
    
    /**
     * Contre-indications liées au médicament.
     */
    private String medContreIndic;
    
    /**
     * Prix de l'échantillon du médicament.
     */
    private float medPrixEchantillon;
    
    /**
     * Code de la famille du médicament.
     */
    private String famCode;
    
    /**
     * Libellé de la famille du médicament.
     */
    private String famLibelle;
    
    private String denominationCommun;
    private String type;
    private String dosage;
    private String quantite;


    // Constructeurs

    /**
     * Constructeur principal de la classe Medicament.
     * 
     * @param medDepotLegal Code de dépôt légal du médicament.
     * @param medNomCommercial Nom commercial du médicament.
     * @param medComposition Composition du médicament.
     * @param medEffets Effets du médicament.
     * @param medContreIndic Contre-indications du médicament.
     * @param medPrixEchantillon Prix de l'échantillon du médicament.
     * @param famCode Code de la famille du médicament.
     * @param famLibelle Libellé de la famille du médicament.
     */
    public Medicament(String medDepotLegal, String medNomCommercial, String medComposition, String medEffets, 
                      String medContreIndic, float medPrixEchantillon, String famCode, String famLibelle) {
        this.medDepotLegal = medDepotLegal;
        this.medNomCommercial = medNomCommercial;
        this.medComposition = medComposition;
        this.medEffets = medEffets;
        this.medContreIndic = medContreIndic;
        this.medPrixEchantillon = medPrixEchantillon;
        this.famCode = famCode;
        this.famLibelle = famLibelle;
    }

    /**
     * Constructeur alternatif de la classe Medicament, sans informations sur le prix d'échantillon et la famille.
     * 
     * @param medDepotLegal Code de dépôt légal du médicament.
     * @param medNomCommercial Nom commercial du médicament.
     * @param medComposition Composition du médicament.
     * @param medEffets Effets du médicament.
     * @param medContreIndic Contre-indications du médicament.
     */
    public Medicament(String medDepotLegal, String medNomCommercial, String medComposition, String medEffets, 
                      String medContreIndic) {
        this.medDepotLegal = medDepotLegal;
        this.medNomCommercial = medNomCommercial;
        this.medComposition = medComposition;
        this.medEffets = medEffets;
        this.medContreIndic = medContreIndic;
    }
    public Medicament(String medDepotLegal, String medNomCommercial, String medComposition, String medEffets, 
            String medContreIndic, float medPrixEchantillon, String famCode, String famLibelle,String denominationCommun,String type,String dosage,String quantite) {
		this.medDepotLegal = medDepotLegal;
		this.medNomCommercial = medNomCommercial;
		this.medComposition = medComposition;
		this.medEffets = medEffets;
		this.medContreIndic = medContreIndic;
		this.medPrixEchantillon = medPrixEchantillon;
		this.famCode = famCode;
		this.famLibelle = famLibelle;
		this.denominationCommun = denominationCommun;
	    this.type = type;
	    this.dosage = dosage;
	    this.quantite = quantite;
		}

    // Getters et Setters

    /**
     * Retourne le code de dépôt légal du médicament.
     * 
     * @return le code de dépôt légal du médicament.
     */
    public String getMedDepotLegal() {
        return medDepotLegal;
    }

    /**
     * Définit le code de dépôt légal du médicament.
     * 
     * @param medDepotLegal le code de dépôt légal à définir.
     */
    public void setMedDepotLegal(String medDepotLegal) {
        this.medDepotLegal = medDepotLegal;
    }

    /**
     * Retourne le nom commercial du médicament.
     * 
     * @return le nom commercial du médicament.
     */
    public String getMedNomCommercial() {
        return medNomCommercial;
    }

    /**
     * Définit le nom commercial du médicament.
     * 
     * @param medNomCommercial le nom commercial à définir.
     */
    public void setMedNomCommercial(String medNomCommercial) {
        this.medNomCommercial = medNomCommercial;
    }

    /**
     * Retourne la composition du médicament.
     * 
     * @return la composition du médicament.
     */
    public String getMedComposition() {
        return medComposition;
    }

    /**
     * Définit la composition du médicament.
     * 
     * @param medComposition la composition à définir.
     */
    public void setMedComposition(String medComposition) {
        this.medComposition = medComposition;
    }

    /**
     * Retourne les effets du médicament.
     * 
     * @return les effets du médicament.
     */
    public String getMedEffets() {
        return medEffets;
    }

    /**
     * Définit les effets du médicament.
     * 
     * @param medEffets les effets à définir.
     */
    public void setMedEffets(String medEffets) {
        this.medEffets = medEffets;
    }

    /**
     * Retourne les contre-indications du médicament.
     * 
     * @return les contre-indications du médicament.
     */
    public String getMedContreIndic() {
        return medContreIndic;
    }

    /**
     * Définit les contre-indications du médicament.
     * 
     * @param medContreIndic les contre-indications à définir.
     */
    public void setMedContreIndic(String medContreIndic) {
        this.medContreIndic = medContreIndic;
    }

    /**
     * Retourne le prix de l'échantillon du médicament.
     * 
     * @return le prix de l'échantillon du médicament.
     */
    public float getMedPrixEchantillon() {
        return medPrixEchantillon;
    }

    /**
     * Définit le prix de l'échantillon du médicament.
     * 
     * @param medPrixEchantillon le prix de l'échantillon à définir.
     */
    public void setMedPrixEchantillon(float medPrixEchantillon) {
        this.medPrixEchantillon = medPrixEchantillon;
    }

    /**
     * Retourne le code de la famille du médicament.
     * 
     * @return le code de la famille du médicament.
     */
    public String getFamCode() {
        return famCode;
    }

    /**
     * Définit le code de la famille du médicament.
     * 
     * @param famCode le code de la famille à définir.
     */
    public void setFamCode(String famCode) {
        this.famCode = famCode;
    }

    /**
     * Retourne le libellé de la famille du médicament.
     * 
     * @return le libellé de la famille du médicament.
     */
    public String getFamLibelle() {
        return famLibelle;
    }

    /**
     * Définit le libellé de la famille du médicament.
     * 
     * @param famLibelle le libellé de la famille à définir.
     */
    public void setFamLibelle(String famLibelle) {
        this.famLibelle = famLibelle;
    }
    public String getDenominationCommun() { return denominationCommun; }
    public void setDenominationCommun(String denominationCommun) { this.denominationCommun = denominationCommun; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDosage() { return dosage; }
    public void setDosage(String dosage) { this.dosage = dosage; }

    public String getQuantite() { return quantite; }
    public void setQuantite(String quantite) { this.quantite = quantite; }
} 


