package gsb.vue;

import gsb.modele.Stock;
import gsb.modele.Visite;
import gsb.modele.dao.VisiteDao;
import gsb.modele.dao.StockDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class JIFVisiteModif extends JInternalFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private JPanel pFormulaire;
    private JLabel JLreference, JLdateVisite, JLmatricule, JLcodeMed, JLcommentaire, JLmed1, JLquantiteMed1, JLmed2, JLquantiteMed2;
    private JComboBox<String> JCBreference, JCBmed1, JCBmed2;
    private JTextField JTdateVisite, JTmatricule, JTcodeMed, JTcommentaire, JTquantiteMed1, JTquantiteMed2;
    private JButton JBmodifier, JBannuler;

    public JIFVisiteModif(MenuPrincipal uneFenetreContainer) {
        super("Mise à jour d'une visite", true, true, true, true);

        // Panneau principal contenant les sous-panneaux
        pFormulaire = new JPanel();
        pFormulaire.setLayout(new BoxLayout(pFormulaire, BoxLayout.Y_AXIS));

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
        JCBmed1 = new JComboBox<>();
        JCBmed1.addItem("Aucun"); // Option par défaut
        JCBmed1.addActionListener(e -> chargerMedicamentsDansMenu(JCBmed1));

        JLquantiteMed1 = new JLabel("Quantité offerte 1 :");
        JTquantiteMed1 = new JTextField(20);

        JLmed2 = new JLabel("Médicament 2 (Dépôt légal) :");
        JCBmed2 = new JComboBox<>();
        JCBmed2.addItem("Aucun"); // Option par défaut
        JCBmed2.addActionListener(e -> chargerMedicamentsDansMenu(JCBmed2));

        JLquantiteMed2 = new JLabel("Quantité offerte 2 :");
        JTquantiteMed2 = new JTextField(20);

        // Ajout des sous-panneaux pour chaque ligne
        pFormulaire.add(creerLignePanel(JLreference, JCBreference));
        pFormulaire.add(creerLignePanel(JLdateVisite, JTdateVisite));
        pFormulaire.add(creerLignePanel(JLmatricule, JTmatricule));
        pFormulaire.add(creerLignePanel(JLcodeMed, JTcodeMed));
        pFormulaire.add(creerLignePanel(JLcommentaire, JTcommentaire));
        pFormulaire.add(creerLignePanel(JLmed1, JCBmed1));
        pFormulaire.add(creerLignePanel(JLquantiteMed1, JTquantiteMed1));
        pFormulaire.add(creerLignePanel(JLmed2, JCBmed2));
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

    private JPanel creerLignePanel(JLabel label, JComponent champ) {
        JPanel ligne = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ligne.add(label);
        ligne.add(champ);
        return ligne;
    }

    private void chargerReferences() {
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
        }

        JCBmed1.removeAllItems();
        JCBmed1.addItem("Aucun");
        JCBmed2.removeAllItems();
        JCBmed2.addItem("Aucun");
    }

    private void chargerMedicamentsDansMenu(JComboBox<String> comboBox) {
        if (comboBox.getItemCount() > 1) {
            return; // Évite de recharger si déjà chargé
        }

        List<Stock> stocks = StockDao.getStockByVisiteur(JTmatricule.getText());
        for (Stock stock : stocks) {
            comboBox.addItem(stock.getMedDepotLegal());
        }
    }

    public void actionPerformed(ActionEvent evt) {
    	if (evt.getSource() == JBmodifier) {
    	    try {
    	        String reference = (String) JCBreference.getSelectedItem();

    	        if (reference == null || reference.isEmpty()) {
    	            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une référence de visite.", "Erreur", JOptionPane.ERROR_MESSAGE);
    	            return;
    	        }

    	        String commentaire = JTcommentaire.getText();
    	        String medOffert1 = "Aucun".equals(JCBmed1.getSelectedItem()) ? null : (String) JCBmed1.getSelectedItem();
    	        String medOffert2 = "Aucun".equals(JCBmed2.getSelectedItem()) ? null : (String) JCBmed2.getSelectedItem();

    	        Integer quantiteMed1 = JTquantiteMed1.getText().isEmpty() ? 0 : Integer.parseInt(JTquantiteMed1.getText());
    	        Integer quantiteMed2 = JTquantiteMed2.getText().isEmpty() ? 0 : Integer.parseInt(JTquantiteMed2.getText());

    	        boolean modificationEffectuee = false;

    	        // Mise à jour du commentaire
    	        if (commentaire != null && !commentaire.isEmpty()) {
    	            VisiteDao.mettreAJourCommentaire(reference, commentaire);
    	            modificationEffectuee = true;
    	        }

    	        // Vérification des médicaments et des quantités
    	        if (commentaire != null && !commentaire.isEmpty()) { // Les modifications des médicaments nécessitent un commentaire
    	            if (medOffert1 != null) {
    	                if (quantiteMed1 <= 0) {
    	                    JOptionPane.showMessageDialog(this, "Veuillez entrer une quantité valide pour le médicament 1.", "Erreur", JOptionPane.ERROR_MESSAGE);
    	                    return;
    	                }
    	                if (!mettreAJourStock(JTmatricule.getText(), medOffert1, quantiteMed1)) {
    	                    JOptionPane.showMessageDialog(this, "Stock insuffisant pour le médicament 1.", "Erreur de stock", JOptionPane.ERROR_MESSAGE);
    	                    return;
    	                }
    	                VisiteDao.mettreAJourMedOffert1(reference, medOffert1, quantiteMed1);
    	                modificationEffectuee = true;
    	            }

    	            if (medOffert2 != null) {
    	                if (quantiteMed2 <= 0) {
    	                    JOptionPane.showMessageDialog(this, "Veuillez entrer une quantité valide pour le médicament 2.", "Erreur", JOptionPane.ERROR_MESSAGE);
    	                    return;
    	                }
    	                if (!mettreAJourStock(JTmatricule.getText(), medOffert2, quantiteMed2)) {
    	                    JOptionPane.showMessageDialog(this, "Stock insuffisant pour le médicament 2.", "Erreur de stock", JOptionPane.ERROR_MESSAGE);
    	                    return;
    	                }
    	                VisiteDao.mettreAJourMedOffert2(reference, medOffert2, quantiteMed2);
    	                modificationEffectuee = true;
    	            }
    	        }

    	        // Résultat de la modification
    	        if (modificationEffectuee) {
    	            JOptionPane.showMessageDialog(this, "Visite mise à jour avec succès.");
    	        } else {
    	            JOptionPane.showMessageDialog(this, "Aucune modification effectuée car le commentaire est vide.", "Avertissement", JOptionPane.WARNING_MESSAGE);
    	        }

    	        viderChamps();

    	    } catch (NumberFormatException e) {
    	        JOptionPane.showMessageDialog(this, "Veuillez entrer un nombre valide pour les quantités.", "Erreur", JOptionPane.ERROR_MESSAGE);
    	    }
    	} else if (evt.getSource() == JBannuler) {
    	    viderChamps();
    	} else if (evt.getSource() == JCBreference) {
    	    String reference = (String) JCBreference.getSelectedItem();
    	    afficherDetailsVisite(reference);
    	}

    }

    private boolean mettreAJourStock(String matricule, String medDepotLegal, Integer quantite) {
        if (medDepotLegal == null || quantite == null) {
            return true; // Aucun médicament ou quantité, rien à faire
        }

        Stock stock = StockDao.getStockByVisiteurEtMedicament(matricule, medDepotLegal);
        if (stock == null || stock.getStock() < quantite) {
            return false; // Stock insuffisant ou non trouvé
        }

        stock.setStock(stock.getStock() - quantite);
        int resultat = StockDao.modifierStock(stock);
        return resultat > 0;
    }

    private void viderChamps() {
        JCBreference.setSelectedIndex(-1);
        JTdateVisite.setText("");
        JTmatricule.setText("");
        JTcodeMed.setText("");
        JTcommentaire.setText("");
        JCBmed1.removeAllItems();
        JCBmed1.addItem("Aucun");
        JCBmed2.removeAllItems();
        JCBmed2.addItem("Aucun");
        JTquantiteMed1.setText("");
        JTquantiteMed2.setText("");
    }
}
