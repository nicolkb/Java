package gsb.modele;

public class Stock {

    private int stockId;
    private String medDepotLegal;
    private String matricule;
    private int stock;

    // Constructeur
    public Stock(int stockId, String medDepotLegal, String matricule, int stock) {
        this.stockId = stockId;
        this.medDepotLegal = medDepotLegal;
        this.matricule = matricule;
        this.stock = stock;
    }
    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public String getMedDepotLegal() {
        return medDepotLegal;
    }

    public void setMedDepotLegal(String medDepotLegal) {
        this.medDepotLegal = medDepotLegal;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}