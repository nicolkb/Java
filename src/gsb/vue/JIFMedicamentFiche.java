package gsb.vue;

import gsb.modele.Medicament;

public class JIFMedicamentFiche extends JIFMedicament {
	
	public JIFMedicamentFiche(MenuPrincipal uneFenetreContainer, Medicament unMedicament) {
        super(uneFenetreContainer); // passe MenuPrincipal au constructeur parent
        this.remplirText(unMedicament);
    }

}
