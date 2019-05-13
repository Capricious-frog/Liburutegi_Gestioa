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

@SuppressWarnings("serial")
public class LeihoNagusi extends JFrame {

    private JLabel izenburukoEtiketa; //leihoaren izenburua

    private JButton btnKatBistaratu, btnKatGorde, btnMaiKudeatu, btnIrten; //botoiak

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
        JMenuItem mnuKatBistaratu = new JMenuItem("Bistaratu katalogoa");
        JMenuItem mnuKatGorde = new JMenuItem("Gorde katalogoa");
        JMenuItem mnuMaiKudeatu = new JMenuItem("Kudeatu maileguak");
        JMenuItem mnuIrten = new JMenuItem("Irten");

        menuNagusia.add(mnuKatBistaratu);
        menuNagusia.add(mnuKatGorde);
        menuNagusia.addSeparator();
        menuNagusia.add(mnuMaiKudeatu);
        menuNagusia.addSeparator();
        menuNagusia.add(mnuIrten);


        // Botoi nagusiak sortu:
        //		btnInbKargatu = new JButton("Inbentarioa kargatu");
        //		this.add(btnInbKargatu);
        btnKatBistaratu = new JButton("Bistaratu katalogoa");
        this.add(btnKatBistaratu);
        btnKatGorde = new JButton("Gorde katalogoa");
        this.add(btnKatGorde);
        btnMaiKudeatu = new JButton("Kudeatu maileguak");
        this.add(btnMaiKudeatu);
        btnIrten = new JButton("Irten");
        this.add(btnIrten);

        this.pack();


        // menuko aukerei eta botoiei erantzuteko ekintzak definitu:

        //		mnuKargatu.addActionListener(new ActionListener() {
        //		ActionListener actInbKargatu = new ActionListener() {
        //			public void actionPerformed(ActionEvent arg0) {
        //				int erantzuna = JOptionPane.YES_OPTION;
        //				Liburutegia lib = Liburutegia.getInstance();
        //				if (inb.kargatutaDago()) { //kontuz, inbentarioa lehendik kargatuta dago-eta...
        //					erantzuna = JOptionPane.showConfirmDialog (
        //							null,
        //							"Inbentarioa fitxategitik kargatuz gero, gorde gabeko aldaketa guztiak galduko dira. Aurrera egingo?",
        //							"Super Online - Inbentarioa kargatu, baieztapena",
        //							JOptionPane.YES_NO_OPTION);
        //				}
        //
        //				if(erantzuna == JOptionPane.YES_OPTION) {
        //					try {
        //						inb.kargatuProduktuakFitxategitik();
        //						//JOptionPane.showMessageDialog((Component)(arg0.getSource()), "Kargatu da inbentarioa fitxategitik.");
        //						//JOptionPane.showMessageDialog(null, "Kargatu da inbentarioa fitxategitik.");
        //						oharra = new JOptionPane("Kargatu da inbentarioa fitxategitik.",
        //								JOptionPane.INFORMATION_MESSAGE);
        //						oharLeihoa = oharra.createDialog("Super Online - Info");
        //						oharLeihoa.setAlwaysOnTop(true);
        //						oharLeihoa.setVisible(true);
        //					} catch (Inbentarioa.KodeEzezaguna e1) {
        //						System.err.println(e1.getMessage());
        //					}
        //				}
        //			}
        //
        //		};

        //		mnuInbBistaratu.addActionListener(new ActionListener() {
        ActionListener actKatBistaratu = new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Liburutegia lib = Liburutegia.getInstance();

//				if (!lib.kargatutaDago()) 
//					lib.kargatuKatalogoaEtaMaileguakFitxategitik(); //kargatuta ez badago, kargatu egiten da

                //				lib.bistaratuKatalogoa(); //***traza

                //				{
                //					oharra = new JOptionPane("Inbentarioa bistaratu ahal izateko, lehenago kargatu egin behar da.",
                //							JOptionPane.WARNING_MESSAGE);
                //					oharLeihoa = oharra.createDialog("Super Online - Oharra");
                //					oharLeihoa.setAlwaysOnTop(true);
                //					oharLeihoa.setVisible(true);
                //				} else {
                try {
                    KatalogoLeiho katLeihoa = new KatalogoLeiho(lib.katalogokoAleenMatrizea());
                    katLeihoa.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    katLeihoa.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
                    katLeihoa.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //				}

            }
        };

        //		mnuInbGorde.addActionListener(new ActionListener() {
        ActionListener actKatGorde = new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Liburutegia lib = Liburutegia.getInstance();
//				if (!lib.kargatutaDago()) {
//					oharra = new JOptionPane("Katalogoa gorde ahal izateko, lehenago kargatu egin behar da.",
//							JOptionPane.WARNING_MESSAGE);
//					oharLeihoa = oharra.createDialog("Liburutegia - Oharra");
//					oharLeihoa.setAlwaysOnTop(true);
//					oharLeihoa.setVisible(true);
//				} else {			
                lib.gorde();
                oharra = new JOptionPane("Gorde da katalogo eguneratua fitxategian.",
                        JOptionPane.INFORMATION_MESSAGE);
                JDialog oharLeihoa = oharra.createDialog("Liburutegia - Info");
                oharLeihoa.setAlwaysOnTop(true);
                oharLeihoa.setVisible(true);
//				}				
            }
        };


        ActionListener actMaiKudeatu = new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                Liburutegia lib = Liburutegia.getInstance();

//				if (!lib.kargatutaDago()) {
//					oharra = new JOptionPane("Maileguak kudeatu ahal izateko, lehenago katalogoa kargatu behar da.",
//							JOptionPane.WARNING_MESSAGE);
//					oharLeihoa = oharra.createDialog("Liburutegia - Oharra");
//					oharLeihoa.setAlwaysOnTop(true);
//					oharLeihoa.setVisible(true);
//				} else {
                try {
                    MaileguZerrendaLeiho maileguZerrendaLeihoa = new MaileguZerrendaLeiho(lib.mailegagarrienMatrizea());
                    maileguZerrendaLeihoa.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    maileguZerrendaLeihoa.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
                    maileguZerrendaLeihoa.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
//			}
            }
        };


        //		mnuIrten.addActionListener(new ActionListener() {
        ActionListener actIrten = new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                oharra = new JOptionPane("Agur, hurrena arte.",
                        JOptionPane.INFORMATION_MESSAGE);
                oharLeihoa = oharra.createDialog("Liburutegia - Agurra");
                oharLeihoa.setAlwaysOnTop(true);
                //oharLeihoa.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
                oharLeihoa.setVisible(true);
                System.exit(0);
            }
        };


        //menu-aukerak eta botoiak ekintza berberen bidez erantzun dezaten prestatu:
        mnuKatBistaratu.addActionListener(actKatBistaratu);
        btnKatBistaratu.addActionListener(actKatBistaratu);

        mnuKatGorde.addActionListener(actKatGorde);
        btnKatGorde.addActionListener(actKatGorde);

        mnuMaiKudeatu.addActionListener(actMaiKudeatu);
        btnMaiKudeatu.addActionListener(actMaiKudeatu);

        mnuIrten.addActionListener(actIrten);
        btnIrten.addActionListener(actIrten);

    }

}

