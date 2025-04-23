package gsb.vue;

import gsb.service.MedicamentService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JIFMedicamentAjout extends JInternalFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    // Composants
    private JPanel pFormulaire;
    private JLabel JLdepotLegal, JLnomCommercial, JLcomposition, JLeffets, JLcontreIndications, JLprixEchantillon;
    private JLabel JLfamCode, JLfamLibelle, JLdenomination, JLtype, JLdosage, JLquantite;
    private JTextField JTdepotLegal, JTnomCommercial, JTcomposition, JTeffets, JTcontreIndications, JTprixEchantillon;
    private JTextField JTfamCode, JTfamLibelle, JTdenomination, JTtype, JTdosage, JTquantite;
    private JButton JBajouter, JBannuler;

    public JIFMedicamentAjout(MenuPrincipal uneFenetreContainer) {
        super("Ajouter un médicament", true, true, true, true);

        pFormulaire = new JPanel(new GridLayout(14, 2, 10, 10)); 

        // Labels
        JLdepotLegal = new JLabel("Dépôt légal :");
        JLnomCommercial = new JLabel("Nom commercial :");
        JLcomposition = new JLabel("Composition :");
        JLeffets = new JLabel("Effets :");
        JLcontreIndications = new JLabel("Contre-indications :");
        JLprixEchantillon = new JLabel("Prix échantillon :");
        JLfamCode = new JLabel("Code famille :");
        JLfamLibelle = new JLabel("Libellé famille :");
        JLdenomination = new JLabel("Dénomination :");
        JLtype = new JLabel("Type :");
        JLdosage = new JLabel("Dosage :");
        JLquantite = new JLabel("Quantité :");

        // Champs texte
        JTdepotLegal = new JTextField(20);
        JTnomCommercial = new JTextField(20);
        JTcomposition = new JTextField(20);
        JTeffets = new JTextField(20);
        JTcontreIndications = new JTextField(20);
        JTprixEchantillon = new JTextField(20);
        JTfamCode = new JTextField(20);
        JTfamLibelle = new JTextField(20);
        JTdenomination = new JTextField(20);
        JTtype = new JTextField(20);
        JTdosage = new JTextField(20);
        JTquantite = new JTextField(20);

        // Ajout au panel
        pFormulaire.add(JLdepotLegal); pFormulaire.add(JTdepotLegal);
        pFormulaire.add(JLnomCommercial); pFormulaire.add(JTnomCommercial);
        pFormulaire.add(JLcomposition); pFormulaire.add(JTcomposition);
        pFormulaire.add(JLeffets); pFormulaire.add(JTeffets);
        pFormulaire.add(JLcontreIndications); pFormulaire.add(JTcontreIndications);
        pFormulaire.add(JLprixEchantillon); pFormulaire.add(JTprixEchantillon);
        pFormulaire.add(JLfamCode); pFormulaire.add(JTfamCode);
        pFormulaire.add(JLfamLibelle); pFormulaire.add(JTfamLibelle);
        pFormulaire.add(JLdenomination); pFormulaire.add(JTdenomination);
        pFormulaire.add(JLtype); pFormulaire.add(JTtype);
        pFormulaire.add(JLdosage); pFormulaire.add(JTdosage);
        pFormulaire.add(JLquantite); pFormulaire.add(JTquantite);

        // Boutons
        JBajouter = new JButton("Ajouter");
        JBannuler = new JButton("Annuler");
        JBajouter.addActionListener(this);
        JBannuler.addActionListener(this);

        pFormulaire.add(JBajouter);
        pFormulaire.add(JBannuler);

        // Ajout dans la fenêtre
        Container contentPane = getContentPane();
        contentPane.add(pFormulaire, BorderLayout.CENTER);
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == JBajouter) {
            try {
                String depotLegal = JTdepotLegal.getText();
                String nomCommercial = JTnomCommercial.getText();
                String composition = JTcomposition.getText();
                String effets = JTeffets.getText();
                String contreIndications = JTcontreIndications.getText();
                float prixEchantillon = Float.parseFloat(JTprixEchantillon.getText());
                String famCode = JTfamCode.getText();
                String famLibelle = JTfamLibelle.getText();
                String denomination = JTdenomination.getText();
                String type = JTtype.getText();
                String dosage = JTdosage.getText();
                String quantite = JTquantite.getText();

                String result = MedicamentService.ajouterMedicament(
                        depotLegal, nomCommercial, composition, effets,
                        contreIndications, prixEchantillon, famCode, famLibelle,
                        denomination, type, dosage, quantite
                );

                if (result.equals("Médicament ajouté avec succès !")) {
                    JOptionPane.showMessageDialog(this, result);
                    viderChamps();
                } else {
                    JOptionPane.showMessageDialog(this, result, "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else if (evt.getSource() == JBannuler) {
            viderChamps();
        }
    }

    private void viderChamps() {
        JTdepotLegal.setText("");
        JTnomCommercial.setText("");
        JTcomposition.setText("");
        JTeffets.setText("");
        JTcontreIndications.setText("");
        JTprixEchantillon.setText("");
        JTfamCode.setText("");
        JTfamLibelle.setText("");
        JTdenomination.setText("");
        JTtype.setText("");
        JTdosage.setText("");
        JTquantite.setText("");
    }
}
