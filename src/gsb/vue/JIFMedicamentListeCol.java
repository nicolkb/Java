package gsb.vue;

import gsb.modele.Medicament;
import gsb.modele.dao.MedicamentDao;

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

public class JIFMedicamentListeCol extends JInternalFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private ArrayList<Medicament> lesMedicaments;
    protected JPanel p;
    protected JScrollPane scrollPane;
    protected JPanel pSaisie;
    protected JTextField JTcodeMedicament;
    protected JButton JBafficherFiche;
    protected MenuPrincipal fenetreContainer;

    public JIFMedicamentListeCol(MenuPrincipal uneFenetreContainer) {
        fenetreContainer = uneFenetreContainer;
        lesMedicaments = MedicamentDao.retournerCollectionDesMedicaments();
        int nbLignes = lesMedicaments.size();

        JTable table;
        p = new JPanel(); 

        int i = 0;
        String[][] data = new String[nbLignes][3]; // Changez le nombre de colonnes selon vos besoins
        for(Medicament unMedicament : lesMedicaments){
            data[i][0] = unMedicament.getMedDepotLegal();
            data[i][1] = unMedicament.getMedNomCommercial();
            data[i][2] = unMedicament.getFamLibelle(); // Ajoutez d'autres attributs si nécessaire
            i++;
        }
        String[] columnNames = {"Code", "Nom", "Famille"};
        table = new JTable(data, columnNames);
        
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
