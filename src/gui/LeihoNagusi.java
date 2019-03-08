package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import biblio.Liburutegia;

//import obrak.Aldizkari;
//import obrak.DVD;
//import obrak.Entziklopedia;
//import obrak.Hiztegi;
//import obrak.Liburu;
//import obrak.MusikaCD;

@SuppressWarnings("serial")
public class LeihoNagusi extends JFrame {

    private JLabel izenburukoEtiketa; //leihoaren izenburua

    private JButton btnKatKudeatu, btnMaiKudeatu, btnKatGorde, btnIrten; //botoiak

    private JOptionPane oharra; //ohar-leihoak bistaratzeko panela
    private JDialog oharLeihoa; //ohar-leihoa

    /**
     * Eraikitzailea
     */
    public LeihoNagusi() {

        this.setTitle("Liburutegia - Leiho nagusia");
        //this.setSize( 1200, 5000);
        //setLayout();
        this.getContentPane().setLayout(
                new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));


        // menu-barra sortu eta markoan gehitu
        JMenuBar menuBarra = new JMenuBar();
        this.setJMenuBar(menuBarra);

        // Drop down menua sortu eta menu-barrari egokitu
        JMenu menuNagusia = new JMenu("Menua");
        //JMenu editMenu = new JMenu("Edit");
        menuBarra.add(menuNagusia);
        //menuBarra.add(editMenu);


        izenburukoEtiketa = new JLabel("Liburutegia");
        izenburukoEtiketa.setFont(new Font("Arial", 1, 16));
        //izenburukoEtiketa.setSize(new Dimension (2000, 2000));
        //testuNagusia.setBounds(3000, 300, 390, 10);
        izenburukoEtiketa.setAlignmentX(CENTER_ALIGNMENT);
        //izenburukoEtiketa.setAlignmentY(TOP_ALIGNMENT);
        this.add(izenburukoEtiketa);


        // Menu nagusia sortu:
        JMenuItem mnuKatKudeatu = new JMenuItem("Kudeatu katalogoa");
        JMenuItem mnuMaiKudeatu = new JMenuItem("Kudeatu maileguak");
        JMenuItem mnuKatGorde = new JMenuItem("Gorde katalogoa");
        JMenuItem mnuIrten = new JMenuItem("Irten");

        menuNagusia.add(mnuKatKudeatu);
        menuNagusia.add(mnuMaiKudeatu);
        menuNagusia.addSeparator();
        menuNagusia.add(mnuKatGorde);
        menuNagusia.addSeparator();
        menuNagusia.add(mnuIrten);


        // Botoi nagusiak sortu:
        btnKatKudeatu = new JButton("Kudeatu katalogoa");
        this.add(btnKatKudeatu);
        btnMaiKudeatu = new JButton("Kudeatu maileguak");
        this.add(btnMaiKudeatu);
        btnKatGorde = new JButton("Gorde katalogoa");
        this.add(btnKatGorde);
        btnIrten = new JButton("Irten");
        this.add(btnIrten);

        this.pack();


        // menuko aukerei eta botoiei erantzuteko ekintzak definitu:

        //		mnuInbBistaratu.addActionListener(new ActionListener() {
        ActionListener actKatKudeatu = new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                OinarrizkoKatalogoKudeaketaLeiho katLeihoa = new OinarrizkoKatalogoKudeaketaLeiho();
                katLeihoa.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                katLeihoa.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
                katLeihoa.setVisible(true);
            }
        };

        ActionListener actMaiKudeatu = new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                OinarrizkoMaileguKudeaketaLeiho maileguZerrendaLeihoa = new OinarrizkoMaileguKudeaketaLeiho();
                maileguZerrendaLeihoa.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                maileguZerrendaLeihoa.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
                maileguZerrendaLeihoa.setVisible(true);
            }
        };

        //		mnuInbGorde.addActionListener(new ActionListener() {
        ActionListener actKatGorde = new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Liburutegia lib = Liburutegia.getInstance();
                lib.gorde();
                oharra = new JOptionPane("Gorde da katalogo eguneratua fitxategian.",
                        JOptionPane.INFORMATION_MESSAGE);
                JDialog oharLeihoa = oharra.createDialog("Liburutegia - Info");
                oharLeihoa.setAlwaysOnTop(true);
                oharLeihoa.setVisible(true);
            }
        };

        //		mnuIrten.addActionListener(new ActionListener() {
        ActionListener actIrten = new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                oharra = new JOptionPane("Agur, hurrena arte.",
                        JOptionPane.INFORMATION_MESSAGE);
                oharLeihoa = oharra.createDialog("Liburutegia - Agurra");
                oharLeihoa.setAlwaysOnTop(true);
                oharLeihoa.setVisible(true);
                System.exit(0);
            }
        };


        //menu-aukerak eta botoiak ekintza berberen bidez erantzun dezaten prestatu:

        mnuKatKudeatu.addActionListener(actKatKudeatu);
        btnKatKudeatu.addActionListener(actKatKudeatu);

        mnuKatGorde.addActionListener(actKatGorde);
        btnKatGorde.addActionListener(actKatGorde);

        mnuMaiKudeatu.addActionListener(actMaiKudeatu);
        btnMaiKudeatu.addActionListener(actMaiKudeatu);

        mnuIrten.addActionListener(actIrten);
        btnIrten.addActionListener(actIrten);

    }

}

