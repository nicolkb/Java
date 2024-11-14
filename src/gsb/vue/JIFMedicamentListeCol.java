package gsb.vue;

import gsb.modele.Medicament;
import gsb.modele.dao.MedicamentDao;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class JIFMedicamentListeCol extends JInternalFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private ArrayList<Medicament> lesMedicaments;
    protected JPanel p;
    protected JScrollPane scrollPane;
    protected JPanel pSaisie;
    protected JTextField JTcodeMedicament;
    protected JButton JBafficherFiche;
    protected MenuPrincipal fenetreContainer;
    private JTable table;

    public JIFMedicamentListeCol(MenuPrincipal uneFenetreContainer) {
        fenetreContainer = uneFenetreContainer;
        lesMedicaments = MedicamentDao.retournerCollectionDesMedicaments();
        int nbLignes = lesMedicaments.size();

        p = new JPanel(); 

        int i = 0;
        String[][] data = new String[nbLignes][3]; // Changez le nombre de colonnes selon vos besoins
        for (Medicament unMedicament : lesMedicaments) {
            data[i][0] = unMedicament.getMedDepotLegal();
            data[i][1] = unMedicament.getMedNomCommercial();
            data[i][2] = unMedicament.getFamLibelle(); // Ajoutez d'autres attributs si nécessaire
            i++;
        }
        String[] columnNames = {"Code", "Nom", "Famille"};
        table = new JTable(data, columnNames);
        
        // Ajout d'un listener pour détecter les sélections de lignes
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) { // S'assure que le listener n'est pas déclenché en double
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        // Récupère le code du médicament sélectionné et le place dans JTcodeMedicament
                        String selectedCode = (String) table.getValueAt(selectedRow, 0);
                        JTcodeMedicament.setText(selectedCode);
                    }
                }
            }
        });

        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        p.add(scrollPane);
        
        pSaisie = new JPanel();
        JLabel labelCode = new JLabel("Code");
        JTcodeMedicament = new JTextField(20);
        JTcodeMedicament.setMaximumSize(JTcodeMedicament.getPreferredSize());
        JBafficherFiche = new JButton("Fiche médicament détaillée");
        JBafficherFiche.addActionListener(this);
        pSaisie.add(labelCode);
        pSaisie.add(JTcodeMedicament);
        pSaisie.add(JBafficherFiche);
        p.add(pSaisie);

        Container contentPane = getContentPane();
        contentPane.add(p);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        Object source = arg0.getSource();
        if (source == JBafficherFiche) {
            Medicament unMedicament = MedicamentDao.rechercher(JTcodeMedicament.getText());
            if (unMedicament != null) {
                fenetreContainer.ouvrirFenetre(new JIFMedicamentFiche(fenetreContainer, unMedicament));
            }
        }   
    }
}

