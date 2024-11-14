package gsb.vue;

import gsb.modele.Visite;

import javax.swing.*;
import java.awt.*;

public class JIFVisite extends JInternalFrame {

    private static final long serialVersionUID = 1L;

    private JPanel pFormulaire;
    private JLabel JLreference, JLdateVisite, JLmatricule, JLcodeMed, JLcommentaire, JLmed1, JLquantiteMed1, JLmed2, JLquantiteMed2;
    private JTextField JTreference, JTdateVisite, JTmatricule, JTcodeMed, JTcommentaire, JTmed1, JTquantiteMed1, JTmed2, JTquantiteMed2;

    public JIFVisite(MenuPrincipal uneFenetreContainer, Visite visite) {
        super("Détails de la Visite", true, true, true, true);
        initUI();  // Initialise l'interface utilisateur
        remplirText(visite);  // Remplit les détails de la visite
    }

    private void initUI() {
        pFormulaire = new JPanel();
        pFormulaire.setLayout(new BoxLayout(pFormulaire, BoxLayout.Y_AXIS));

        // Création des composants
        JLreference = new JLabel("Référence :");
        JTreference = new JTextField(20);
        JTreference.setEditable(false);

        JLdateVisite = new JLabel("Date visite :");
        JTdateVisite = new JTextField(20);
        JTdateVisite.setEditable(false);

        JLmatricule = new JLabel("Matricule visiteur :");
        JTmatricule = new JTextField(20);
        JTmatricule.setEditable(false);

        JLcodeMed = new JLabel("Code Médecin :");
        JTcodeMed = new JTextField(20);
        JTcodeMed.setEditable(false);

        JLcommentaire = new JLabel("Commentaire :");
        JTcommentaire = new JTextField(20);
        JTcommentaire.setEditable(false);

        JLmed1 = new JLabel("Médicament 1 (Dépôt légal) :");
        JTmed1 = new JTextField(20);
        JTmed1.setEditable(false);

        JLquantiteMed1 = new JLabel("Quantité offerte 1 :");
        JTquantiteMed1 = new JTextField(20);
        JTquantiteMed1.setEditable(false);

        JLmed2 = new JLabel("Médicament 2 (Dépôt légal) :");
        JTmed2 = new JTextField(20);
        JTmed2.setEditable(false);

        JLquantiteMed2 = new JLabel("Quantité offerte 2 :");
        JTquantiteMed2 = new JTextField(20);
        JTquantiteMed2.setEditable(false);

        // Ajout des sous-panneaux pour chaque ligne
        pFormulaire.add(creerLignePanel(JLreference, JTreference));
        pFormulaire.add(creerLignePanel(JLdateVisite, JTdateVisite));
        pFormulaire.add(creerLignePanel(JLmatricule, JTmatricule));
        pFormulaire.add(creerLignePanel(JLcodeMed, JTcodeMed));
        pFormulaire.add(creerLignePanel(JLcommentaire, JTcommentaire));
        pFormulaire.add(creerLignePanel(JLmed1, JTmed1));
        pFormulaire.add(creerLignePanel(JLquantiteMed1, JTquantiteMed1));
        pFormulaire.add(creerLignePanel(JLmed2, JTmed2));
        pFormulaire.add(creerLignePanel(JLquantiteMed2, JTquantiteMed2));

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(pFormulaire, BorderLayout.CENTER);
        pack();
    }

    // Remplit les champs texte avec les données de l'objet Visite
    public void remplirText(Visite visite) {
        JTreference.setText(visite.getReference());
        JTdateVisite.setText(visite.getDateVisite());
        JTmatricule.setText(visite.getMatricule());
        JTcodeMed.setText(visite.getCodeMed());
        JTcommentaire.setText(visite.getCommentaire());
        JTmed1.setText(visite.getMedOffert1());
        JTquantiteMed1.setText(String.valueOf(visite.getQuantiteMed1()));
        JTmed2.setText(visite.getMedOffert2());
        JTquantiteMed2.setText(String.valueOf(visite.getQuantiteMed2()));
    }

    private JPanel creerLignePanel(JLabel label, JComponent champ) {
        JPanel ligne = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ligne.add(label);
        ligne.add(champ);
        return ligne;
    }
}

