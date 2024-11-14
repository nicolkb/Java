package gsb.vue;

import gsb.modele.Visite;
import gsb.modele.dao.VisiteDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class JIFVisiteModif extends JInternalFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private JPanel pFormulaire;
    private JLabel JLreference, JLdateVisite, JLmatricule, JLcodeMed, JLcommentaire, JLmed1, JLquantiteMed1, JLmed2, JLquantiteMed2;
    private JComboBox<String> JCBreference;
    private JTextField JTdateVisite, JTmatricule, JTcodeMed, JTcommentaire, JTmed1, JTquantiteMed1, JTmed2, JTquantiteMed2;
    private JButton JBmodifier, JBannuler;

    public JIFVisiteModif(MenuPrincipal uneFenetreContainer) {
        super("Mise à jour d'une visite", true, true, true, true);

        // Panneau principal contenant les sous-panneaux
        pFormulaire = new JPanel();
        pFormulaire.setLayout(new BoxLayout(pFormulaire, BoxLayout.Y_AXIS));  // Layout en colonne pour chaque ligne

        // Création des composants
        JLreference = new JLabel("Référence :");
        JCBreference = new JComboBox<>();
        chargerReferences();
        JCBreference.addActionListener(this);

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

        JLmed1 = new JLabel("Médicament 1 (Dépôt légal) :");
        JTmed1 = new JTextField(20);

        JLquantiteMed1 = new JLabel("Quantité offerte 1 :");
        JTquantiteMed1 = new JTextField(20);

        JLmed2 = new JLabel("Médicament 2 (Dépôt légal) :");
        JTmed2 = new JTextField(20);

        JLquantiteMed2 = new JLabel("Quantité offerte 2 :");
        JTquantiteMed2 = new JTextField(20);

        // Ajout des sous-panneaux pour chaque ligne
        pFormulaire.add(creerLignePanel(JLreference, JCBreference));
        pFormulaire.add(creerLignePanel(JLdateVisite, JTdateVisite));
        pFormulaire.add(creerLignePanel(JLmatricule, JTmatricule));
        pFormulaire.add(creerLignePanel(JLcodeMed, JTcodeMed));
        pFormulaire.add(creerLignePanel(JLcommentaire, JTcommentaire));
        pFormulaire.add(creerLignePanel(JLmed1, JTmed1));
        pFormulaire.add(creerLignePanel(JLquantiteMed1, JTquantiteMed1));
        pFormulaire.add(creerLignePanel(JLmed2, JTmed2));
        pFormulaire.add(creerLignePanel(JLquantiteMed2, JTquantiteMed2));

        // Boutons Modifier et Annuler dans un panel distinct
        JPanel pBoutons = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JBmodifier = new JButton("Modifier");
        JBannuler = new JButton("Annuler");
        JBmodifier.addActionListener(this);
        JBannuler.addActionListener(this);
        pBoutons.add(JBmodifier);
        pBoutons.add(JBannuler);

        // Ajout des panneaux au contentPane
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(pFormulaire, BorderLayout.CENTER);
        contentPane.add(pBoutons, BorderLayout.SOUTH);
        pack();
    }

    // Méthode utilitaire pour créer une ligne avec un label et un champ
    private JPanel creerLignePanel(JLabel label, JComponent champ) {
        JPanel ligne = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ligne.add(label);
        ligne.add(champ);
        return ligne;
    }

    private void chargerReferences() {
        // Charger les références des visites dans le menu déroulant
        List<Visite> visites = VisiteDao.getAllVisites();
        for (Visite visite : visites) {
            JCBreference.addItem(visite.getReference());
        }
    }

    private void afficherDetailsVisite(String reference) {
        Visite visite = VisiteDao.getVisiteByReference(reference);
        if (visite != null) {

            JTdateVisite.setText(visite.getDateVisite());
            JTmatricule.setText(visite.getMatricule());
            JTcodeMed.setText(visite.getCodeMed());
            JTcommentaire.setText(visite.getCommentaire());
            JTmed1.setText(visite.getMedOffert1());
            JTquantiteMed1.setText(String.valueOf(visite.getQuantiteMed1()));
            JTmed2.setText(visite.getMedOffert2());
            JTquantiteMed2.setText(String.valueOf(visite.getQuantiteMed2()));
        }
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == JBmodifier) {
            // Récupérer les données des champs pour la mise à jour
            String reference = (String) JCBreference.getSelectedItem();
            String commentaire = JTcommentaire.getText();
            String medOffert1 = JTmed1.getText();
            int quantiteMed1 = Integer.parseInt(JTquantiteMed1.getText());
            String medOffert2 = JTmed2.getText();
            int quantiteMed2 = Integer.parseInt(JTquantiteMed2.getText());

            // Créer un objet Visite pour la mise à jour
            Visite visite = new Visite(reference, null, null, null, commentaire, medOffert1, quantiteMed1, medOffert2, quantiteMed2);

            // Appel du service de mise à jour
            int result = VisiteDao.mettreAJourVisite(visite);
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Visite mise à jour avec succès");
                viderChamps();
            } else {
                JOptionPane.showMessageDialog(this, "Erreur lors de la mise à jour de la visite", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else if (evt.getSource() == JBannuler) {
            viderChamps();
        } else if (evt.getSource() == JCBreference) {
            // Afficher les détails de la visite sélectionnée
            String reference = (String) JCBreference.getSelectedItem();
            afficherDetailsVisite(reference);
        }
    }

    private void viderChamps() {
        JCBreference.setSelectedIndex(-1);
        JTdateVisite.setText("");
        JTmatricule.setText("");
        JTcodeMed.setText("");
        JTcommentaire.setText("");
        JTmed1.setText("");
        JTquantiteMed1.setText("");
        JTmed2.setText("");
        JTquantiteMed2.setText("");
    }
}
