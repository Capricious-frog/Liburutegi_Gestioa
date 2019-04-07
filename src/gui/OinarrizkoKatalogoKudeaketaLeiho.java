package gui;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;

import biblio.ErregZenbEzezaguna;
import biblio.Liburutegia;
//import biblio.Liburutegia.ErregZenbEzezaguna;
import obrak.Obra;


@SuppressWarnings("serial")
public class OinarrizkoKatalogoKudeaketaLeiho extends JDialog {

    private JLabel lblKatalogoa; //etiketak
    private JButton btnKatBistaratu, btnObraGehitu, btnObraKendu, btnKatGorde; //botoiak
    private JOptionPane oharra; //ohar-leihoak bistaratzeko panela


    /**
     * Eraikitzailea
     */
    public OinarrizkoKatalogoKudeaketaLeiho() {

        this.getContentPane().setLayout(
                new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        lblKatalogoa = new JLabel("Liburutegiaren katalogoa");
        lblKatalogoa.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(lblKatalogoa);
        lblKatalogoa.setFont(new Font("Tahoma", Font.BOLD, 14));

        this.setTitle("Liburutegia - Katalogoaren kudeaketa");


        // menu-barra sortu eta markoan gehitu
        JMenuBar menuBarra = new JMenuBar();
        this.setJMenuBar(menuBarra);

        // Drop down menua sortu eta menu-barrari egokitu
        JMenu menua = new JMenu("Menua");
        menuBarra.add(menua);

        // Menua sortu:
        JMenuItem mnuKatBistaratu = new JMenuItem("Bistaratu katalogoa");
        JMenuItem mnuObraGehitu = new JMenuItem("Gehitu obra");
        JMenuItem mnuObraKendu = new JMenuItem("Kendu obra");
        JMenuItem mnuKatGorde = new JMenuItem("Gorde katalogoa");

        menua.add(mnuKatBistaratu);
        menua.addSeparator();
        menua.add(mnuObraGehitu);
        menua.add(mnuObraKendu);
        menua.addSeparator();
        menua.add(mnuKatGorde);

        // Botoiak sortu:
        btnKatBistaratu = new JButton("Bistaratu katalogoa");
        this.add(btnKatBistaratu);
        btnObraGehitu = new JButton("Gehitu obra");
        this.add(btnObraGehitu);
        btnObraKendu = new JButton("Kendu obra");
        this.add(btnObraKendu);
        btnKatGorde = new JButton("Gorde katalogoa");
        this.add(btnKatGorde);

        this.pack();

        ActionListener actKatBistaratu = new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Liburutegia lib = Liburutegia.getInstance();
                lib.katalogoaBistaratu();
            }
        };

        ActionListener actObraGehitu = new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Liburutegia lib = Liburutegia.getInstance();
//				try {
                OinarrizkoAleLeiho dlgAleBerria = new OinarrizkoAleLeiho();
                dlgAleBerria.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
                dlgAleBerria.setVisible(true);

                if (dlgAleBerria.adosSakaturik()) {
                    //alea eraiki eta gehitu katalogoan, motaren arabera:
                    int erregZenbakia = lib.getHurrengoErregZenbakia();
                    lib.gehituObra( //int eZenb, String sig, String izenb
                            new Obra(erregZenbakia,
                                    dlgAleBerria.getTxtSignatura(),
                                    dlgAleBerria.getTxtIzenburua()));

                    JOptionPane.showMessageDialog(dlgAleBerria,
                            erregZenbakia + " erregistro-zenbakiko alea gehitu da.");
                }
                dlgAleBerria.dispose(); //'Ados' zein 'Utzi' sakatu
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
            }
        };

        ActionListener actObraKendu = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int erantzuna = JOptionPane.YES_OPTION;
                //eskatu erabiltzaileari erregistro-zenbakia sar dezan:
                String erregZenbakia = JOptionPane.showInputDialog(null,
                        "Sar ezazu kendu beharreko alearen erregistro-zenbakia:",
                        "Erregistro-zenbakia?",
                        JOptionPane.QUESTION_MESSAGE);
                Liburutegia lib = Liburutegia.getInstance();
                Obra obra;
                try {
                    if (erregZenbakia != null) { //ez du Cancel sakatu
                        obra = lib.erregZenbDuenAlea(Integer.parseInt(erregZenbakia));
                        if (obra.getErregistroZenbakia() != 0) { //alea existitzen bada...
                            if (obra.getMaileguanDago() == true) { //maileguan badago, baieztarazi ezabatu nahia
                                erantzuna = JOptionPane.showConfirmDialog(
                                        null,
                                        "Ale hori maileguan dago: aurrera egin nahi duzu?",
                                        "Liburutegia - Ale mailegatua, ezabaketa baieztarazi",
                                        JOptionPane.YES_NO_OPTION);
                                if (erantzuna == JOptionPane.YES_OPTION) {
                                    erantzuna = JOptionPane.showConfirmDialog(
                                            null,
                                            erregZenbakia + " erregistro-zenbakiko ale mailegatua ezabatzera zoaz: ziur zaude?",
                                            "Liburutegia - Ale mailegatua ezabatu, baieztapena",
                                            JOptionPane.YES_NO_OPTION);
                                    if (erantzuna == JOptionPane.YES_OPTION) {
                                        aleaEzabarazi(Integer.parseInt(erregZenbakia));
                                    }
                                }
                            } else { //ezabatu alea
                                erantzuna = JOptionPane.showConfirmDialog(
                                        null,
                                        erregZenbakia + " erregistro-zenbakiko alea ezabatzera zoaz: ziur zaude?",
                                        "Liburutegia - Alea ezabatu, baieztapena",
                                        JOptionPane.YES_NO_OPTION);
                                if (erantzuna == JOptionPane.YES_OPTION) {
                                    aleaEzabarazi(Integer.parseInt(erregZenbakia));
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Ale hori ez da existitzen, ezin da kendu!",
                                    "Liburutegia - Ezabaketa ez da zilegi",
                                    JOptionPane.OK_OPTION);
                        }
                    }
                } catch (ErregZenbEzezaguna e1) {
                    e1.printStackTrace();
                }
            }
        };

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

        //menu-aukerak eta botoiak ekintza berberen bidez erantzun dezaten prestatu:
        mnuKatBistaratu.addActionListener(actKatBistaratu);
        btnKatBistaratu.addActionListener(actKatBistaratu);

        mnuObraGehitu.addActionListener(actObraGehitu);
        btnObraGehitu.addActionListener(actObraGehitu);

        mnuObraKendu.addActionListener(actObraKendu);
        btnObraKendu.addActionListener(actObraKendu);

        mnuKatGorde.addActionListener(actKatGorde);
        btnKatGorde.addActionListener(actKatGorde);

    }


    /**
     * @param erregZenbakia ezabarazi beharreko alearen erregistro-zenbakia
     *                      erregZenbakia erregistro-zenbakidun alea ezabarazten du katalogotik
     */
    private void aleaEzabarazi(int erregZenbakia) {
        Liburutegia lib = Liburutegia.getInstance();
//		try {
        // erregistro-zenbaki bidezko ezabaketa:
        lib.kenduObra(erregZenbakia);
        JOptionPane.showMessageDialog(null, erregZenbakia
                + " erregistro-zenbakiko alea ezabatu da.");
//		} catch (Liburutegia.ErregZenbEzezaguna p) {
//			System.err.println("Ez da aurkitu liburutegian " + erregZenbakia
//					+ " erregistro-zenbakiko alerik."); //***tratamendurik???
//		}
    }

}



