package gsb.vue;

import gsb.modele.Medicament;
import gsb.modele.dao.MedicamentDao;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class JIFMedicamentConsFamille extends JInternalFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    protected JPanel panelPrincipal;
    protected JScrollPane scrollPane;
    protected JPanel panelSaisie;
    protected JComboBox<String> comboFamille;
    protected JButton boutonRechercher;
    protected JTable table;
    protected MenuPrincipal fenetreContainer;

    // Constructeur
    public JIFMedicamentConsFamille(MenuPrincipal uneFenetreContainer) {
        fenetreContainer = uneFenetreContainer;

        panelPrincipal = new JPanel();
        
        List<String> familles = MedicamentDao.retournerToutesLesFamilles();
        comboFamille = new JComboBox<>(familles.toArray(new String[0]));
        
        // Bouton de recherche
        boutonRechercher = new JButton("Rechercher");
        boutonRechercher.addActionListener(this);
        panelSaisie = new JPanel();
        panelSaisie.add(comboFamille);
        panelSaisie.add(boutonRechercher);
        panelPrincipal.add(panelSaisie);

        // Initialisation du tableau (vide au départ)
        String[][] data = {};
        String[] columnNames = {"Dépôt Légal", "Nom Commercial", "Composition", "Effets", "Contre-indications"};
        table = new JTable(new DefaultTableModel(data, columnNames));  // Utiliser DefaultTableModel pour une mise à jour facile
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(500, 200));
        panelPrincipal.add(scrollPane);



        // Mise en forme de la fenêtre
        Container contentPane = getContentPane();
        contentPane.add(panelPrincipal);

        // Configuration de la fenêtre
        setSize(600, 400); // Taille de la fenêtre
        setVisible(true);   // Rendre la fenêtre visible
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        Object source = arg0.getSource();
        if (source == boutonRechercher) {
            // Récupérer l'élément sélectionné (qui est sous la forme "FAM_CODE - FAM_LIBELLE")
            String selectedFamille = (String) comboFamille.getSelectedItem();
            
            // Extraire uniquement le code de la famille (avant le " - ")
            String famCode = selectedFamille.split(" - ")[0];
            
            // Appeler la méthode DAO pour récupérer les médicaments par famille
            List<Medicament> medicaments = MedicamentDao.rechercherParFamille(famCode);

            // Mettre à jour les données du tableau
            String[][] data = new String[medicaments.size()][5];
            int i = 0;
            for (Medicament medicament : medicaments) {
                data[i][0] = medicament.getMedDepotLegal();
                data[i][1] = medicament.getMedNomCommercial();
                data[i][2] = medicament.getMedComposition();
                data[i][3] = medicament.getMedEffets();
                data[i][4] = medicament.getMedContreIndic();
                i++;
            }

            // Mise à jour du modèle du tableau
            String[] columnNames = {"Dépôt Légal", "Nom Commercial", "Composition", "Effets", "Contre-indications"};
            table.setModel(new DefaultTableModel(data, columnNames));
        }
    }
}
