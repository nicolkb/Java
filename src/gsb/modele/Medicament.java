package gsb.modele;

public class Medicament {
    
    // Attributs
    private String medDepotLegal;
    private String medNomCommercial;
    private String medComposition;
    private String medEffets;
    private String medContreIndic;
    private float medPrixEchantillon;
    private String famCode;
    private String famLibelle;

    // Constructeur
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
    public Medicament(String medDepotLegal, String medNomCommercial, String medComposition, String medEffets, 
            String medContreIndic) {
		this.medDepotLegal = medDepotLegal;
		this.medNomCommercial = medNomCommercial;
		this.medComposition = medComposition;
		this.medEffets = medEffets;
		this.medContreIndic = medContreIndic;
		}

	public String getMedDepotLegal() {
		return medDepotLegal;
	}

	public void setMedDepotLegal(String medDepotLegal) {
		this.medDepotLegal = medDepotLegal;
	}

	public String getMedNomCommercial() {
		return medNomCommercial;
	}

	public void setMedNomCommercial(String medNomCommercial) {
		this.medNomCommercial = medNomCommercial;
	}

	public String getMedComposition() {
		return medComposition;
	}

	public void setMedComposition(String medComposition) {
		this.medComposition = medComposition;
	}

	public String getMedEffets() {
		return medEffets;
	}

	public void setMedEffets(String medEffets) {
		this.medEffets = medEffets;
	}

	public String getMedContreIndic() {
		return medContreIndic;
	}

	public void setMedContreIndic(String medContreIndic) {
		this.medContreIndic = medContreIndic;
	}

	public float getMedPrixEchantillon() {
		return medPrixEchantillon;
	}

	public void setMedPrixEchantillon(float medPrixEchantillon) {
		this.medPrixEchantillon = medPrixEchantillon;
	}

	public String getFamCode() {
		return famCode;
	}

	public void setFamCode(String famCode) {
		this.famCode = famCode;
	}

	public String getFamLibelle() {
		return famLibelle;
	}

	public void setFamLibelle(String famLibelle) {
		this.famLibelle = famLibelle;
	}
}