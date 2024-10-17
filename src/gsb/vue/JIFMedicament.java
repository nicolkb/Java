package gsb.vue;

import gsb.modele.Medicament;
import gsb.modele.dao.MedicamentDao;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JInternalFrame;

public class JIFMedicament extends JInternalFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    protected JPanel p;  
    protected JPanel pTexte;
    protected JPanel pBoutons;
    
    protected JLabel JLdepotLegal;
    protected JLabel JLnomCommercial;
    protected JLabel JLcomposition;
    protected JLabel JLeffets;
    protected JLabel JLcontreIndications;
    protected JLabel JLprixEchantillon;
    protected JLabel JLfamCode;
    protected JLabel JLfamLibelle;

    protected JTextField JTdepotLegal;
    protected JTextField JTnomCommercial;
    protected JTextField JTcomposition;
    protected JTextField JTeffets;
    protected JTextField JTcontreIndications;
    protected JTextField JTprixEchantillon;
    protected JTextField JTfamCode;
    protected JTextField JTfamLibelle;
    protected JButton JBfermer;
    protected JInternalFrame myJInternalFrame;
    protected MenuPrincipal fenetreContainer;
    
    

    public JIFMedicament(MenuPrincipal uneFenetreContainer) {
    	fenetreContainer = uneFenetreContainer;
        p = new JPanel();  
        pTexte = new JPanel(new GridLayout(9, 2));
        myJInternalFrame = new JInternalFrame();
        
        JLdepotLegal = new JLabel("Dépôt légal");
        JLnomCommercial = new JLabel("Nom commercial");
        JLcomposition = new JLabel("Composition");
        JLeffets = new JLabel("Effets");
        JLcontreIndications = new JLabel("Contre indications");
        JLprixEchantillon = new JLabel("Prix échantillon");
        JLfamCode = new JLabel("Code famille");
        JLfamLibelle = new JLabel("Libellé famille");
        
        JTdepotLegal = new JTextField(20);
        JTnomCommercial = new JTextField(20);
        JTcomposition = new JTextField(20);
        JTeffets = new JTextField(20);
        JTcontreIndications = new JTextField(20);
        JTprixEchantillon = new JTextField(20);
        JTfamCode = new JTextField(20);
        JTfamLibelle = new JTextField(20);
        
        pTexte.add(JLdepotLegal);
        pTexte.add(JTdepotLegal);
        pTexte.add(JLnomCommercial);
        pTexte.add(JTnomCommercial);
        pTexte.add(JLcomposition);
        pTexte.add(JTcomposition);
        pTexte.add(JLeffets);
        pTexte.add(JTeffets);
        pTexte.add(JLcontreIndications);
        pTexte.add(JTcontreIndications);
        pTexte.add(JLprixEchantillon);
        pTexte.add(JTprixEchantillon);
        pTexte.add(JLfamCode);
        pTexte.add(JTfamCode);
        pTexte.add(JLfamLibelle);
        pTexte.add(JTfamLibelle);
        
        JBfermer = new JButton("FERMER");
        JBfermer.addActionListener(this);
        pTexte.add(JBfermer);
        
        p.add(pTexte);
        Container contentPane = getContentPane();
        contentPane.add(p);
    }
    
    public void remplirText(Medicament unMedicament) {
        JTdepotLegal.setText(unMedicament.getMedDepotLegal());
        JTnomCommercial.setText(unMedicament.getMedNomCommercial());
        JTcomposition.setText(unMedicament.getMedComposition());
        JTeffets.setText(unMedicament.getMedEffets());
        JTcontreIndications.setText(unMedicament.getMedContreIndic());
        JTprixEchantillon.setText(String.valueOf(unMedicament.getMedPrixEchantillon()));
        JTfamCode.setText(unMedicament.getFamCode());
        JTfamLibelle.setText(unMedicament.getFamLibelle());
    }
    
    public void viderText() {
        JTdepotLegal.setText("");        
        JTnomCommercial.setText("");
        JTcomposition.setText("");
        JTeffets.setText("");
        JTcontreIndications.setText("");
        JTprixEchantillon.setText("");
        JTfamCode.setText("");
        JTfamLibelle.setText("");
    }
    public void actionPerformed(ActionEvent arg0) {
                fenetreContainer.ouvrirFenetre(new JIFMedicamentListeCol(fenetreContainer));
            }   
    }
    

