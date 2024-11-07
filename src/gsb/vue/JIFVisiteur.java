package gsb.vue;

import gsb.modele.Visiteur;
import gsb.modele.dao.VisiteurDao;
import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JIFVisiteur extends JInternalFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    protected JPanel p;
    protected JPanel pTexte;
    protected JPanel pBoutons;

    protected JLabel JLmatricule;
    protected JLabel JLnom;
    protected JLabel JLprenom;
    protected JLabel JLlogin;
    protected JLabel JLmdp;
    protected JLabel JLadresse;
    protected JLabel JLcodePostal;
    protected JLabel JLdateEntree;
    protected JLabel JLcodeUnit;
    protected JLabel JLnomUnit;

    protected JTextField JTmatricule;
    protected JTextField JTnom;
    protected JTextField JTprenom;
    protected JTextField JTlogin;
    protected JTextField JTmdp;
    protected JTextField JTadresse;
    protected JTextField JTcodePostal;
    protected JTextField JTdateEntree;
    protected JTextField JTcodeUnit;
    protected JTextField JTnomUnit;

    protected JButton JBfermer;
    protected MenuPrincipal fenetreContainer;

    public JIFVisiteur(MenuPrincipal uneFenetreContainer) {
        fenetreContainer = uneFenetreContainer;
        p = new JPanel();
        pTexte = new JPanel(new GridLayout(11, 2));

        JLmatricule = new JLabel("Matricule");
        JLnom = new JLabel("Nom");
        JLprenom = new JLabel("Prénom");
        JLlogin = new JLabel("Login");
        JLmdp = new JLabel("Mot de passe");
        JLadresse = new JLabel("Adresse");
        JLcodePostal = new JLabel("Code postal");
        JLdateEntree = new JLabel("Date d'entrée");
        JLcodeUnit = new JLabel("Code unité");
        JLnomUnit = new JLabel("Nom unité");

        JTmatricule = new JTextField(20);
        JTnom = new JTextField(20);
        JTprenom = new JTextField(20);
        JTlogin = new JTextField(20);
        JTmdp = new JTextField(20);
        JTadresse = new JTextField(20);
        JTcodePostal = new JTextField(20);
        JTdateEntree = new JTextField(20);
        JTcodeUnit = new JTextField(20);
        JTnomUnit = new JTextField(20);

        pTexte.add(JLmatricule);
        pTexte.add(JTmatricule);
        pTexte.add(JLnom);
        pTexte.add(JTnom);
        pTexte.add(JLprenom);
        pTexte.add(JTprenom);
        pTexte.add(JLlogin);
        pTexte.add(JTlogin);
        pTexte.add(JLmdp);
        pTexte.add(JTmdp);
        pTexte.add(JLadresse);
        pTexte.add(JTadresse);
        pTexte.add(JLcodePostal);
        pTexte.add(JTcodePostal);
        pTexte.add(JLdateEntree);
        pTexte.add(JTdateEntree);
        pTexte.add(JLcodeUnit);
        pTexte.add(JTcodeUnit);
        pTexte.add(JLnomUnit);
        pTexte.add(JTnomUnit);

        JBfermer = new JButton("FERMER");
        JBfermer.addActionListener(this);
        pTexte.add(JBfermer);

        p.add(pTexte);
        Container contentPane = getContentPane();
        contentPane.add(p);
    }

    public void remplirText(Visiteur unVisiteur) {
        JTmatricule.setText(unVisiteur.getMatricule());
        JTnom.setText(unVisiteur.getNom());
        JTprenom.setText(unVisiteur.getPrenom());
        JTlogin.setText(unVisiteur.getLogin());
        JTmdp.setText(unVisiteur.getMdp());
        JTadresse.setText(unVisiteur.getAdresse());
        JTcodePostal.setText(unVisiteur.getCodePostal());
        JTdateEntree.setText(String.valueOf(unVisiteur.getDateEntree()));
        JTcodeUnit.setText(unVisiteur.getCodeUnit());
        JTnomUnit.setText(unVisiteur.getNomUnit());
    }

    public void viderText() {
        JTmatricule.setText("");
        JTnom.setText("");
        JTprenom.setText("");
        JTlogin.setText("");
        JTmdp.setText("");
        JTadresse.setText("");
        JTcodePostal.setText("");
        JTdateEntree.setText("");
        JTcodeUnit.setText("");
        JTnomUnit.setText("");
    }

    public void actionPerformed(ActionEvent arg0) {
        fenetreContainer.ouvrirFenetre(new JIFVisiteurListeCol(fenetreContainer));
    }
}
