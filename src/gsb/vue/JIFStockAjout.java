package gsb.vue;

import gsb.modele.Stock;
import gsb.modele.dao.StockDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import gsb.modele.dao.VisiteurDao;
import gsb.modele.dao.MedicamentDao;
import java.util.List;

public class JIFStockAjout extends JInternalFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    // Déclaration des composants
    private JPanel pFormulaire;
    private JLabel JLmatricule, JLdepotLegal, JLquantite;
    private JComboBox<String> JTmatricule, JTdepotLegal; // Listes déroulantes pour le code visiteur et le dépôt légal
    private JTextField JTquantite;
    private JButton JBajouter, JBannuler;

    // Constructeur
    public JIFStockAjout(MenuPrincipal uneFenetreContainer) {
        super("Ajouter échantillons pour un visiteur", true, true, true, true);

        // Initialisation du panneau formulaire
        pFormulaire = new JPanel(new GridLayout(4, 2, 10, 10)); // Espacement de 10 pixels entre les lignes et colonnes

        // Initialisation des labels et champs texte
        JLmatricule = new JLabel("Code visiteur :");
        JLdepotLegal = new JLabel("Dépôt légal :");
        JLquantite = new JLabel("Quantité :");

        // Initialisation des champs pour le matricule, le dépôt légal et la quantité
        JTmatricule = new JComboBox<>(); // Liste déroulante pour le matricule
        JTdepotLegal = new JComboBox<>(); // Liste déroulante pour le dépôt légal
        JTquantite = new JTextField(20);
        
        remplirComboBoxMatricule();
        remplirComboBoxDepotLegal();

        // Ajouter les labels et champs texte au panneau
        pFormulaire.add(JLmatricule);
        pFormulaire.add(JTmatricule);
        pFormulaire.add(JLdepotLegal);
        pFormulaire.add(JTdepotLegal);
        pFormulaire.add(JLquantite);
        pFormulaire.add(JTquantite);

        // Boutons Ajouter et Annuler
        JBajouter = new JButton("Ajouter quantité");
        JBannuler = new JButton("Annuler");

        JBajouter.addActionListener(this);
        JBannuler.addActionListener(this);

        // Ajout des boutons au panneau
        pFormulaire.add(JBajouter);
        pFormulaire.add(JBannuler);

        // Ajout du panneau dans la fenêtre
        Container contentPane = getContentPane();
        contentPane.add(pFormulaire, BorderLayout.CENTER);
        // Ajuste la taille de la fenêtre automatiquement
    }
    private void remplirComboBoxMatricule() {
        List<String> matricules = VisiteurDao.getAllMatricules();
        for (String matricule : matricules) {
            JTmatricule.addItem(matricule);
        }
    }

    private void remplirComboBoxDepotLegal() {
        List<String> depotLegals = MedicamentDao.getAllDepotLegals();
        for (String depotLegal : depotLegals) {
            JTdepotLegal.addItem(depotLegal);
        }
    }

    // Gestion des événements des boutons
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == JBajouter) {
            try {
                // Récupération des valeurs des champs
                String matricule = (String) JTmatricule.getSelectedItem();
                String depotLegal = (String) JTdepotLegal.getSelectedItem();
                int quantite = Integer.parseInt(JTquantite.getText());

                // Ajout de la quantité dans le stock via StockDao
                boolean ajoutReussi = StockDao.ajouterStock(matricule, depotLegal, quantite);

                // Vérification du résultat
                if (ajoutReussi) {
                    JOptionPane.showMessageDialog(this, "Quantité ajoutée avec succès !");
                    viderChamps();
                } else {
                    JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout de la quantité", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erreur : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else if (evt.getSource() == JBannuler) {
            viderChamps();
        }
    }

    // Méthode pour vider les champs de saisie
    private void viderChamps() {
        JTmatricule.setSelectedIndex(0);
        JTdepotLegal.setSelectedIndex(0);
        JTquantite.setText("");
    }
}
