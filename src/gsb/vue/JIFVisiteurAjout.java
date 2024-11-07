package gsb.vue;

import gsb.modele.Visiteur;
import gsb.service.VisiteurService;
import gsb.utils.ValidationUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JIFVisiteurAjout extends JInternalFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private JPanel pFormulaire;
    private JLabel JLmatricule, JLnom, JLprenom, JLlogin, JLmotDePasse, JLadresse, JLcodePostal, JLdateEntree, JLcodeUnit, JLnomUnit;
    private JTextField JTmatricule, JTnom, JTprenom, JTlogin, JTadresse, JTcodePostal, JTdateEntree, JTcodeUnit, JTnomUnit;
    private JPasswordField JTmotDePasse;
    private JButton JBajouter, JBannuler;

    public JIFVisiteurAjout(MenuPrincipal uneFenetreContainer) {
        super("Ajouter un visiteur", true, true, true, true);
        
        // Panneau du formulaire
        pFormulaire = new JPanel(new GridLayout(11, 2, 10, 10));
        
        // Initialisation des labels et champs texte
        JLmatricule = new JLabel("Matricule :");
        JLnom = new JLabel("Nom :");
        JLprenom = new JLabel("Prénom :");
        JLlogin = new JLabel("Login :");
        JLmotDePasse = new JLabel("Mot de passe :");
        JLadresse = new JLabel("Adresse :");
        JLcodePostal = new JLabel("Code Postal :");
        JLdateEntree = new JLabel("Date d'entrée (jj-mm-aaaa) :");
        JLcodeUnit = new JLabel("Code Unité :");
        JLnomUnit = new JLabel("Nom Unité :");

        JTmatricule = new JTextField(20);
        JTnom = new JTextField(20);
        JTprenom = new JTextField(20);
        JTlogin = new JTextField(20);
        JTmotDePasse = new JPasswordField(20);
        JTadresse = new JTextField(20);
        JTcodePostal = new JTextField(20);
        JTdateEntree = new JTextField(20);
        JTcodeUnit = new JTextField(20);
        JTnomUnit = new JTextField(20);

        // Ajout des composants au panneau
        pFormulaire.add(JLmatricule);
        pFormulaire.add(JTmatricule);
        pFormulaire.add(JLnom);
        pFormulaire.add(JTnom);
        pFormulaire.add(JLprenom);
        pFormulaire.add(JTprenom);
        pFormulaire.add(JLlogin);
        pFormulaire.add(JTlogin);
        pFormulaire.add(JLmotDePasse);
        pFormulaire.add(JTmotDePasse);
        pFormulaire.add(JLadresse);
        pFormulaire.add(JTadresse);
        pFormulaire.add(JLcodePostal);
        pFormulaire.add(JTcodePostal);
        pFormulaire.add(JLdateEntree);
        pFormulaire.add(JTdateEntree);
        pFormulaire.add(JLcodeUnit);
        pFormulaire.add(JTcodeUnit);
        pFormulaire.add(JLnomUnit);
        pFormulaire.add(JTnomUnit);

        // Boutons Ajouter et Annuler
        JBajouter = new JButton("Ajouter");
        JBannuler = new JButton("Annuler");
        JBajouter.addActionListener(this);
        JBannuler.addActionListener(this);

        pFormulaire.add(JBajouter);
        pFormulaire.add(JBannuler);

        Container contentPane = getContentPane();
        contentPane.add(pFormulaire, BorderLayout.CENTER);
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == JBajouter) {
            String matricule = JTmatricule.getText();
            String nom = JTnom.getText();
            String prenom = JTprenom.getText();
            String login = JTlogin.getText();
            String motDePasse = new String(JTmotDePasse.getPassword());
            String adresse = JTadresse.getText();
            String codePostal = JTcodePostal.getText();
            String dateEntree = JTdateEntree.getText();
            String codeUnit = JTcodeUnit.getText();
            String nomUnit = JTnomUnit.getText();

            String result = VisiteurService.ajouterVisiteur(matricule, nom, prenom, login, motDePasse, adresse, codePostal, dateEntree, codeUnit, nomUnit);
            if (result.equals("Visiteur ajouté avec succès !")) {
                JOptionPane.showMessageDialog(this, result);
                viderChamps();
            } else {
                JOptionPane.showMessageDialog(this, result, "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else if (evt.getSource() == JBannuler) {
            viderChamps();
        }
    }

    private void viderChamps() {
        JTmatricule.setText("");
        JTnom.setText("");
        JTprenom.setText("");
        JTlogin.setText("");
        JTmotDePasse.setText("");
        JTadresse.setText("");
        JTcodePostal.setText("");
        JTdateEntree.setText("");
        JTcodeUnit.setText("");
        JTnomUnit.setText("");
    }
}
