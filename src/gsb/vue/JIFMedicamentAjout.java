package gsb.vue;

import gsb.modele.Medicament;
import gsb.modele.dao.MedicamentDao;
import gsb.service.MedicamentService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JIFMedicamentAjout extends JInternalFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    
    // Déclaration des composants
    private JPanel pFormulaire;
    private JLabel JLdepotLegal, JLnomCommercial, JLcomposition, JLeffets, JLcontreIndications, JLprixEchantillon, JLfamCode, JLfamLibelle;
    private JTextField JTdepotLegal, JTnomCommercial, JTcomposition, JTeffets, JTcontreIndications, JTprixEchantillon, JTfamCode, JTfamLibelle;
    private JButton JBajouter, JBannuler;

    // Constructeur
    public JIFMedicamentAjout(MenuPrincipal uneFenetreContainer) {
        super("Ajouter un médicament", true, true, true, true);
        
        // Initialisation du panneau formulaire
        pFormulaire = new JPanel(new GridLayout(9, 2, 10, 10)); // Espacement de 10 pixels entre les lignes et colonnes
        
        // Initialisation des labels et champs texte
        JLdepotLegal = new JLabel("Dépôt légal :");
        JLnomCommercial = new JLabel("Nom commercial :");
        JLcomposition = new JLabel("Composition :");
        JLeffets = new JLabel("Effets :");
        JLcontreIndications = new JLabel("Contre indications :");
        JLprixEchantillon = new JLabel("Prix échantillon :");
        JLfamCode = new JLabel("Code famille :");
        JLfamLibelle = new JLabel("Libellé famille :");

        JTdepotLegal = new JTextField(20);
        JTnomCommercial = new JTextField(20);
        JTcomposition = new JTextField(20);
        JTeffets = new JTextField(20);
        JTcontreIndications = new JTextField(20);
        JTprixEchantillon = new JTextField(20);
        JTfamCode = new JTextField(20);
        JTfamLibelle = new JTextField(20);

        // Ajout des labels et champs texte au panneau
        pFormulaire.add(JLdepotLegal);
        pFormulaire.add(JTdepotLegal);
        pFormulaire.add(JLnomCommercial);
        pFormulaire.add(JTnomCommercial);
        pFormulaire.add(JLcomposition);
        pFormulaire.add(JTcomposition);
        pFormulaire.add(JLeffets);
        pFormulaire.add(JTeffets);
        pFormulaire.add(JLcontreIndications);
        pFormulaire.add(JTcontreIndications);
        pFormulaire.add(JLprixEchantillon);
        pFormulaire.add(JTprixEchantillon);
        pFormulaire.add(JLfamCode);
        pFormulaire.add(JTfamCode);
        pFormulaire.add(JLfamLibelle);
        pFormulaire.add(JTfamLibelle);

        // Boutons Ajouter et Annuler
        JBajouter = new JButton("Ajouter");
        JBannuler = new JButton("Annuler");

        JBajouter.addActionListener(this);
        JBannuler.addActionListener(this);

        // Ajout des boutons au panneau
        pFormulaire.add(JBajouter);
        pFormulaire.add(JBannuler);

        // Ajout du panneau dans la fenêtre
        Container contentPane = getContentPane();
        contentPane.add(pFormulaire, BorderLayout.CENTER);
        pack(); // Ajuste la taille de la fenêtre automatiquement
    }

    // Gestion des événements des boutons
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == JBajouter) {
            try {
                // Récupération des valeurs des champs
                String depotLegal = JTdepotLegal.getText();
                String nomCommercial = JTnomCommercial.getText();
                String composition = JTcomposition.getText();
                String effets = JTeffets.getText();
                String contreIndications = JTcontreIndications.getText();
                float prixEchantillon = Float.parseFloat(JTprixEchantillon.getText());
                String famCode = JTfamCode.getText();
                String famLibelle = JTfamLibelle.getText();

                // Ajout du médicament via le service
                String result = MedicamentService.ajouterMedicament(depotLegal, nomCommercial, composition, effets,
                        contreIndications, prixEchantillon, famCode, famLibelle);
                
                // Vérification du résultat
                if (result.equals("Médicament ajouté avec succès !")) {
                    JOptionPane.showMessageDialog(this, result);
                    viderChamps();
                } else {
                    // Si le médicament n'est pas ajouté, afficher le message d'erreur
                    JOptionPane.showMessageDialog(this, result, "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }  catch (Exception e) {
                // Gestion des autres exceptions
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else if (evt.getSource() == JBannuler) {
            viderChamps();
        }
    }

    // Méthode pour vider les champs de saisie
    private void viderChamps() {
        JTdepotLegal.setText("");
        JTnomCommercial.setText("");
        JTcomposition.setText("");
        JTeffets.setText("");
        JTcontreIndications.setText("");
        JTprixEchantillon.setText("");
        JTfamCode.setText("");
        JTfamLibelle.setText("");
    }
}