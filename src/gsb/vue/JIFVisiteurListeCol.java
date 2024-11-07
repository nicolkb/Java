package gsb.vue;

import gsb.modele.Visiteur;
import gsb.modele.dao.VisiteurDao;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class JIFVisiteurListeCol extends JInternalFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private ArrayList<Visiteur> lesVisiteurs;
    protected JPanel p;
    protected JScrollPane scrollPane;
    protected JPanel pSaisie;
    protected JTextField JTcodeVisiteur;
    protected JButton JBafficherFiche;
    protected MenuPrincipal fenetreContainer;

    public JIFVisiteurListeCol(MenuPrincipal uneFenetreContainer) {
        fenetreContainer = uneFenetreContainer;
        lesVisiteurs = VisiteurDao.retournerCollectionDesVisiteurs(); // Assurez-vous que cette méthode existe
        int nbLignes = lesVisiteurs.size();

        JTable table;
        p = new JPanel(); 

        int i = 0;
        String[][] data = new String[nbLignes][3]; // Changez le nombre de colonnes selon vos besoins
        for (Visiteur unVisiteur : lesVisiteurs) {
            data[i][0] = unVisiteur.getMatricule(); // Ajustez cela selon les attributs de Visiteur
            data[i][1] = unVisiteur.getNom(); // Ajustez cela
            data[i][2] = unVisiteur.getPrenom(); // Ajustez cela
            i++;
        }
        String[] columnNames = {"Code", "Nom", "Prénom"}; // Modifiez selon vos colonnes
        table = new JTable(data, columnNames);
        
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        p.add(scrollPane);
        
        pSaisie = new JPanel();
        JLabel labelCode = new JLabel("Code");
        JTcodeVisiteur = new JTextField(20);
        JTcodeVisiteur.setMaximumSize(JTcodeVisiteur.getPreferredSize());
        JBafficherFiche = new JButton("Fiche visiteur détaillée");
        JBafficherFiche.addActionListener(this);
        pSaisie.add(labelCode);
        pSaisie.add(JTcodeVisiteur);
        pSaisie.add(JBafficherFiche);
        p.add(pSaisie);
        Container contentPane = getContentPane();
        contentPane.add(p);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        Object source = arg0.getSource();
        if (source == JBafficherFiche) {
            Visiteur unVisiteur = VisiteurDao.selectionnerVisiteur(JTcodeVisiteur.getText());
            if (unVisiteur != null) {
                fenetreContainer.ouvrirFenetre(new JIFVisiteurFiche(fenetreContainer, unVisiteur)); // Assurez-vous que le constructeur existe
            }
        }   
    }
}
