package gsb.vue;

import gsb.modele.Visite;
import gsb.modele.dao.VisiteDao;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class JIFVisiteListe extends JInternalFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    protected JPanel p;
    protected JScrollPane scrollPane;
    protected JPanel pSaisie;
    protected JComboBox<String> JCBcodeVisiteur; // Menu déroulant pour les codes visiteurs
    protected JComboBox<String> JCBdateVisite;   // Menu déroulant pour les dates de visite
    protected JButton JBafficherDetails;
    protected JButton JBrecherche;
    private JTable table;
    private JTextField JTreference; 
    private MenuPrincipal fenetreContainer;

    public JIFVisiteListe(MenuPrincipal uneFenetreContainer) {
        fenetreContainer = uneFenetreContainer;
        p = new JPanel();
        p.setLayout(new BorderLayout());

        // Saisie du code visiteur et de la date de visite
        pSaisie = new JPanel();
        pSaisie.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel labelCodeVisiteur = new JLabel("Code visiteur");
        JCBcodeVisiteur = new JComboBox<>(); // Menu déroulant pour le code visiteur
        remplirCodeVisiteurs(); // Remplir la JComboBox avec les codes visiteurs ayant des visites
        pSaisie.add(labelCodeVisiteur, gbc);

        gbc.gridy = 1;
        pSaisie.add(JCBcodeVisiteur, gbc);

        JLabel labelDateVisite = new JLabel("Date visite");
        JCBdateVisite = new JComboBox<>(); // Menu déroulant pour les dates de visite
        remplirDatesVisite(); // Remplir la JComboBox avec les dates de visite
        gbc.gridy = 2;
        pSaisie.add(labelDateVisite, gbc);

        gbc.gridy = 3;
        pSaisie.add(JCBdateVisite, gbc);

        JBrecherche = new JButton("Rechercher");
        JBrecherche.addActionListener(this);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 3;
        gbc.fill = GridBagConstraints.VERTICAL;
        pSaisie.add(JBrecherche, gbc);

        p.add(pSaisie, BorderLayout.NORTH);

        // Initialisation du tableau avec toutes les visites et la ville
        List<Visite> toutesLesVisitesAvecVille = VisiteDao.getAllVisitesAvecVille();
        table = new JTable(new String[toutesLesVisitesAvecVille.size()][3], new String[] {"Référence", "Code médecin", "Lieu"});
        updateTableData(toutesLesVisitesAvecVille);
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        p.add(scrollPane, BorderLayout.CENTER);

        // Ajout d'un listener pour détecter les sélections de lignes
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        // Récupère la référence de la visite sélectionnée et la place dans JTreference
                        String selectedReference = (String) table.getValueAt(selectedRow, 0);
                        JTreference.setText(selectedReference);
                    }
                }
            }
        });

        JPanel pReference = new JPanel();
        JLabel labelReference = new JLabel("Référence");
        JTreference = new JTextField(10); 
        JBafficherDetails = new JButton("Visite détaillée");
        JBafficherDetails.addActionListener(this);

        pReference.add(labelReference);
        pReference.add(JTreference);
        pReference.add(JBafficherDetails);
        p.add(pReference, BorderLayout.SOUTH);

        Container contentPane = getContentPane();
        contentPane.add(p);
    }

    // Remplir le menu déroulant avec les codes visiteurs ayant des visites
    private void remplirCodeVisiteurs() {
        List<String> codesVisiteurs = VisiteDao.getMatriculesAvecVisites();
        for (String code : codesVisiteurs) {
            JCBcodeVisiteur.addItem(code);
        }
    }

    // Remplir le menu déroulant avec les dates de visite disponibles
    private void remplirDatesVisite() {
        List<String> datesVisite = VisiteDao.getDatesVisite();
        for (String date : datesVisite) {
            JCBdateVisite.addItem(date);
        }
    }

    private void updateTableData (List<Visite> visites) {
        String[][] data = new String[visites.size()][3];
        for (int i = 0; i < visites.size(); i++) {
            Visite uneVisite = visites.get(i);
            data[i][0] = uneVisite.getReference();
            data[i][1] = uneVisite.getCodeMed();
            data[i][2] = uneVisite.getVille();
        }
        table.setModel(new javax.swing.table.DefaultTableModel(
            data,
            new String[] {"Référence", "Code médecin", "Lieu"}
        ));
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == JBrecherche) {
            String codeVisiteur = (String) JCBcodeVisiteur.getSelectedItem(); // Récupérer le code visiteur sélectionné
            String dateVisite = (String) JCBdateVisite.getSelectedItem();     // Récupérer la date de visite sélectionnée
            List<Visite> visitesFiltrees = VisiteDao.getVisitesByMatriculeAndDate(codeVisiteur, dateVisite);
            updateTableData(visitesFiltrees);

        } else if (arg0.getSource() == JBafficherDetails) {
            String reference = JTreference.getText();
            Visite visite = VisiteDao.getVisiteByReference(reference);
            if (visite != null) {
                fenetreContainer.ouvrirFenetre(new JIFVisite(fenetreContainer, visite));
            } else {
                System.out.println("Aucune visite trouvée pour la référence donnée.");
            }
        }
    }
}

