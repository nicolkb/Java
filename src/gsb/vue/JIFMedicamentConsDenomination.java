package gsb.vue;

import gsb.modele.Medicament;
import gsb.modele.dao.MedicamentDao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class JIFMedicamentConsDenomination extends JInternalFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    protected JPanel panelPrincipal;
    protected JScrollPane scrollPane;
    protected JPanel panelSaisie;
    protected JComboBox<String> comboDenomination;
    protected JButton boutonRechercher;
    protected JTable table;
    protected MenuPrincipal fenetreContainer;

    public JIFMedicamentConsDenomination(MenuPrincipal uneFenetreContainer) {
        fenetreContainer = uneFenetreContainer;

        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());

        List<String> denominations = MedicamentDao.retournerToutesLesDenominations();
        if (denominations == null || denominations.isEmpty()) {
            denominations = List.of("Aucune dénomination disponible");
        }
        comboDenomination = new JComboBox<>(denominations.toArray(new String[0]));

        boutonRechercher = new JButton("Rechercher");
        boutonRechercher.addActionListener(this);

        panelSaisie = new JPanel();
        panelSaisie.add(comboDenomination);
        panelSaisie.add(boutonRechercher);

        String[][] data = {};
        String[] columnNames = {"Nom", "Dénomination", "Type", "Dosage", "Quantité"};
        table = new JTable(new DefaultTableModel(data, columnNames));
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(700, 200));

        panelPrincipal.add(panelSaisie, BorderLayout.NORTH);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);
        setContentPane(panelPrincipal);
        pack();
    }

    public void actionPerformed(ActionEvent e) {
        String denominationChoisie = (String) comboDenomination.getSelectedItem();
        List<Medicament> lesMedicaments = MedicamentDao.rechercherParDenomination(denominationChoisie);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for (Medicament m : lesMedicaments) {
            model.addRow(new Object[]{
                m.getMedNomCommercial(),
                m.getDenominationCommun(),
                m.getType(),
                m.getDosage(),
                m.getQuantite()
            });
        }
    }
}
