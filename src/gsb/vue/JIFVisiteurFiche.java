package gsb.vue;

import gsb.modele.Visiteur;

public class JIFVisiteurFiche extends JIFVisiteur {
	
    public JIFVisiteurFiche(MenuPrincipal uneFenetreContainer, Visiteur unVisiteur) {
        super(uneFenetreContainer); // passe MenuPrincipal au constructeur parent
        this.remplirText(unVisiteur);
    }

}