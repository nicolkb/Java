package gsb.vue;

import gsb.modele.Stock;
import gsb.modele.dao.StockDao;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class JIFStockVisiteur extends JInternalFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private ArrayList<Stock> lesStocks;
    protected JPanel p;
    protected JScrollPane scrollPane;
    protected JPanel pSaisie;
    protected JTextField JTcodeVisiteur;
    protected JButton JBafficherStock;
    protected MenuPrincipal fenetreContainer;
    private JTable table;

    public JIFStockVisiteur(MenuPrincipal uneFenetreContainer) {
        fenetreContainer = uneFenetreContainer;

        // Récupère tous les stocks au chargement
        lesStocks = StockDao.getAllStocks();

        p = new JPanel();
        
        // Initialisation des données pour le tableau
        String[][] data = new String[lesStocks.size()][3];
        int i = 0;
        for (Stock unStock : lesStocks) {
            data[i][0] = unStock.getMatricule(); // Matricule du visiteur
            data[i][1] = unStock.getMedDepotLegal(); // Code Médicament
            data[i][2] = String.valueOf(unStock.getStock()); // Quantité
            i++;
        }

        String[] columnNames = {"Matricule Visiteur", "Code Medicament", "Stock"}; // Noms des colonnes
        table = new JTable(data, columnNames);

        // Ajout du listener pour remplir le champ `JTcodeVisiteur` avec le matricule sélectionné
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) { // Éviter que le listener soit appelé plusieurs fois
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        // Récupère le matricule du visiteur sélectionné et le place dans JTcodeVisiteur
                        String selectedMatricule = (String) table.getValueAt(selectedRow, 0);
                        JTcodeVisiteur.setText(selectedMatricule);
                    }
                }
            }
        });

        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        p.add(scrollPane);
        
        pSaisie = new JPanel();
        JLabel labelCode = new JLabel("Code visiteur");
        JTcodeVisiteur = new JTextField(20);
        JTcodeVisiteur.setMaximumSize(JTcodeVisiteur.getPreferredSize());
        JBafficherStock = new JButton("Afficher Stock");
        JBafficherStock.addActionListener(this);
        
        pSaisie.add(labelCode);
        pSaisie.add(JTcodeVisiteur);
        pSaisie.add(JBafficherStock);
        p.add(pSaisie);
        
        Container contentPane = getContentPane();
        contentPane.add(p);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        Object source = arg0.getSource();
        if (source == JBafficherStock) {
            String codeVisiteur = JTcodeVisiteur.getText();
            lesStocks = StockDao.getStockByVisiteur(codeVisiteur);

            String[][] data = new String[lesStocks.size()][3];
            int i = 0;
            for (Stock unStock : lesStocks) {
                data[i][0] = unStock.getMatricule(); // Matricule du visiteur
                data[i][1] = unStock.getMedDepotLegal(); // Code Médicament
                data[i][2] = String.valueOf(unStock.getStock()); // Quantité
                i++;
            }

            // Mise à jour de la table avec les stocks du visiteur spécifié
            table.setModel(new javax.swing.table.DefaultTableModel(
                data, 
                new String[]{"Matricule Visiteur", "Code Medicament", "Stock"}
            ));
        }
    }
}

