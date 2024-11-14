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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class JIFVisiteurListeCol extends JInternalFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private ArrayList<Visiteur> lesVisiteurs;
    protected JPanel p;
    protected JScrollPane scrollPane;
    protected JPanel pSaisie;
    protected JTextField JTcodeVisiteur;
    protected JButton JBafficherFiche;
    protected MenuPrincipal fenetreContainer;
    private JTable table;

    public JIFVisiteurListeCol(MenuPrincipal uneFenetreContainer) {
        fenetreContainer = uneFenetreContainer;
        lesVisiteurs = VisiteurDao.retournerCollectionDesVisiteurs(); // Assurez-vous que cette méthode existe
        int nbLignes = lesVisiteurs.size();

        p = new JPanel();

        // Préparer les données pour le tableau
        String[][] data = new String[nbLignes][3];
        for (int i = 0; i < nbLignes; i++) {
            Visiteur unVisiteur = lesVisiteurs.get(i);
            data[i][0] = unVisiteur.getMatricule();
            data[i][1] = unVisiteur.getNom();
            data[i][2] = unVisiteur.getPrenom();
        }
        String[] columnNames = {"Code", "Nom", "Prénom"};
        
        // Création du tableau
        table = new JTable(data, columnNames);
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        p.add(scrollPane);

        // Ajout d'un listener pour détecter les sélections de lignes
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) { // S'assure que le listener n'est pas déclenché en double
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        // Récupère le matricule du visiteur sélectionné et le place dans JTcodeVisiteur
                        String selectedMatricule = (String) table.getValueAt(selectedRow, 0);
                        JTcodeVisiteur.setText(selectedMatricule);
                    }
                }
            }
        });

        // Configuration du panneau de saisie
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

        // Ajouter le panneau principal au contentPane
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

