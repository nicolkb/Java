package gsb.vue;

import javax.swing.*;
import gsb.modele.Visite;
import gsb.modele.dao.VisiteDao;
import gsb.modele.dao.VisiteurDao;
import gsb.modele.dao.MedecinDao;
import gsb.modele.dao.MedicamentDao;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class JIFVisiteAjout extends JInternalFrame {
    private static final long serialVersionUID = 1L;
    private JTextField txtReference;
    private JComboBox<String> cbMatricule;  // Menu déroulant pour les matricules
    private JComboBox<String> cbCodeMed;    // Menu déroulant pour les codes médecins
    private JTextField txtDateVisite;
    private JTextArea txtCommentaire;
    private JButton btnAjouter;

    public JIFVisiteAjout(MenuPrincipal uneFenetreContainer) {
        setTitle("Ajouter une Visite");
        setSize(400, 300);
        setLayout(new BorderLayout());

        // Panel pour les champs d'entrée
        JPanel panelChamps = new JPanel(new GridLayout(5, 2, 5, 5));

        // Champ Référence de la Visite
        panelChamps.add(new JLabel("Référence de la Visite:"));
        txtReference = new JTextField();
        panelChamps.add(txtReference);

        // Champ Matricule du Visiteur (menu déroulant)
        panelChamps.add(new JLabel("Matricule du Visiteur:"));
        cbMatricule = new JComboBox<>();
        remplirComboBoxMatricules();
        panelChamps.add(cbMatricule);

        // Champ Code Médecin (menu déroulant)
        panelChamps.add(new JLabel("Code Médecin:"));
        cbCodeMed = new JComboBox<>();
        remplirComboBoxCodesMed();
        panelChamps.add(cbCodeMed);

        // Champ Date de Visite
        panelChamps.add(new JLabel("Date de Visite (YYYY-MM-DD):"));
        txtDateVisite = new JTextField();
        panelChamps.add(txtDateVisite);

        // Champ Commentaire
        panelChamps.add(new JLabel("Commentaire:"));
        txtCommentaire = new JTextArea(3, 20);
        txtCommentaire.setEditable(false);

        JScrollPane scrollPaneCommentaire = new JScrollPane(txtCommentaire);
        panelChamps.add(scrollPaneCommentaire);

        // Bouton Ajouter
        btnAjouter = new JButton("Ajouter");
        JPanel panelBouton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBouton.add(btnAjouter);

        // Ajout des panels à la fenêtre
        add(panelChamps, BorderLayout.CENTER);
        add(panelBouton, BorderLayout.SOUTH);

        // Gestion de l'événement pour le bouton Ajouter
        btnAjouter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ajouterVisite();
            }
        });

        setVisible(true);
    }

    // Remplit le JComboBox des matricules des visiteurs
    private void remplirComboBoxMatricules() {
        List<String> matricules = VisiteurDao.getAllMatricules();
        for (String matricule : matricules) {
            cbMatricule.addItem(matricule);
        }
    }

    // Remplit le JComboBox des codes médecins
    private void remplirComboBoxCodesMed() {
        List<String> codesMed = MedecinDao.getAllCode();
        for (String codeMed : codesMed) {
            cbCodeMed.addItem(codeMed);
        }
    }

    private void ajouterVisite() {
        String reference = txtReference.getText();
        String matricule = (String) cbMatricule.getSelectedItem();
        String codemed = (String) cbCodeMed.getSelectedItem();
        String dateVisite = txtDateVisite.getText();
        String commentaire = txtCommentaire.getText();

        // Validation des champs
        if (reference.isEmpty() || matricule == null || codemed == null || dateVisite.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs obligatoires.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Création de l'objet Visite
        Visite nouvelleVisite = new Visite(reference, dateVisite, commentaire, matricule, codemed);

        // Appel à la méthode de DAO pour ajouter la visite
        int ajoutReussi = VisiteDao.ajouteVisite(nouvelleVisite);
        if (ajoutReussi > 0) {
            JOptionPane.showMessageDialog(this, "Visite ajoutée avec succès!");
            // Optionnel : vider les champs après l'ajout
            txtReference.setText("");
            cbMatricule.setSelectedIndex(-1);
            cbCodeMed.setSelectedIndex(-1);
            txtDateVisite.setText("");
            txtCommentaire.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout de la visite.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}

